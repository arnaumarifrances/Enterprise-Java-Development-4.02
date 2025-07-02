-- ==============================
-- TEACHERS
-- ==============================
INSERT INTO teacher (id, name, salary) VALUES
('t1', 'Manuel Rodríguez', 27000.00),
('t2', 'Isabel Fernández', 30000.00),
('t3', 'José Martínez', 32500.00),
('t4', 'Laura Gómez', 29500.00),
('t5', 'Antonio López', 38000.00),
('t6', 'Celia Hernández', 26000.00),
('t7', 'Francisco Ruiz', 34000.00),
('t8', 'María Navarro', 31000.00),
('t9', 'Jorge Torres', 28500.00),
('t10', 'Beatriz Morales', 25000.00);

-- ==============================
-- COURSES
-- ==============================
INSERT INTO course (id, name, price, money_earned, teacher_id) VALUES
('c1', 'Lengua Castellana y Literatura', 150.00, 0.00, 't1'),
('c2', 'Matemáticas', 160.00, 0.00, 't2'),
('c3', 'Ciencias Sociales', 140.00, 0.00, 't3'),
('c4', 'Física y Química', 170.00, 0.00, 't4'),
('c5', 'Educación Física', 130.00, 0.00, 't5'),
('c6', 'Tecnología', 155.00, 0.00, 't6'),
('c7', 'Biología y Geología', 165.00, 0.00, 't7'),
('c8', 'Música', 120.00, 0.00, 't8'),
('c9', 'Inglés', 150.00, 0.00, 't9'),
('c10', 'Francés', 145.00, 0.00, 't10'),
('c11', 'Historia', 140.00, 0.00, 't3'),
('c12', 'Plástica', 135.00, 0.00, 't8'),
('c13', 'Informática', 160.00, 0.00, 't6'),
('c14', 'Religión', 100.00, 0.00, 't1'),
('c15', 'Filosofía', 155.00, 0.00, 't7');

-- ==============================
--  STUDENTS
-- ==============================
INSERT INTO student (id, name, email, address, course_id) VALUES
('s1', 'Lucía Pérez', 'lucia.perez@example.com', 'Calle Atocha 12', 'c1'),
('s2', 'Marcos Díaz', 'marcos.diaz@example.com', 'Calle Mayor 45', 'c2'),
('s3', 'Sofía Romero', 'sofia.romero@example.com', 'Avda. América 10', 'c1'),
('s4', 'Javier Torres', 'javier.torres@example.com', 'Calle Alcalá 55', 'c3'),
('s5', 'Nuria Gómez', 'nuria.gomez@example.com', 'Gran Vía 22', 'c2'),
('s6', 'Andrés Moreno', 'andres.moreno@example.com', 'Calle Serrano 8', 'c4'),
('s7', 'Clara Ruiz', 'clara.ruiz@example.com', 'Calle Goya 3', 'c5'),
('s8', 'Daniela Ortega', 'daniela.ortega@example.com', 'Paseo del Prado 7', 'c6'),
('s9', 'Hugo Fernández', 'hugo.fernandez@example.com', 'Calle Velázquez 90', 'c4'),
('s10', 'Elena Sánchez', 'elena.sanchez@example.com', 'Calle Ibiza 14', 'c3'),
('s11', 'Álvaro Jiménez', 'alvaro.jimenez@example.com', 'Calle Ferraz 21', 'c2'),
('s12', 'Marta López', 'marta.lopez@example.com', 'Calle Princesa 2', 'c6'),
('s13', 'Pablo Navarro', 'pablo.navarro@example.com', 'Avda. Reina 40', 'c1'),
('s14', 'Ana Castro', 'ana.castro@example.com', 'Calle Bailén 5', 'c3'),
('s15', 'Lucas Molina', 'lucas.molina@example.com', 'Calle Zurita 17', 'c5'),
('s16', 'Irene Vargas', 'irene.vargas@example.com', 'Calle Luna 60', 'c6'),
('s17', 'Diego León', 'diego.leon@example.com', 'Calle Colón 31', 'c1'),
('s18', 'Sara Iglesias', 'sara.iglesias@example.com', 'Calle León 9', 'c2'),
('s19', 'Raúl Martín', 'raul.martin@example.com', 'Calle Barquillo 29', 'c4'),
('s20', 'Carmen Gil', 'carmen.gil@example.com', 'Calle Hortaleza 11', 'c5');