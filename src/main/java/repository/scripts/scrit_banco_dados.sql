-- Autor: Rodolfo Santana
-- Generation Time: Aug 17, 2017 at 06:21 PM

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--PARA LIMPEZA DAS TABELAS
-- DELETE FROM `Configuracao`
-- DELETE FROM `PessoaFoto`;
-- DELETE FROM `Usuario`;
-- DELETE FROM `Pessoa`;
-- 
DROP TABLE `Configuracao`;
DROP TABLE `PessoaFoto`;
DROP TABLE `Usuario`;
DROP TABLE `Pessoa`;
-- --
-- Table structure for table `Pessoa`
--
CREATE TABLE IF NOT EXISTS `Pessoa` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `primeiroNome` varchar(30) NOT NULL,
    `segundoNome` varchar(50) NOT NULL,
    `matricula` varchar(30) NOT NULL,
    `ddd` varchar(03) DEFAULT NULL,
    `foneCelular` varchar(10) DEFAULT NULL,   
    `email` varchar(200) NOT NULL,
    `biografia` varchar(500) DEFAULT NULL,  
    `instituicao` varchar(200) DEFAULT NULL,
    `pais` varchar(200) DEFAULT NULL,
    `estado` varchar(200) DEFAULT NULL,
    `cidade` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Pessoa_PrimeiroNome` (`primeiroNome`),
  KEY `Pessoa_SegundoNome` (`SegundoNome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `Pessoa`
  ADD CONSTRAINT Pessoa_Matricula UNIQUE (matricula),
  ADD CONSTRAINT Pessoa_Email UNIQUE (email);

-- --------------------------------------------------------s
--
-- Table structure for table `Usuario`
--
CREATE TABLE IF NOT EXISTS `Usuario` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `senha` varchar(30) DEFAULT NULL,
    `token` varchar(500) DEFAULT NULL,
    `bloqueado` tinyint(1) DEFAULT 1,
    `dtDesbloqueio` varchar(10) DEFAULT NULL,
    `dtUltAcesso` varchar(20) DEFaULT NULL,
    `id_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`id`)
 )ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `Usuario`
  ADD CONSTRAINT `Usuario_Pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `Pessoa` (`id`)  ON DELETE CASCADE;

-- --
-- Table structure for table `PessoaFoto`
--
CREATE TABLE IF NOT EXISTS `PessoaFoto` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `pessoa_id` int(11) NOT NULL,
    `foto` LONGBLOB DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `PessoaFoto`
ADD CONSTRAINT `PessoFoto_Pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `Pessoa` (`id`) ON DELETE CASCADE;

-- --
-- Table structure for table `Configuracao`
--
CREATE TABLE IF NOT EXISTS `Configuracao` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `horaEntrada` int(11) NOT NULL,
    `horaSaida` int(11) NOT NULL,
    `intervalo` int(11) NOT NULL,
    `toleranciaDia` int(11) NOT NULL,
    `cargaHorariaDia` int(11) NOT NULL,
    `id_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `Configuracao`
ADD CONSTRAINT `Configuracao_Pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `Pessoa` (`id`) ON DELETE CASCADE;




/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

/*
-- --------------------------------------------------------
--
-- Table structure for table `Tipo Documento`
--
CREATE TABLE IF NOT EXISTS `TipoDocumento` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- --------------------------------------------------------
--
-- Table structure for table `Documento`
--
CREATE TABLE IF NOT EXISTS `Documento` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `titulo` varchar(250) NOT NULL,
    `resumo` varchar(600) DEFAULT NULL,
    `palavrasChave` varchar(200) DEFAULT NULL,
    `tipoDocumento` int(11) NOT NULL,
    `criadoPor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Documento_Titulo`(titulo),
  KEY `Documento_CriadoPor`(criadoPor),
  KEY `Documento_PalavraChave`(palavraschave)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `Documento`
  ADD CONSTRAINT `Documento_Pessoa` FOREIGN KEY (`criadoPor`) REFERENCES `Pessoa` (`id`);

ALTER TABLE `Documento`
  ADD CONSTRAINT `Documento_TipoDocumento` FOREIGN KEY (`tipoDocumento`) REFERENCES `TipoDocumento` (`id`);

-- --------------------------------------------------------
--
-- Table structure for table `Documentos_Pessoas_Favoritos`
--
CREATE TABLE IF NOT EXISTS `DocumentosPessoasFavoritos` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_documento` int(11) NOT NULL,
    `id_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `DocumentosPessoasFavoritos`
  ADD CONSTRAINT `DocumentosPessoasFavoritos_Pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `Pessoa` (`id`),
  ADD CONSTRAINT `DocumentosPessoasFavoritos_Documento` FOREIGN KEY (`id_documento`) REFERENCES `Documento` (`id`);
  
