CREATE DATABASE IF NOT EXISTS clinica_veterinaria;
USE clinica_veterinaria;

CREATE TABLE IF NOT EXISTS persona (
    documento VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    telefono VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS mascota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    especie VARCHAR(50),
    raza VARCHAR(50),
    documento_dueno VARCHAR(20),
    FOREIGN KEY (documento_dueno) REFERENCES persona(documento)
);
