-- =====================
-- TABLA: empresa
-- =====================
INSERT INTO empresa (nombre) VALUES ('SuperStar');              -- id = 1
INSERT INTO empresa (nombre) VALUES ('Express Norte');          -- id = 2
INSERT INTO empresa (nombre) VALUES ('Transporte Morropón');    -- id = 3
INSERT INTO empresa (nombre) VALUES ('Sol del Sur');            -- id = 4
INSERT INTO empresa (nombre) VALUES ('San Miguel');             -- id = 5
INSERT INTO empresa (nombre) VALUES ('Rápidos del Norte');      -- id = 6

-- =====================
-- EMPRESA: SuperStar (1 ruta)
-- =====================
INSERT INTO rutas (nombre, empresa_id) VALUES ('ruta', 1); -- id = 1

INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.19449, -80.63282, 1, 1),
(-5.19300, -80.63000, 2, 1),
(-5.19425, -80.62843, 3, 1);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(1, 'Plaza de Armas', -5.1930, -80.6320),
(1, 'Catedral', -5.1925, -80.6310),
(1, 'Municipalidad', -5.19425, -80.62843);

-- =====================
-- EMPRESA: Express Norte (2 rutas)
-- =====================
INSERT INTO rutas (nombre, empresa_id) VALUES ('Ruta Universidad', 2); -- id = 2
INSERT INTO rutas (nombre, empresa_id) VALUES ('Ruta Hospital', 2);    -- id = 3

-- Ruta 2
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.19000, -80.62800, 1, 2),
(-5.19150, -80.62950, 2, 2),
(-5.19449, -80.63282, 3, 2);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(2, 'Universidad', -5.2000, -80.6400),
(2, 'Estadio', -5.2015, -80.6415);

-- Ruta 3
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.20000, -80.62000, 1, 3),
(-5.20200, -80.62300, 2, 3);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(3, 'Hospital Regional', -5.2030, -80.6250),
(3, 'Mercado Modelo', -5.2040, -80.6270);

-- =====================
-- EMPRESA: Transporte Morropón (2 rutas)
-- =====================
INSERT INTO rutas (nombre, empresa_id) VALUES ('Morropón Norte', 3);  -- id = 4
INSERT INTO rutas (nombre, empresa_id) VALUES ('Morropón Centro', 3); -- id = 5

-- Ruta 4
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.18000, -80.62000, 1, 4),
(-5.18150, -80.62150, 2, 4);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(4, 'Plaza Morropón', -5.1820, -80.6220),
(4, 'Colegio San Juan', -5.1830, -80.6230);

-- Ruta 5
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.18400, -80.62400, 1, 5),
(-5.18500, -80.62550, 2, 5);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(5, 'Hospital Morropón', -5.1860, -80.6260),
(5, 'Mercado Morropón', -5.1870, -80.6270);

-- =====================
-- EMPRESA: Sol del Sur (2 rutas)
-- =====================
INSERT INTO rutas (nombre, empresa_id) VALUES ('Sol Este', 4);  -- id = 6
INSERT INTO rutas (nombre, empresa_id) VALUES ('Sol Oeste', 4); -- id = 7

-- Ruta 6
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.19000, -80.61000, 1, 6),
(-5.19100, -80.61150, 2, 6);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(6, 'Paradero Sol 1', -5.1920, -80.6120),
(6, 'Paradero Sol 2', -5.1930, -80.6130);

-- Ruta 7
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.19400, -80.61400, 1, 7),
(-5.19500, -80.61550, 2, 7);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(7, 'Paradero Sol 3', -5.1960, -80.6160),
(7, 'Paradero Sol 4', -5.1970, -80.6170);

-- =====================
-- EMPRESA: San Miguel (2 rutas)
-- =====================
INSERT INTO rutas (nombre, empresa_id) VALUES ('Miguel Norte', 5);  -- id = 8
INSERT INTO rutas (nombre, empresa_id) VALUES ('Miguel Sur', 5);    -- id = 9

-- Ruta 8
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.17000, -80.60000, 1, 8),
(-5.17100, -80.60150, 2, 8);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(8, 'Paradero Norte 1', -5.1720, -80.6020),
(8, 'Paradero Norte 2', -5.1730, -80.6030);

-- Ruta 9
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.17400, -80.60400, 1, 9),
(-5.17500, -80.60550, 2, 9);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(9, 'Paradero Sur 1', -5.1760, -80.6060),
(9, 'Paradero Sur 2', -5.1770, -80.6070);

-- =====================
-- EMPRESA: Rápidos del Norte (2 rutas)
-- =====================
INSERT INTO rutas (nombre, empresa_id) VALUES ('Ruta Rápida A', 6); -- id = 10
INSERT INTO rutas (nombre, empresa_id) VALUES ('Ruta Rápida B', 6); -- id = 11

-- Ruta 10
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.16000, -80.59000, 1, 10),
(-5.16100, -80.59150, 2, 10);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(10, 'Paradero A1', -5.1620, -80.5920),
(10, 'Paradero A2', -5.1630, -80.5930);

-- Ruta 11
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.16400, -80.59400, 1, 11),
(-5.16500, -80.59550, 2, 11);

INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(11, 'Paradero B1', -5.1660, -80.5960),
(11, 'Paradero B2', -5.1670, -80.5970);

-- =====================
-- INFORMACIÓN POR EMPRESA
-- =====================

INSERT INTO informacion (
    numero_unidades,
    duracion_recorrido,
    longitud_recorrido,
    inicio_servicio_lunes_viernes,
    inicio_servicio_sabado,
    inicio_servicio_domingo,
    fin_servicio_lunes_viernes,
    fin_servicio_sabado,
    fin_servicio_domingo,
    mensaje,
    empresa_id
) VALUES
-- Empresa 1: SuperStar
(5, '1h 15min', 12.5, '06:00', '07:00', '08:00', '22:00', '21:00', '20:00',
 'La ubicación de las unidades de los buses es una aproximación del lugar geográfico por donde circulan los camiones.', 1),

-- Empresa 2: Express Norte
(10, '1h 40min', 18.2, '05:30', '06:30', '07:30', '23:00', '22:00', '21:00',
 'La ubicación de las unidades de los buses es una aproximación del lugar geográfico por donde circulan los camiones.', 2),

-- Empresa 3: Transporte Morropón
(7, '1h 10min', 10.8, '06:00', '07:00', '08:00', '21:30', '20:30', '20:00',
 'La ubicación de las unidades de los buses es una aproximación del lugar geográfico por donde circulan los camiones.', 3),

-- Empresa 4: Sol del Sur
(8, '1h 30min', 13.4, '06:15', '07:15', '08:15', '22:15', '21:15', '20:15',
 'La ubicación de las unidades de los buses es una aproximación del lugar geográfico por donde circulan los camiones.', 4),

-- Empresa 5: San Miguel
(6, '1h 20min', 11.7, '05:45', '06:45', '07:45', '22:45', '21:45', '20:45',
 'La ubicación de las unidades de los buses es una aproximación del lugar geográfico por donde circulan los camiones.', 5),

-- Empresa 6: Rápidos del Norte
(12, '1h 50min', 19.9, '05:00', '06:00', '07:00', '23:30', '22:30', '21:30',
 'La ubicación de las unidades de los buses es una aproximación del lugar geográfico por donde circulan los camiones.', 6);
