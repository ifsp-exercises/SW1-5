CREATE DATABASE IF NOT EXISTS gerenciador_produtos;

USE gerenciador_produtos;

CREATE TABLE IF NOT EXISTS produtos (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  unidadeCompra INT NOT NULL,
  qtdPrevistoMes DECIMAL(8, 2) NOT NULL,
  precoMaxComprado DECIMAL(8, 2) NOT NULL
);
