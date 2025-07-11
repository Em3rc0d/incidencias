CREATE TABLE "empresa" (
  "empresa_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "aseguradora_id" int,
  "nombre" varchar(100) UNIQUE NOT NULL,
  "ruc" varchar(50)
);

CREATE TABLE "aseguradora" (
  "aseguradora_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "nombre" varchar(100) NOT NULL,
  "correo_contacto" varchar(100),
  "telefono" varchar(20)
);

CREATE TABLE "vehiculo" (
  "vehiculo_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "empresa_id" int,
  "placa" varchar(10) UNIQUE NOT NULL,
  "marca" varchar(50),
  "modelo" varchar(50),
  "anio" int,
  "tipo" varchar(20),
  "estado" varchar(20) DEFAULT 'activo'
);

CREATE TABLE "usuario" (
  "usuario_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "empresa_id" int,
  "nombre" varchar(100) NOT NULL,
  "correo" varchar(100) UNIQUE NOT NULL,
  "contrasena" text NOT NULL,
  "rol" varchar(20)
);

CREATE TABLE "tipo_incidencia" (
  "tipo_incidencia_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "nombre" varchar(100) UNIQUE NOT NULL,
  "descripcion" text
);

CREATE TABLE "incidencia" (
  "incidencia_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "vehiculo_id" int,
  "usuario_id" int,
  "tipo_incidencia_id" int,
  "descripcion" text NOT NULL,
  "prioridad" varchar(10),
  "estado" varchar(20) DEFAULT 'abierta',
  "fecha_reporte" timestamp DEFAULT (CURRENT_TIMESTAMP),
  "latitud" decimal(10,8),
  "longitud" decimal(11,8)
);

