CREATE DATABASE cesde_academic;
USE cesde_academic;

CREATE TABLE escuela (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL UNIQUE,
	creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE programa (
	id INT PRIMARY KEY AUTO_INCREMENT,
    escuela_id INT NOT NULL,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (escuela_id) REFERENCES escuela(id) ON UPDATE CASCADE
);

CREATE TABLE modulo (
	id INT PRIMARY KEY AUTO_INCREMENT,
    programa_id INT NOT NULL,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    tipo ENUM('MATERIA', 'CURSO', 'CATEDRA', 'SEMINARIO') NOT NULL,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (programa_id) REFERENCES programa(id) ON UPDATE CASCADE
);

CREATE TABLE semestre (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE usuario (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    tipo ENUM('ESTUDIANTE', 'DOCENTE', 'ADMINISTRATIVO', 'DIRECTIVO') NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO', 'SUSPENDIDO', 'ELIMINADO') NOT NULL DEFAULT 'ACTIVO',
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE grupo (
	id INT PRIMARY KEY AUTO_INCREMENT,
    programa_id INT NOT NULL,
    semestre_id INT NOT NULL,
    estado ENUM('ACTIVO', 'FINALIZADO') NOT NULL DEFAULT 'ACTIVO',
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (programa_id) REFERENCES programa(id) ON UPDATE CASCADE,
    FOREIGN KEY (semestre_id) REFERENCES semestre(id) ON UPDATE CASCADE
);

CREATE TABLE grupo_estudiante ( -- CONDICIÓN
	grupo_id INT NOT NULL,
    estudiante_id INT NOT NULL,
    PRIMARY KEY(grupo_id, estudiante_id),
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (grupo_id) REFERENCES grupo(id) ON UPDATE CASCADE,
    FOREIGN KEY (estudiante_id) REFERENCES usuario(id) ON UPDATE CASCADE
);

CREATE TABLE clase ( -- CONDICIÓN
	id INT PRIMARY KEY AUTO_INCREMENT,
    grupo_id INT NOT NULL,
    docente_id INT NOT NULL,
    modulo_id INT NOT NULL,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (grupo_id) REFERENCES grupo(id) ON UPDATE CASCADE,
    FOREIGN KEY (docente_id) REFERENCES usuario(id) ON UPDATE CASCADE,
    FOREIGN KEY (modulo_id) REFERENCES modulo(id) ON UPDATE CASCADE
);

CREATE TABLE actividad (
	id INT PRIMARY KEY AUTO_INCREMENT,
    clase_id INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    tipo ENUM('TAREA', 'TALLER', 'EXAMEN', 'PROYECTO') NOT NULL,
    fecha_entrega DATETIME NOT NULL,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (clase_id) REFERENCES clase(id) ON UPDATE CASCADE
);

CREATE TABLE calificacion ( -- CONDICION
	id INT PRIMARY KEY AUTO_INCREMENT,
    actividad_id INT NOT NULL,
    estudiante_id INT NOT NULL,
    fecha DATETIME NOT NULL,
    nota DECIMAL,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (actividad_id) REFERENCES actividad(id) ON UPDATE CASCADE,
    FOREIGN KEY (estudiante_id) REFERENCES usuario(id) ON UPDATE CASCADE
);

CREATE TABLE archivo (
	id INT PRIMARY KEY AUTO_INCREMENT,
    clase_id INT NOT NULL,
    usuario_id INT NOT NULL,
    nombre_archivo VARCHAR(255),
    ruta_archivo VARCHAR(500),
    fecha_subida DATETIME NOT NULL,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (clase_id) REFERENCES clase(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE asistencia ( -- CONDICION
	id INT PRIMARY KEY AUTO_INCREMENT,
    clase_id INT NOT NULL,
    estudiante_id INT NOT NULL,
    fecha DATETIME NOT NULL,
    estado ENUM('ASISTIO', 'INASISTENCIA', 'JUSTIFICADO') NOT NULL,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (clase_id) REFERENCES clase(id),
    FOREIGN KEY (estudiante_id) REFERENCES usuario(id)
);

CREATE TABLE anuncio (
	id INT PRIMARY KEY AUTO_INCREMENT,
    clase_id INT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT,
    fecha DATETIME NOT NULL,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (clase_id) REFERENCES clase(id)
);

CREATE TABLE reporte (
	id INT PRIMARY KEY AUTO_INCREMENT,
    clase_id INT NOT NULL,
    usuario_id INT NOT NULL,
    descripcion TEXT NOT NULL,
    fecha DATETIME NOT NULL,
    estado ENUM('PENDIENTE', 'EN PROGRESO', 'COMPLETADO', 'CANCELADO', 'ARCHIVADO', 'APROBADO', 'RECHAZADO') NOT NULL DEFAULT 'PENDIENTE',
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (clase_id) REFERENCES clase(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE dia (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre ENUM('LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO') UNIQUE
);

CREATE TABLE franja_horaria (
	id INT PRIMARY KEY AUTO_INCREMENT,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL
);

CREATE TABLE horario (
	id INT PRIMARY KEY AUTO_INCREMENT,
    dia_id INT NOT NULL,
    franja_id INT NOT NULL,
    FOREIGN KEY (dia_id) REFERENCES dia(id),
    FOREIGN KEY (franja_id) REFERENCES franja_horaria(id)
);

CREATE TABLE clase_horario (
	clase_id INT NOT NULL,
    horario_id INT NOT NULL,
    creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (clase_id, horario_id),
    FOREIGN KEY (clase_id) REFERENCES clase(id),
    FOREIGN KEY (horario_id) REFERENCES horario(id)
);

-- Aseguramos que el delimitador no interfiera con el código
DELIMITER $$

-- Trigger para validar tipo de usuario en la tabla `grupo_estudiante` (solo usuarios de tipo 'ESTUDIANTE')
CREATE TRIGGER verificar_tipo_usuario_grupo_estudiante
BEFORE INSERT ON grupo_estudiante
FOR EACH ROW
BEGIN
    -- Validar si el estudiante es un usuario de tipo 'ESTUDIANTE'
    DECLARE tipo_usuario VARCHAR(50);
    
    -- Obtener el tipo del usuario
    SELECT tipo INTO tipo_usuario
    FROM usuario
    WHERE id = NEW.estudiante_id;

    -- Verificar si el tipo del usuario coincide con 'ESTUDIANTE'
    IF tipo_usuario != 'ESTUDIANTE' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Solo usuarios de tipo ESTUDIANTE pueden ser agregados al grupo';
    END IF;
END$$

-- Trigger para validar tipo de usuario en la tabla `clase` (solo usuarios de tipo 'DOCENTE')
CREATE TRIGGER verificar_tipo_usuario_clase
BEFORE INSERT ON clase
FOR EACH ROW
BEGIN
    -- Validar si el docente es un usuario de tipo 'DOCENTE'
    DECLARE tipo_usuario VARCHAR(50);
    
    -- Obtener el tipo del usuario
    SELECT tipo INTO tipo_usuario
    FROM usuario
    WHERE id = NEW.docente_id;

    -- Verificar si el tipo del usuario coincide con 'DOCENTE'
    IF tipo_usuario != 'DOCENTE' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Solo usuarios de tipo DOCENTE pueden ser asignados a una clase';
    END IF;
END$$

-- Trigger para validar tipo de usuario en la tabla `calificacion` (solo usuarios de tipo 'ESTUDIANTE')
CREATE TRIGGER verificar_tipo_usuario_calificacion
BEFORE INSERT ON calificacion
FOR EACH ROW
BEGIN
    -- Validar si el estudiante es un usuario de tipo 'ESTUDIANTE'
    DECLARE tipo_usuario VARCHAR(50);
    
    -- Obtener el tipo del usuario
    SELECT tipo INTO tipo_usuario
    FROM usuario
    WHERE id = NEW.estudiante_id;

    -- Verificar si el tipo del usuario coincide con 'ESTUDIANTE'
    IF tipo_usuario != 'ESTUDIANTE' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Solo usuarios de tipo ESTUDIANTE pueden tener calificaciones';
    END IF;
END$$

-- Trigger para validar tipo de usuario en la tabla `asistencia` (solo usuarios de tipo 'ESTUDIANTE')
CREATE TRIGGER verificar_tipo_usuario_asistencia
BEFORE INSERT ON asistencia
FOR EACH ROW
BEGIN
    -- Validar si el estudiante es un usuario de tipo 'ESTUDIANTE'
    DECLARE tipo_usuario VARCHAR(50);
    
    -- Obtener el tipo del usuario
    SELECT tipo INTO tipo_usuario
    FROM usuario
    WHERE id = NEW.estudiante_id;

    -- Verificar si el tipo del usuario coincide con 'ESTUDIANTE'
    IF tipo_usuario != 'ESTUDIANTE' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Solo usuarios de tipo ESTUDIANTE pueden tener registros de asistencia';
    END IF;
END$$

-- Trigger para validar tipo de usuario en la tabla `reporte` (verificar que el usuario exista)
CREATE TRIGGER verificar_tipo_usuario_reporte
BEFORE INSERT ON reporte
FOR EACH ROW
BEGIN
    -- Verificar que el usuario existe en la tabla usuario
    DECLARE usuario_existe INT;

    -- Comprobar si el usuario existe
    SELECT COUNT(*) INTO usuario_existe
    FROM usuario
    WHERE id = NEW.usuario_id;

    -- Si el usuario no existe, lanzar un error
    IF usuario_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El usuario no existe en la base de datos';
    END IF;
END$$

-- Volver a cambiar el delimitador a ;
DELIMITER ;