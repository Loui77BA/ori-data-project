DROP TABLE IF EXISTS matriculas;

CREATE TABLE matriculas (
    id SERIAL PRIMARY KEY,
    estado VARCHAR(50) NOT NULL,
    municipio VARCHAR(255) NOT NULL,
    ano INT NOT NULL,
    baixa_visao INT DEFAULT 0,
    cegueira INT DEFAULT 0,
    surdez INT DEFAULT 0,
    auditiva INT DEFAULT 0,
    surdocegueira INT DEFAULT 0,
    fisica INT DEFAULT 0,
    intelectual INT DEFAULT 0,
    multipla INT DEFAULT 0,
    transtorno_global INT DEFAULT 0,
    altas_habilidades INT DEFAULT 0
);
