-- --------------------------------------------------------
-- Servidor:                     localhost
-- Versão do servidor:           5.7.12-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- Copiando dados para a tabela bancocentral.contas: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `contas` DISABLE KEYS */;
INSERT INTO `contas` (`codigo`, `codpessoa`, `numero`, `saldo`) VALUES
	(1, 1, 'PB00001', 1000),
	(2, 2, 'PB00002', 5000);
/*!40000 ALTER TABLE `contas` ENABLE KEYS */;

-- Copiando dados para a tabela bancocentral.pessoa: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` (`codigo`, `nome`, `datanascimento`, `sexo`) VALUES
	(1, 'Arthur', '1994-09-22 00:00:00', 'M'),
	(2, 'Larissa', '1994-02-25 00:00:00', 'F');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;

-- Copiando dados para a tabela bancocentral.pessoafisica: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `pessoafisica` DISABLE KEYS */;
INSERT INTO `pessoafisica` (`codpessoa`, `rg`, `cpf`) VALUES
	(1, '3195313', '08678438452');
/*!40000 ALTER TABLE `pessoafisica` ENABLE KEYS */;

-- Copiando dados para a tabela bancocentral.pessoajuridica: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `pessoajuridica` DISABLE KEYS */;
INSERT INTO `pessoajuridica` (`codpessoa`, `inscricaoestadual`, `cnpj`) VALUES
	(2, '3195313', '08678438452124');
/*!40000 ALTER TABLE `pessoajuridica` ENABLE KEYS */;

-- Copiando dados para a tabela bancocentral.usuario: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`codigo`, `id`, `senha`, `nivel`, `digital`) VALUES
	(1, 'Arthur', 'windows5', 1, NULL),
	(2, 'Larissa', 'lary7206', 1, NULL),
	(3, 'Lissia', '123456', 1, NULL),
	(4, 'Ze', '123456', 2, NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
