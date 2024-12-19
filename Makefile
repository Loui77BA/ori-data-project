.PHONY: up build down clean test-all logs-app logs-db check-docker load-db

check-docker:
	@echo "Verificando se o Docker está em execução..."
	@systemctl is-active --quiet docker || (echo "Docker não está em execução. Iniciando o Docker..." && sudo systemctl start docker)

clean:
	@echo "Limpando contêineres, imagens e volumes órfãos..."
	-docker-compose down --remove-orphans || true
	-docker rm -f $(shell docker ps -aq) || true
	-docker rmi -f $(shell docker images -aq) || true
	-docker volume prune -f || true
	-docker network prune -f || true

up: check-docker
	@echo "Subindo os containers..."
	docker-compose up -d
	@echo "Aguardando PostgreSQL ficar pronto..."
	until docker exec postgres-container pg_isready -U myuser > /dev/null 2>&1; do sleep 1; done
	@echo "PostgreSQL está pronto. Iniciando aplicação Spring..."

build: clean check-docker
	@echo "Construindo e subindo os containers..."
	docker-compose up --build -d

down: check-docker
	@echo "Derrubando os containers..."
	docker-compose down --remove-orphans

load-db:
	@echo "Inicializando o banco de dados com init_db.sql..."
	docker exec -i postgres-container psql -U myuser -d mydb < scripts/init_db.sql
	@echo "Banco de dados inicializado com sucesso."
	@echo "Carregando dados dos arquivos CSV para o banco de dados..."
	docker exec -i postgres-container psql -U myuser -d mydb < scripts/load_csv.sql
	@echo "Dados carregados com sucesso."

test-all:
	@echo "Testando API..."
	curl -X GET "http://localhost:8080/api/matriculas" || echo "API não está acessível."

logs-app: check-docker
	@echo "Exibindo logs da aplicação Spring..."
	docker-compose logs -f spring-app

logs-db: check-docker
	@echo "Exibindo logs do banco de dados PostgreSQL..."
	docker-compose logs -f postgres-container
