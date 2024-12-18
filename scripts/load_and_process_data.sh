#!/bin/bash

set -e

# Variáveis de ambiente
INPUT_DIR=/data
OUTPUT_DIR=/data/processed
DB_HOST=postgres
DB_USER=myuser
DB_PASSWORD=mypassword
DB_NAME=mydb
TABLE_NAME=matriculas

# Exportar a senha para o psql
export PGPASSWORD=$DB_PASSWORD

# Instalar dependências no contêiner
apt-get update && apt-get install -y postgresql-client gcc python3-dev
pip install --no-cache-dir pandas psycopg2-binary

mkdir -p "$OUTPUT_DIR"

echo "Processando arquivos CSV com Python..."

# Executar Python inline para processar os dados como o antigo transform_data.py
python3 <<EOF
import os
import pandas as pd

input_dir = "$INPUT_DIR"
output_dir = "$OUTPUT_DIR"
os.makedirs(output_dir, exist_ok=True)

for file_name in os.listdir(input_dir):
    if file_name.endswith('.csv'):
        file_path = os.path.join(input_dir, file_name)
        print(f"Processando arquivo: {file_name}")
        try:
            df = pd.read_csv(file_path, sep=',')
            
            # Garantir que a coluna "Ano" seja renomeada para "ano"
            if 'Ano' in df.columns:
                df.rename(columns={"Ano": "ano"}, inplace=True)

            # Transformar usando melt para o formato longo
            df_transformed = df.melt(
                id_vars=['ano'], 
                var_name='municipio', 
                value_name='total_matriculas'
            )

            # Extrair o estado do nome do arquivo
            estado = file_name.replace('matriculas_pcd_', '').replace('.csv', '').capitalize()
            df_transformed['estado'] = estado

            # Converter ano para inteiro
            df_transformed['ano'] = pd.to_numeric(df_transformed['ano'], errors='coerce')
            df_transformed.dropna(subset=['ano'], inplace=True)
            df_transformed['ano'] = df_transformed['ano'].astype(int)

            # Limpar total_matriculas
            df_transformed['total_matriculas'] = df_transformed['total_matriculas'].astype(str)
            df_transformed['total_matriculas'] = df_transformed['total_matriculas'].str.replace('%', '', regex=False)
            df_transformed['total_matriculas'] = df_transformed['total_matriculas'].str.replace(',', '.', regex=False)
            df_transformed['total_matriculas'] = pd.to_numeric(df_transformed['total_matriculas'], errors='coerce')

            output_file = os.path.join(output_dir, file_name)
            df_transformed.to_csv(output_file, index=False)
            print(f"Arquivo processado salvo em {output_file}")
        except Exception as e:
            print(f"Erro ao processar {file_name}: {e}")
EOF

echo "Criando tabela no banco de dados..."
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "
CREATE TABLE IF NOT EXISTS $TABLE_NAME (
    id SERIAL PRIMARY KEY,
    estado VARCHAR(255),
    municipio VARCHAR(255),
    ano INT,
    total_matriculas NUMERIC
);
"

echo "Carregando dados no banco de dados..."
for file in "$OUTPUT_DIR"/*.csv; do
    if [[ -f "$file" ]]; then
        echo "Carregando $file..."
        psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\COPY $TABLE_NAME(ano, municipio, total_matriculas, estado) FROM '$file' DELIMITER ',' CSV HEADER;"
    fi
done

echo "Processamento e carregamento concluídos!"
