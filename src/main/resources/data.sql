-- Inserir dados na tabela tb_cliente
INSERT INTO tb_cliente (cpf, nome) VALUES ('12345678901', 'João Silva');
INSERT INTO tb_cliente (cpf, nome) VALUES ('23456789012', 'Maria Oliveira');
INSERT INTO tb_cliente (cpf, nome) VALUES ('34567890123', 'Carlos Souza');

-- Inserir dados na tabela tb_endereco
INSERT INTO tb_endereco (endereco_id, cpf, cep, logadouro, complemento, bairro, cidade, uf) VALUES
(UUID(), '12345678901', '01001000', 'Praça da Sé', 'lado ímpar', 'Sé', 'São Paulo', 'SP'),
(UUID(), '23456789012', '20040002', 'Rua da Assembleia', 'sala 1001', 'Centro', 'Rio de Janeiro', 'RJ'),
(UUID(), '34567890123', '30140071', 'Avenida Afonso Pena', 'apto 101', 'Centro', 'Belo Horizonte', 'MG');
