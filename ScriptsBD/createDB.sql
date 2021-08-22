-- Implementa PostGis
CREATE EXTENSION postgis;

-- Tabla Estado
CREATE TABLE Estado(
    ID_Estado INTEGER PRIMARY KEY,
    nombreEstado VARCHAR(64)
);

-- Tabla Institucion
CREATE TABLE Institucion(
    ID_Institucion INTEGER PRIMARY KEY,
    nombreInstitucion VARCHAR(128),
    coordinador VARCHAR(64)
);

-- Tabla Emergencia
CREATE TABLE Emergencia(
    ID_Emergencia INTEGER PRIMARY KEY,
    nombreEmergencia VARCHAR(64),
    requerimientos VARCHAR(64),
    latitud_Eme FLOAT,
    longitud_Eme FLOAT,
	ID_Institucion INTEGER,
    ID_Estado INTEGER,
    FOREIGN KEY (ID_Institucion) REFERENCES Institucion (ID_Institucion),
    FOREIGN KEY (ID_Estado) REFERENCES Estado (ID_Estado)
);

-- Tabla Tarea
CREATE TABLE Tarea(
    ID_Tarea INTEGER PRIMARY KEY,
    nombreTarea VARCHAR(64),
    detalle VARCHAR(128),
    vol_requeridos INTEGER,
	ID_Estado INTEGER,
    ID_Emergencia INTEGER,
    FOREIGN KEY (ID_Estado) REFERENCES Estado (ID_Estado),
    FOREIGN KEY (ID_Emergencia) REFERENCES Emergencia (ID_Emergencia)
);

-- Tabla Voluntario
CREATE TABLE Voluntario(
    ID_Voluntario INTEGER PRIMARY KEY,
    nombreVoluntario VARCHAR(64),
    Flg_participa VARCHAR(64),
    ubicacion GEOMETRY(POINT),
    borrado INTEGER,
    ID_Tarea INTEGER,
    FOREIGN KEY (ID_Tarea) REFERENCES Tarea (ID_Tarea)
);

-- Tabla Habilidad
CREATE TABLE Habilidad(
    ID_Habilidad INTEGER PRIMARY KEY,
    nombreHabilidad VARCHAR(64),
    tipo VARCHAR(64)
);

-- Tabla Ranking
CREATE TABLE Ranking(
    ID_Ranking INTEGER PRIMARY KEY,
    nombreRanking VARCHAR(64),
	ID_Voluntario INTEGER,
	ID_Tarea INTEGER,
    FOREIGN KEY (ID_Voluntario) REFERENCES Voluntario (ID_Voluntario),
    FOREIGN KEY (ID_Tarea) REFERENCES Tarea (ID_Tarea)
);

-- Tabla Vol_Habilidad
CREATE TABLE Vol_Habilidad(
    ID_Vol_Habilidad INTEGER PRIMARY KEY,
	ID_Voluntario INTEGER,
	ID_Habilidad INTEGER,
    FOREIGN KEY (ID_Voluntario) REFERENCES Voluntario (ID_Voluntario),
    FOREIGN KEY (ID_Habilidad) REFERENCES Habilidad (ID_Habilidad)
);

-- Tabla Eme_Habilidad
CREATE TABLE Eme_Habilidad(
    ID_Eme_Habilidad INTEGER PRIMARY KEY,
	ID_Emergencia INTEGER,
	ID_Habilidad INTEGER,
    FOREIGN KEY (ID_Emergencia) REFERENCES Emergencia (ID_Emergencia),
    FOREIGN KEY (ID_Habilidad) REFERENCES Habilidad (ID_Habilidad)
);

-- Tabla Tarea_Habilidad
CREATE TABLE Tarea_Habilidad(
    ID_Tarea_Habilidad INTEGER PRIMARY KEY,
	ID_Tarea INTEGER,
	ID_Habilidad INTEGER,
    FOREIGN KEY (ID_Tarea) REFERENCES Tarea (ID_Tarea),
    FOREIGN KEY (ID_Habilidad) REFERENCES Habilidad (ID_Habilidad)
);