
-- =====================
-- TABLA: rutas
-- =====================
INSERT INTO rutas (nombre) VALUES ('SuperStar');
INSERT INTO rutas (nombre) VALUES ('Zona Norte');
INSERT INTO rutas (nombre) VALUES ('Zona Sur');

-- =====================
-- TABLA: coordenadas_ruta (Ruta 1: SuperStar)
-- =====================
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.19449, -80.63282, 1, 1),  -- Plaza de Armas
(-5.19300, -80.63000, 2, 1),  -- Catedral
(-5.194257875537605, -80.62843708257918, 3, 1);  -- Municipalidad

-- =====================
-- TABLA: paradero (Ruta 1: SuperStar)
-- =====================
INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(1, 'Paradero Plaza de Armas', -5.1930, -80.6320),
(1, 'Paradero Catedral', -5.1925, -80.6310),
(1, 'Paradero Municipalidad', -5.194257875537605, -80.62843708257918);

-- =====================
-- TABLA: coordenadas_ruta (Ruta 2: Zona Norte)
-- =====================
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.19000, -80.62800, 1, 2),
(-5.19150, -80.62950, 2, 2),
(-5.19449, -80.63282, 3, 2);

-- =====================
-- TABLA: paradero (Ruta 2: Zona Norte)
-- =====================
INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(2, 'Paradero Universidad', -5.2000, -80.6400),
(2, 'Paradero Hospital', -5.2015, -80.6415),
(2, 'Paradero UCV', -5.2030, -80.6430);

-- =====================
-- TABLA: coordenadas_ruta (Ruta 3: Zona Sur)
-- =====================
INSERT INTO coordenadas_ruta (latitud, longitud, orden, ruta_id) VALUES
(-5.1916959, -80.6647480, 1, 3),
(-5.19100, -80.63400, 2, 3),
(-5.18800, -80.63600, 3, 3);

-- =====================
-- TABLA: paradero (Ruta 3: Zona Sur)
-- =====================
INSERT INTO paradero (ruta_id, nombre, latitud, longitud) VALUES 
(3, 'Paradero Open Plaza', -5.2100, -80.6500),
(3, 'Paradero Avelino', -5.2115, -80.6515),
(3, 'Paradero Terminal', -5.2130, -80.6530);


