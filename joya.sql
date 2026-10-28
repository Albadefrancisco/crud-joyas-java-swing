CREATE DATABASE IF NOT EXISTS programacion;
CREATE USER IF NOT EXISTS 'programacion'@'localhost' IDENTIFIED BY 'programacion';
ALTER USER 'programacion'@'localhost' IDENTIFIED BY 'programacion';
GRANT ALL PRIVILEGES ON programacion.* TO 'programacion'@'localhost';
FLUSH PRIVILEGES;

USE programacion;

CREATE TABLE joya (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    TIPO VARCHAR(50),
    MATERIAL VARCHAR(50),
    ALEACION VARCHAR(50),
    PRECIO DOUBLE,
    MEDIDA DOUBLE
);
ALTER TABLE joya ADD CONSTRAINT unique_joya 
UNIQUE (TIPO, MATERIAL, ALEACION, MEDIDA);

INSERT INTO joya (tipo, material, aleacion, precio, medida)
VALUES 
('Anillo', 'Oro', '18k', 199.99, 12),
('Collar', 'Plata', '925', 89.50, 45),
('Pulsera', 'Oro', '925', 87.50, 85),
('Brazalete', 'Oro', '925', 86.50, 75),
('Cholker', 'Oro', '925', 88.50, 85),
('Collar', 'Oro', '925', 56.8, 95),
('Pulsera', 'Oro', '925', 58.8, 65),
('Anillo', 'Plata', '925', 57.8, 83),
('Anillo', 'Oro', '24k', 55.8, 82);
