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

-- Inserindo TipoAvaliador
INSERT INTO tipoAvaliador (descricao) 
VALUES 
    ('Professor'),
    ('aluno'),
    ('limpmeza'),
    ('coord'),
    ('Educação');

INSERT INTO avaliador 
    (nome, cpf, email, dataNacimento, serieAtual, id_tipo_avaliador) 
  VALUES
    ('leo Henrique Silva', '123.456.789-00', 'carlos.silva@example.com', '1990-05-15', '3º Ano B', 1),
    ('Leocs Oliveira', '987.654.321-00', 'marina.oliveira@example.com', '1987-08-22', '2º Ano A', 2),
    ('Lohanne', '111.222.333-44', 'joao.pedro@example.com', '1995-12-01', '1º Ano C', 1);


INSERT INTO usuario (perfil, login, senha)
VALUES 
    (2, 'leo', 'Ctis6fXh/t+HZ/rrRdKWoe+NFw5AQZTsz52vBgy8OHIAK3ZqtS/NIsgzcm4M5Gkrstv1hfjlAI9QRe/dAY+ijQ=='),
    (3, 'leon', 'Ctis6fXh/t+HZ/rrRdKWoe+NFw5AQZTsz52vBgy8OHIAK3ZqtS/NIsgzcm4M5Gkrstv1hfjlAI9QRe/dAY+ijQ=='),
    (1, 'lola', 'Ctis6fXh/t+HZ/rrRdKWoe+NFw5AQZTsz52vBgy8OHIAK3ZqtS/NIsgzcm4M5Gkrstv1hfjlAI9QRe/dAY+ijQ==');

INSERT INTO avaliador_usuario (id_avaliador, id_usuario)
VALUES 
    -- leo
    (1, 1),
    -- leocs
    (2, 2),
    -- batman
    (3, 3);
