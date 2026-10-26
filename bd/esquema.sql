-- =====================================================
-- Base de datos del proyecto de laboratorios (PostgreSQL)
-- Script ordenado: se puede ejecutar completo sobre una
-- base de datos vacia.
-- =====================================================

-- ---------- USUARIOS ----------

CREATE TABLE usuario (
	id_unico VARCHAR(10) PRIMARY KEY,
	nombre VARCHAR(100),
	correo VARCHAR(120) NOT NULL,
	tipo VARCHAR(30) NOT NULL
);

CREATE TABLE estudiante (
	id_unico VARCHAR(10) PRIMARY KEY,
	carrera VARCHAR(100) NOT NULL,
	semestre INT NOT NULL,
	FOREIGN KEY (id_unico) REFERENCES usuario(id_unico)
);

CREATE TABLE profesor (
	id_unico VARCHAR(10) PRIMARY KEY,
	materia VARCHAR(100) NOT NULL,
	semestre INT NOT NULL,
	FOREIGN KEY (id_unico) REFERENCES usuario(id_unico),
	CHECK (semestre BETWEEN 1 AND 10)
);

CREATE TABLE tecnico (
	id_unico VARCHAR(10) PRIMARY KEY,
	especialidad VARCHAR(100),
	FOREIGN KEY (id_unico) REFERENCES usuario(id_unico)
);

CREATE TABLE auxiliar_laboratorio (
	id_unico VARCHAR(10) PRIMARY KEY,
	turno VARCHAR(30) NOT NULL,
	FOREIGN KEY (id_unico) REFERENCES usuario(id_unico)
);

-- ---------- LABORATORIOS Y EQUIPOS ----------

CREATE TABLE laboratorio (
	codigo VARCHAR(10) PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	ubicación VARCHAR(150) NOT NULL,
	horario_apertura TIME NOT NULL,
	horario_cierre TIME NOT NULL,
	estado BOOLEAN NOT NULL DEFAULT TRUE,
	CHECK (horario_apertura < horario_cierre)
);

CREATE TABLE equipo (
	codigo VARCHAR(10) PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	tipo_equipo VARCHAR(30) NOT NULL,
	nivel_riesgo INT NOT NULL,
	estado VARCHAR(30) NOT NULL,
	laboratorio_codigo VARCHAR(10) NOT NULL,
	FOREIGN KEY (laboratorio_codigo) REFERENCES laboratorio(codigo),
	CHECK (nivel_riesgo BETWEEN 1 AND 5),
	CHECK (estado IN (
		'Disponible',
		'Ocupado',
		'Mantenimiento',
		'Fuera de servicio'
	)),
	CHECK (tipo_equipo IN (
		'Impresora3D',
		'Multimetro',
		'Cortadora Laser'
	))
);

CREATE TABLE impresora_3d (
	codigo_equipo VARCHAR(10) PRIMARY KEY,
	material_compatible VARCHAR(50) NOT NULL,
	FOREIGN KEY (codigo_equipo) REFERENCES equipo(codigo)
);

CREATE TABLE multimetro (
	codigo_equipo VARCHAR(10) PRIMARY KEY,
	rango_medicion VARCHAR(50) NOT NULL,
	precision_medicion DECIMAL(5,2) NOT NULL,
	FOREIGN KEY (codigo_equipo) REFERENCES equipo(codigo),
	CHECK (precision_medicion > 0)
);

CREATE TABLE cortadora_laser (
	codigo_equipo VARCHAR(10) PRIMARY KEY,
	potencia_laser DECIMAL(6,2) NOT NULL,
	FOREIGN KEY (codigo_equipo) REFERENCES equipo(codigo),
	CHECK (potencia_laser > 0)
);

-- ---------- RESERVAS Y SESIONES ----------

CREATE TABLE reserva (
	id_reserva INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	usuario_id VARCHAR(10) NOT NULL,
	equipo_codigo VARCHAR(10) NOT NULL,
	fecha DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	hora_final TIME NOT NULL,
	proposito VARCHAR(500) NOT NULL,
	estado VARCHAR(30) NOT NULL DEFAULT 'Pendiente',
	FOREIGN KEY (usuario_id) REFERENCES usuario(id_unico),
	FOREIGN KEY (equipo_codigo) REFERENCES equipo(codigo),
	CHECK (hora_inicio < hora_final),
	CONSTRAINT reserva_estado_check CHECK (estado IN (
		'Pendiente',
		'Aprobada',
		'Rechazada',
		'Cancelada',
		'Finalizada'
	))
);

