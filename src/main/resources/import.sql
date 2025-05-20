-- Inserindo categorias
INSERT INTO categoria (nome) VALUES ('Tecnologia');
INSERT INTO categoria (nome) VALUES ('Moda');
INSERT INTO categoria (nome) VALUES ('Alimentos');
INSERT INTO categoria (nome) VALUES ('Esportes');
INSERT INTO categoria (nome) VALUES ('Educação');

-- Inserindo subcategorias vinculadas às categorias
INSERT INTO subcategoria (nome, id_categoria) VALUES ('Celulares', 1);
INSERT INTO subcategoria (nome, id_categoria) VALUES ('Roupas Femininas', 2);
INSERT INTO subcategoria (nome, id_categoria) VALUES ('Bebidas', 3);
INSERT INTO subcategoria (nome, id_categoria) VALUES ('Academia', 4);
INSERT INTO subcategoria (nome, id_categoria) VALUES ('Cursos Online', 5);