CREATE TABLE "historial_incidencia" (
  "historial_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "incidencia_id" int,
  "usuario_id" int,
  "estado" varchar(30),
  "observacion" text,
  "fecha" timestamp DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "afectado" (
  "afectado_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "incidencia_id" int,
  "nombre" varchar(100),
  "documento_identidad" varchar(20),
  "tipo_tercero" varchar(30),
  "contacto" varchar(100),
  "descripcion_danio" text
);

CREATE TABLE "reporte_aseguradora" (
  "reporte_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "incidencia_id" int,
  "aseguradora_id" int,
  "fecha_envio" timestamp DEFAULT (CURRENT_TIMESTAMP),
  "estado_envio" varchar(20) DEFAULT 'pendiente',
  "asunto" varchar(150),
  "cuerpo" text,
  "observaciones" text
);

CREATE TABLE "evidencia" (
  "evidencia_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "incidencia_id" int,
  "url_archivo" text NOT NULL,
  "tipo_archivo" varchar(20),
  "fecha_subida" timestamp DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "historial_uso_vehiculo" (
  "historial_uso_id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "vehiculo_id" INT,
  "usuario_id" INT,
  "numero_vueltas" INT,
  "dia" VARCHAR(20),
  "hora" VARCHAR(5)
);

ALTER TABLE "empresa" ADD FOREIGN KEY ("aseguradora_id") REFERENCES "aseguradora" ("aseguradora_id");

ALTER TABLE "vehiculo" ADD FOREIGN KEY ("empresa_id") REFERENCES "empresa" ("empresa_id");

ALTER TABLE "usuario" ADD FOREIGN KEY ("empresa_id") REFERENCES "empresa" ("empresa_id");

ALTER TABLE "incidencia" ADD FOREIGN KEY ("vehiculo_id") REFERENCES "vehiculo" ("vehiculo_id");

ALTER TABLE "incidencia" ADD FOREIGN KEY ("usuario_id") REFERENCES "usuario" ("usuario_id");

ALTER TABLE "incidencia" ADD FOREIGN KEY ("tipo_incidencia_id") REFERENCES "tipo_incidencia" ("tipo_incidencia_id");

ALTER TABLE "historial_incidencia" ADD FOREIGN KEY ("incidencia_id") REFERENCES "incidencia" ("incidencia_id");

ALTER TABLE "historial_incidencia" ADD FOREIGN KEY ("usuario_id") REFERENCES "usuario" ("usuario_id");

ALTER TABLE "afectado" ADD FOREIGN KEY ("incidencia_id") REFERENCES "incidencia" ("incidencia_id");

ALTER TABLE "reporte_aseguradora" ADD FOREIGN KEY ("incidencia_id") REFERENCES "incidencia" ("incidencia_id");

ALTER TABLE "reporte_aseguradora" ADD FOREIGN KEY ("aseguradora_id") REFERENCES "aseguradora" ("aseguradora_id");

ALTER TABLE "evidencia" ADD FOREIGN KEY ("incidencia_id") REFERENCES "incidencia" ("incidencia_id");

ALTER TABLE "historial_uso_vehiculo" ADD FOREIGN KEY ("vehiculo_id") REFERENCES "vehiculo" ("vehiculo_id");

ALTER TABLE "historial_uso_vehiculo" ADD FOREIGN KEY ("usuario_id") REFERENCES "usuario"

INSERT INTO aseguradora (nombre, correo_contacto, telefono) VALUES
('Rimac Seguros', 'contacto@rimac.com', '014123456'),
('La Positiva', 'info@lapositiva.com.pe', '014987654');

INSERT INTO empresa (aseguradora_id, nombre, ruc) VALUES
(1, 'Transporte Lima S.A.', '20123456789'),
(2, 'Carga Express Perú', '20456789123');

INSERT INTO vehiculo (empresa_id, placa, marca, modelo, anio, tipo) VALUES
(1, 'ABC-123', 'Toyota', 'Hilux', 2018, 'Camioneta'),
(2, 'XYZ-789', 'Hyundai', 'H100', 2020, 'Furgón');

INSERT INTO usuario (empresa_id, nombre, correo, contrasena, rol) VALUES
(1, 'Juan Pérez', 'juan@translimasa.com', '123456', 'chofer'),
(2, 'Ana Torres', 'ana@cargaexpress.com', '123456', 'supervisor');

INSERT INTO tipo_incidencia (nombre, descripcion) VALUES
('Colisión', 'Impacto entre vehículos o con objeto fijo'),
('Falla mecánica', 'Problemas con el sistema mecánico del vehículo');

INSERT INTO incidencia (vehiculo_id, usuario_id, tipo_incidencia_id, descripcion, prioridad, latitud, longitud) VALUES
(1, 1, 1, 'Colisión leve en intersección Av. Javier Prado con Arenales', 'alta', -12.092334, -77.036590),
(2, 2, 2, 'Falla en el sistema de frenos en ruta a Callao', 'media', -12.058290, -77.118950);

INSERT INTO historial_incidencia (incidencia_id, usuario_id, estado, observacion) VALUES
(1, 1, 'en revisión', 'Esperando informe del taller'),
(2, 2, 'resuelto', 'Frenos reparados, vehículo operativo');

INSERT INTO afectado (incidencia_id, nombre, documento_identidad, tipo_tercero, contacto, descripcion_danio) VALUES
(1, 'Luis Gómez', '12345678', 'peatón', 'luisgomez@gmail.com', 'Golpe leve en pierna'),
(1, 'Soledad Ruiz', '87654321', 'conductor', 'sruiz@yahoo.com', 'Daños en parte frontal de auto');

INSERT INTO reporte_aseguradora (incidencia_id, aseguradora_id, asunto, cuerpo, observaciones) VALUES
(1, 1, 'Reporte de colisión leve', 'Adjuntamos detalles del accidente ocurrido el 08/06.', 'Se notificó a la policía de tránsito'),
(2, 2, 'Reporte de falla mecánica', 'Problema reportado en zona portuaria, sin daños a terceros', 'Sin intervención externa');

INSERT INTO evidencia (incidencia_id, url_archivo, tipo_archivo) VALUES
(1, 'https://miapp.com/evidencias/foto1.jpg', 'imagen'),
(2, 'https://miapp.com/evidencias/video1.mp4', 'video');

INSERT INTO historial_uso_vehiculo (vehiculo_id, usuario_id, numero_vueltas, dia, hora) VALUES
(1, 1, 5, 'Lunes', '08:00'),
(2, 2, 3, 'Martes', '07:00');