CREATE TABLE notificacion (
	id_notificacion INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	usuario_id VARCHAR(10) NOT NULL,
	mensaje VARCHAR(500) NOT NULL,
	tipo VARCHAR(50) NOT NULL,
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	leida BOOLEAN NOT NULL DEFAULT FALSE,
	FOREIGN KEY (usuario_id) REFERENCES usuario(id_unico)
);

CREATE TABLE sesion_uso (
	id_sesion INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	reserva_id INT NOT NULL UNIQUE,
	hora_inicio TIME,
	hora_final TIME,
	observaciones VARCHAR(500),
	incidentes VARCHAR(500),
	cerrada BOOLEAN NOT NULL DEFAULT FALSE,
	FOREIGN KEY (reserva_id) REFERENCES reserva(id_reserva)
);

CREATE TABLE consumible_sesion (
	id_consumible INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	sesion_id INT NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	cantidad INT NOT NULL DEFAULT 1,
	FOREIGN KEY (sesion_id) REFERENCES sesion_uso(id_sesion),
	CHECK (cantidad > 0)
);

-- ---------- LISTA DE ESPERA ----------

CREATE TABLE lista_espera (
	id_espera INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	usuario_id VARCHAR(10) NOT NULL,
	equipo_codigo VARCHAR(10) NOT NULL,
	inicio_franja TIMESTAMP NOT NULL,
	fin_franja TIMESTAMP NOT NULL,
	estado VARCHAR(30) NOT NULL DEFAULT 'Pendiente',
	FOREIGN KEY (usuario_id) REFERENCES usuario(id_unico),
	FOREIGN KEY (equipo_codigo) REFERENCES equipo(codigo),
	CHECK (inicio_franja < fin_franja),
	CHECK (estado IN (
		'Pendiente',
		'Notificado',
		'Atendido',
		'Cancelado'
	))
);

CREATE UNIQUE INDEX uq_lista_espera_solicitud
ON lista_espera (usuario_id, equipo_codigo, inicio_franja, fin_franja);

-- ---------- MANTENIMIENTO ----------

CREATE TABLE ticket_mantenimiento (
	id_ticket INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	prioridad INT NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	equipo_codigo VARCHAR(10) NOT NULL,
	tecnico_id VARCHAR(10) NOT NULL,
	estado VARCHAR(30) NOT NULL DEFAULT 'Abierto',
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (equipo_codigo) REFERENCES equipo(codigo),
	FOREIGN KEY (tecnico_id) REFERENCES tecnico(id_unico),
	CHECK (prioridad BETWEEN 1 AND 5),
	CHECK (estado IN (
		'Abierto',
		'En proceso',
		'Resuelto',
		'Cerrado'
	))
);

CREATE TABLE historial_ticket (
	id_historial INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	ticket_id INT NOT NULL,
	descripcion_cambio VARCHAR(500) NOT NULL,
	fecha_cambio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (ticket_id) REFERENCES ticket_mantenimiento(id_ticket)
);

-- ---------- CERTIFICACIONES Y CAPACITACIONES ----------

CREATE TABLE certificacion_laser (
	id_certificacion INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	fecha_emision DATE NOT NULL,
	vigencia DATE NOT NULL,
	usuario_id VARCHAR(10) NOT NULL,
	equipo_codigo VARCHAR(10) NOT NULL,
	tipo_autorizado VARCHAR(100) NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES usuario(id_unico),
	FOREIGN KEY (equipo_codigo) REFERENCES equipo(codigo),
	CHECK (vigencia >= fecha_emision)
);

CREATE TABLE capacitacion_laser (
	id_capacitacion INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	fecha_emision DATE NOT NULL,
	vigencia DATE NOT NULL,
	usuario_id VARCHAR(10) NOT NULL,
	equipo_codigo VARCHAR(10) NOT NULL,
	tema_capacitacion VARCHAR(100) NOT NULL,
	horas_duracion INT NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES usuario(id_unico),
	FOREIGN KEY (equipo_codigo) REFERENCES equipo(codigo),
	CHECK (vigencia >= fecha_emision),
	CHECK (horas_duracion > 0)
);

-- =====================================================
-- DATOS DE EJEMPLO
-- =====================================================

INSERT INTO usuario (id_unico, nombre, correo, tipo)
VALUES
('U1', 'Natalia', 'natalia@eia.edu.co', 'estudiante'),
('U2', 'Carlos', 'carlos@eia.edu.co', 'profesor'),
('U3', NULL, 'carlos@eia.edu.co', 'tecnico'),
('U4', 'Pedro Diaz', 'pdiaz@eia.edu.co', 'auxiliar');

