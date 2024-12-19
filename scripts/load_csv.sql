COPY matriculas (
    estado,
    municipio,
    ano,
    baixa_visao,
    cegueira,
    surdez,
    auditiva,
    surdocegueira,
    fisica,
    intelectual,
    multipla,
    transtorno_global,
    altas_habilidades
)
FROM '/csv_files/matriculas_pcd_alagoas.csv' DELIMITER ',' CSV HEADER;

COPY matriculas (
    estado,
    municipio,
    ano,
    baixa_visao,
    cegueira,
    surdez,
    auditiva,
    surdocegueira,
    fisica,
    intelectual,
    multipla,
    transtorno_global,
    altas_habilidades
)
FROM '/csv_files/matriculas_pcd_bahia.csv' DELIMITER ',' CSV HEADER;

-- Repita para os demais arquivos
