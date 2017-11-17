-- Autor: Rodolfo Santana
-- Generation Time: Aug 17, 2017 at 06:21 PM

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--PARA LIMPEZA DAS TABELAS
DELETE FROM `LancamentoPonto`;
DELETE FROM `LancamentoDivergencia`;
DELETE FROM `PeriodoSemPonto`;
DELETE FROM `RegistroPonto`;
DELETE FROM `TipoDivergencia`;
DELETE FROM `Frequencia`;
DELETE FROM `Configuracao`;
DELETE FROM `PessoaFoto`;
DELETE FROM `Usuario`;
DELETE FROM `Pessoa`;
-- 
DROP TABLE `LancamentoPonto`;
DROP TABLE `LancamentoDivergencia`;
DROP TABLE `PeriodoSemPonto`;
DROP TABLE `RegistroPonto`;
DROP TABLE `TipoDivergencia`; 
DROP TABLE `Frequencia`;
DROP TABLE `Configuracao`;
DROP TABLE `PessoaFoto`;
DROP TABLE `Usuario`;
DROP TABLE `Pessoa`;

-- --
-- Table structure for table `Frequencia`
--
CREATE TABLE IF NOT EXISTS `TipoDivergencia` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `descricao` varchar(100) NOT NULL,
    `tipoSaldo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

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

-- --
-- Table structure for table `Frequencia`
--
CREATE TABLE IF NOT EXISTS `Frequencia` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `dataInicio` varchar(10) NOT NULL,
    `dataTermino` varchar(10) NOT NULL,
    `descricao`varchar(60) NOT NULL,
    `id_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `Frequencia`
ADD CONSTRAINT `Frequencia_Pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `Pessoa` (`id`) ON DELETE CASCADE;

-- --
-- Table structure for table `RegistroPonto`
--
CREATE TABLE IF NOT EXISTS `RegistroPonto` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `dataRegistro` varchar(10) NOT NULL,
    `id_frequencia` int(11) NOT NULL,
    `id_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `RegistroPonto`
ADD CONSTRAINT `RegistroPonto_Frequencia` FOREIGN KEY (`id_frequencia`) REFERENCES `Frequencia` (`id`);

ALTER TABLE `RegistroPonto`
ADD CONSTRAINT `RegistroPonto_Pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `Pessoa` (`id`) ON DELETE CASCADE;

-- --
-- Table structure for table `PeriodoSemPonto`
--
CREATE TABLE IF NOT EXISTS `PeriodoSemPonto` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `data` varchar(10) NOT NULL,
    `descricao` varchar(70) NOT NULL,
    `id_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `PeriodoSemPonto`
ADD CONSTRAINT `PeriodoSemPonto_Pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `Pessoa` (`id`) ON DELETE CASCADE;

-- --
-- Table structure for table `LancamentoPonto`
--
CREATE TABLE IF NOT EXISTS `LancamentoPonto` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `horaLancamento` int(11) NOT NULL,
    `id_registroPonto` int(11) NOT NULL,
    `tipoRegistro` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `LancamentoPonto`
ADD CONSTRAINT `LancamentoPonto_RegsitroPonto` FOREIGN KEY (`id_registroPonto`) REFERENCES `RegistroPonto` (`id`) ON DELETE CASCADE;

-- --
-- Table structure for table `LancamentoDivergencia`
--
CREATE TABLE IF NOT EXISTS `LancamentoDivergencia` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_registroPonto` int(11) NOT NULL,
    `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE `LancamentoDivergencia`
ADD CONSTRAINT `LancamentoDivergencia_RegsitroPonto` FOREIGN KEY (`id_registroPonto`) REFERENCES `RegistroPonto` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