INSERT INTO estudiante (id_unico, carrera, semestre)
VALUES ('U1', 'Ingenieria de Sistemas', 5);

INSERT INTO profesor (id_unico, materia, semestre)
VALUES ('U2', 'Mecatronica', 4);

INSERT INTO tecnico (id_unico, especialidad)
VALUES ('U3', 'Mecánica');

INSERT INTO auxiliar_laboratorio (id_unico, turno)
VALUES ('U4', 'mañana');

INSERT INTO laboratorio (codigo, nombre, ubicación, horario_apertura, horario_cierre, estado)
VALUES ('LAB01', 'Laboratorio de fabricación', 'Bloque 5 piso 2', '07:00:00', '19:00:00', TRUE);

INSERT INTO equipo (codigo, nombre, tipo_equipo, nivel_riesgo, estado, laboratorio_codigo)
VALUES
('EQ001', 'Impresora Ender 3', 'Impresora3D', 1, 'Disponible', 'LAB01'),
('EQ002', 'Cortadora K40', 'Cortadora Laser', 3, 'Disponible', 'LAB01'),
('EQ003', 'Multimetro Fluke 117', 'Multimetro', 1, 'Disponible', 'LAB01');

INSERT INTO impresora_3d (codigo_equipo, material_compatible)
VALUES ('EQ001', 'PLA');

INSERT INTO cortadora_laser (codigo_equipo, potencia_laser)
VALUES ('EQ002', 40.00);

INSERT INTO multimetro (codigo_equipo, rango_medicion, precision_medicion)
VALUES ('EQ003', '0-600V', 0.50);

INSERT INTO reserva (usuario_id, equipo_codigo, fecha, hora_inicio, hora_final, proposito, estado)
VALUES ('U1', 'EQ002', '2024-05-10', '10:00:00', '12:00:00', 'Proyecto final de curso', 'Pendiente');

INSERT INTO sesion_uso (reserva_id)
SELECT r.id_reserva
FROM reserva r
WHERE r.usuario_id = 'U1'
  AND r.equipo_codigo = 'EQ002'
  AND r.fecha = '2024-05-10'
  AND r.hora_inicio = '10:00:00'
  AND r.hora_final = '12:00:00'
  AND NOT EXISTS (
      SELECT 1 FROM sesion_uso s WHERE s.reserva_id = r.id_reserva
  );

INSERT INTO lista_espera (usuario_id, equipo_codigo, inicio_franja, fin_franja, estado)
VALUES ('U2', 'EQ002', '2024-05-10 14:00:00', '2024-05-10 16:00:00', 'Pendiente');

INSERT INTO ticket_mantenimiento (prioridad, descripcion, equipo_codigo, tecnico_id, estado)
VALUES (2, 'Ruido anormal en el motor', 'EQ002', 'U3', 'Abierto');

INSERT INTO historial_ticket (ticket_id, descripcion_cambio)
SELECT t.id_ticket, 'Ticket creado'
FROM ticket_mantenimiento t
WHERE t.equipo_codigo = 'EQ002'
  AND t.tecnico_id = 'U3'
  AND t.descripcion = 'Ruido anormal en el motor'
  AND NOT EXISTS (
      SELECT 1 FROM historial_ticket h
      WHERE h.ticket_id = t.id_ticket
        AND h.descripcion_cambio = 'Ticket creado'
  );

INSERT INTO certificacion_laser (fecha_emision, vigencia, usuario_id, equipo_codigo, tipo_autorizado)
VALUES ('2024-01-10', '2025-01-10', 'U1', 'EQ002', 'Cortadora láser');

INSERT INTO capacitacion_laser (fecha_emision, vigencia, usuario_id, equipo_codigo, tema_capacitacion, horas_duracion)
VALUES ('2024-01-10', '2025-01-10', 'U1', 'EQ002', 'Uso seguro de cortadora láser', 4);

-- =====================================================
-- CONSULTA DE COMPROBACION (equipos con su laboratorio)
-- =====================================================

SELECT
	equipo.codigo,
	equipo.nombre,
	equipo.tipo_equipo,
	equipo.nivel_riesgo,
	equipo.estado,
	laboratorio.nombre AS nombre_laboratorio
FROM equipo
INNER JOIN laboratorio
	ON equipo.laboratorio_codigo = laboratorio.codigo;
