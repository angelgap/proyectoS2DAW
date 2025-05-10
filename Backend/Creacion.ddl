-- Generado por Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   en:        2025-05-09 20:25:50 CEST
--   sitio:      Oracle Database 21c
--   tipo:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE comentario (
    id         NUMBER NOT NULL,
    texto      VARCHAR2(4000),
    imagen_id  NUMBER,
    diario_id  NUMBER NOT NULL,
    usuario_id NUMBER NOT NULL
);

ALTER TABLE comentario ADD CONSTRAINT comentario_pk PRIMARY KEY ( id );

CREATE TABLE diario (
    id         NUMBER NOT NULL,
    fecha      DATE NOT NULL,
    text       VARCHAR2(4000),
    usuario_id NUMBER NOT NULL,
    titulo     VARCHAR2(20)
);

ALTER TABLE diario ADD CONSTRAINT diario_pk PRIMARY KEY ( id );

CREATE TABLE es_amigo (
    usuario_id  NUMBER NOT NULL,
    usuario_id1 NUMBER NOT NULL
);

ALTER TABLE es_amigo ADD CONSTRAINT es_amigo_pk PRIMARY KEY ( usuario_id,
                                                              usuario_id1 );

CREATE TABLE imagen (
    id            NUMBER NOT NULL,
    url           VARCHAR2(4000),
    titulo        VARCHAR2(20),
    usuario_id    NUMBER NOT NULL,
    diario_id     NUMBER,
    comentario_id NUMBER
);

ALTER TABLE imagen ADD CONSTRAINT imagen_pk PRIMARY KEY ( id );

CREATE TABLE usuario (
    id         NUMBER NOT NULL,
    nombre     VARCHAR2(20),
    email      VARCHAR2(320) NOT NULL,
    contraseña VARCHAR2(4000) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

ALTER TABLE comentario
    ADD CONSTRAINT comentario_diario_fk FOREIGN KEY ( diario_id )
        REFERENCES diario ( id );

ALTER TABLE comentario
    ADD CONSTRAINT comentario_imagen_fk FOREIGN KEY ( imagen_id )
        REFERENCES imagen ( id );

ALTER TABLE comentario
    ADD CONSTRAINT comentario_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE diario
    ADD CONSTRAINT diario_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE es_amigo
    ADD CONSTRAINT es_amigo_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE es_amigo
    ADD CONSTRAINT es_amigo_usuario_fkv1 FOREIGN KEY ( usuario_id1 )
        REFERENCES usuario ( id );

ALTER TABLE imagen
    ADD CONSTRAINT imagen_comentario_fk FOREIGN KEY ( comentario_id )
        REFERENCES comentario ( id );

ALTER TABLE imagen
    ADD CONSTRAINT imagen_diario_fk FOREIGN KEY ( diario_id )
        REFERENCES diario ( id );

ALTER TABLE imagen
    ADD CONSTRAINT imagen_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             5
-- CREATE INDEX                             0
-- ALTER TABLE                             14
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