-- --------------------------------------------------------
--
-- Table structure for table `Documentos_Pessoas`
--
CREATE TABLE IF NOT EXISTS `DocumentosPessoas`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_pessoa` int(11) NOT NULL,
    `id_documento` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE `DocumentosPessoas_Documento_Pessoa`(id_pessoa, id_documento)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

ALTER TABLE `DocumentosPessoas`
  ADD CONSTRAINT `DocumentoPessoas_Autor` FOREIGN KEY (`id_pessoa`) REFERENCES `Pessoa` (`id`),
  ADD CONSTRAINT `DocumentoPessoas_Documento` FOREIGN KEY (`id_documento`) REFERENCES `Documento` (`id`);

-- --------------------------------------------------------
--
-- Table structure for table `Destino`
--
CREATE TABLE IF NOT EXISTS `Destino` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `descricao` varchar(100) NOT NULL,
    `classificacao` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE `Destino_Descricao`(descricao),
  KEY `Destino_classifacao`(classificacao)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- --------------------------------------------------------
--
-- Table structure for table `FilaSubmissao`
--
CREATE TABLE IF NOT EXISTS `FilaSubmissao` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `situacao` int(11) NOT NULL,
    `id_documento` int(11) NOT NULL,
    `versao` varchar(10) NOT NULL,
    `dtLimiteSubmissao` varchar(10) NOT NULL,
    `destino` int(11) NOT NULL,
    `idioma` varchar(100) DEFAULT NULL,   
    `observacao` varchar(250) DEFAULT NULL,
    `dtPublicacao` varchar(10) DEFAULT NULL,
    `dtUltAtualizacao` varchar(10) DEFAULT NULL,
    `horaUltAtualizacao` varchar(10) DEFAULT NULL,
    `criadoPor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FilaSubmissao_Situacao`(situacao),
  KEY `FilaSubmissao_DtPublicacao`(dtPublicacao),
  KEY `FilaSubmissao_DtLimite`(dtLimiteSubmissao),
  KEY `FilaSubmissao_CriadoPor`(criadoPor)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `FilaSubmissao`
  ADD CONSTRAINT `FilaSubmissao_Pessoa`    FOREIGN KEY (`criadoPor`) REFERENCES `Pessoa` (`id`),
  ADD CONSTRAINT `FilaSubmissao_Destino`   FOREIGN KEY (`destino`)   REFERENCES `Destino` (`id`),
  ADD CONSTRAINT `FilaSubmissao_Documento` FOREIGN KEY (`id_documento`)   REFERENCES `Documento` (`id`);

-- --------------------------------------------------------
--
-- Table structure for table `HistoricoFilaSubmissao`
--
CREATE TABLE IF NOT EXISTS `HistoricoFilaSubmissao` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_filaSubmissao` int(11) NOT NULL,
    `situacao` int(11) NOT NULL,
    `versao` varchar(10) NOT NULL,
    `dtAtualizacao` varchar(10) NOT NULL,
    `horaAtualizacao` varchar(10) NOT NULL,
    `comentario` varchar(500) DEFAULT NULL,
    `criadoPor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `HistoricoFilaSubmissao_DtHrAtualizao`(dtAtualizacao, horaAtualizacao)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `HistoricoFilaSubmissao`
  ADD CONSTRAINT `HistoricoFilaSubmissao_FilaSubmissao` FOREIGN KEY (`id_filaSubmissao`) REFERENCES `FilaSubmissao` (`id`),
  ADD CONSTRAINT `HistoricoFilaSubmissao_Pessoa` FOREIGN KEY (`criadoPor`) REFERENCES `Pessoa` (`id`) ON DELETE CASCADE;

-- --------------------------------------------------------s
--
-- Table structure for table `Notificação`
--
CREATE TABLE IF NOT EXISTS `Notificacao` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `dtCriacao` varchar(10) NOT NULL,
    `horaCriacao` varchar(10) NOT NULL,
    `status` int(11) NOT NULL,
    `conteudo` varchar(500) DEFAULT NULL,
    `documento` int(11) NOT NULL,
    `destinatario` int(11) NOT NULL,
  PRIMARY KEY (`id`)
 )ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `Notificacao`
  ADD CONSTRAINT `Notificacao_Pessoa` FOREIGN KEY (`destinatario`) REFERENCES `Pessoa` (`id`)  ON DELETE CASCADE,
  ADD CONSTRAINT `Notificacao_Documento` FOREIGN KEY (`documento`)   REFERENCES `Documento` (`id`);

*/