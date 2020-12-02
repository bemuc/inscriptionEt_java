-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 02 déc. 2020 à 18:13
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetjava`
--

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `matricule` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `sexe` varchar(255) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `Lnaissance` varchar(255) NOT NULL,
  `Lresidence` varchar(255) NOT NULL,
  `academique` varchar(255) NOT NULL,
  `faculte` varchar(255) NOT NULL,
  `pere` varchar(255) NOT NULL,
  `mere` varchar(255) NOT NULL,
  `adresseparents` varchar(255) NOT NULL,
  `dateInsertion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `etat` int(11) NOT NULL DEFAULT '0',
  `iduser` int(11) NOT NULL,
  PRIMARY KEY (`matricule`),
  KEY `FK_agent` (`iduser`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`matricule`, `nom`, `prenom`, `date`, `sexe`, `foto`, `Lnaissance`, `Lresidence`, `academique`, `faculte`, `pere`, `mere`, `adresseparents`, `dateInsertion`, `etat`, `iduser`) VALUES
(3, 'MUCIRO', 'benito', '2020-03-30', 'Masculin', '', 'mutanaga', 'mutanaga', '2020 - 2021 ', 'BST', 'mutanaga', 'mutanaga', 'mutanaga', '2020-11-28 07:47:44', 0, 1),
(4, 'MUCIRO', 'beni', '2020-08-30', 'masculin', 'C:\\Users\\venom\\Documents\\image1.png', 'mutanga', 'mutanga', '2020 - 2021 ', 'BST', 'mutanga', 'mutanga', 'mutanga', '2020-11-28 08:08:38', 1, 0),
(5, 'OIUYTR', 'hgfds', '0220-08-02', 'feminin', 'C:\\Users\\venom\\Documents\\image1.png', 'g', 'hgfd', '2020 - 2021 ', 'BAM', 'uhgf', 'gd', 'gr', '2020-11-28 16:19:23', 1, 0),
(6, 'MUCIRO', 'bone', '2020-08-30', 'masculin', '', 'mutanga', 'mutanga', '2020 - 2021 ', 'BST', 'mutanga', 'mutanga', 'mutanga', '2020-11-29 09:32:26', 1, 0),
(7, 'OK', 'ok', '2020-06-30', 'masculin', 'C:\\Users\\venom\\Documents\\image2.png', 'ok', 'ok', '2020 - 2021 ', 'BST', 'ok', 'ok', 'ok', '2020-11-29 09:51:02', 1, 0),
(8, 'OK', 'ok', '2020-02-05', 'masculin', '', 'ok', 'ok', '2020 - 2021 ', 'BST', 'ok', 'ok', 'ok', '2020-11-29 09:53:08', 1, 0),
(9, 'OK', 'ok', '1990-10-02', 'Masculin', 'C:\\Users\\venom\\Documents\\image1.png', 'ok', 'ok', '2020 - 2021 ', 'BST', 'ok', 'ok', 'ok', '2020-11-29 10:15:38', 0, 1),
(10, 'MUCO', 'few', '2020-02-25', 'feminin', '', 'ddsdw', 'csc', '2020 - 2021 ', 'BAM', 'wwdw', 'dwdwd', 'wdwdfw', '2020-12-01 21:29:23', 0, 0),
(11, 'MUCO', 'few', '2020-02-25', 'feminin', '', 'ddsdw', 'csc', '2020 - 2021 ', 'BAM', 'wwdw', 'dwdwd', 'wdwdfw', '2020-12-01 21:29:27', 0, 0),
(12, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:10', 0, 0),
(13, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:32', 0, 0),
(14, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:33', 0, 0),
(15, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:33', 0, 0),
(16, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:34', 0, 0),
(17, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:34', 0, 0),
(18, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:34', 0, 0),
(19, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:35', 0, 0),
(20, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:35', 0, 0),
(21, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:35', 0, 0),
(22, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:35', 0, 0),
(23, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:36', 0, 0),
(24, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:36', 0, 0),
(25, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:37', 0, 0),
(26, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:37', 0, 0),
(27, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:38', 0, 0),
(28, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:38', 0, 0),
(29, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:39', 0, 0),
(30, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:39', 0, 0),
(31, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:39', 0, 0),
(32, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:39', 0, 0),
(33, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:40', 0, 0),
(34, 'JHGFD', 'jhgfd', '2020-02-02', 'feminin', '', 'hgfds', 'jhgfd', '2020 - 2021 ', 'BAM', 'hgfds', 'jhgfd', 'jhgfd', '2020-12-01 21:33:40', 0, 0),
(35, 'LKJHGFD', ';lkjhgf', '2020-02-02', 'feminin', '', 'lkjhgfd', 'kjhgf', '2020 - 2021 ', 'BAM', 'lkjhgf', 'kmjnhbgvf', 'lkjhgfd', '2020-12-01 21:46:26', 1, 1),
(36, 'S', 's', '2015-05-02', 'feminin', '', 's', 's', '2020 - 2021 ', 'BAM', 's', 's', 's', '2020-12-01 21:54:01', 0, 2),
(37, 'Srooro', 's', '2020-06-05', 'Masculin', '', 's', 's', '2020 - 2021 ', 'BAM', 's', 's', 's', '2020-12-01 21:55:36', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

DROP TABLE IF EXISTS `profil`;
CREATE TABLE IF NOT EXISTS `profil` (
  `idprofil` int(11) NOT NULL,
  `nomProfil` varchar(30) NOT NULL,
  PRIMARY KEY (`idprofil`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`idprofil`, `nomProfil`) VALUES
(1, 'Administrateur'),
(2, 'Assistant');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) DEFAULT NULL,
  `prenom` varchar(30) DEFAULT NULL,
  `Username` varchar(30) NOT NULL,
  `idprofil` int(11) NOT NULL,
  `motdepas` varchar(30) DEFAULT NULL,
  `tetat` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`iduser`),
  KEY `FK_profil` (`idprofil`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`iduser`, `nom`, `prenom`, `Username`, `idprofil`, `motdepas`, `tetat`) VALUES
(1, 'muciro', 'ben', 'bemuc', 1, '123', 1),
(2, 'muc', 'ben', 'mucbe', 2, '123', 1),
(3, 'NIYONKURU', 'emma', 'manu', 0, '321', 0),
(4, 'NIYONKURU', 'emma', 'manu', 0, '321', 0),
(5, 'NIYONKURU', 'emma', 'manu', 0, '321', 0),
(6, 'NIYO', 'emma', 'manu', 0, '123', 0),
(7, 'NIYO', 'emma', 'manu', 0, '123', 0),
(8, 'NIYO', 'emma', 'manu', 0, '123', 0),
(9, 'NIYO', 'emma', 'manu', 0, '123', 0),
(10, 'NIYO', 'emma', 'manu', 0, '123', 0),
(11, 'NIYO', 'emma', 'manu', 0, '123', 0),
(12, 'NIYO', 'emma', 'manu', 0, '123', 0),
(13, 'NIYO', 'emma', 'manu', 2, '123', 0),
(14, 'NIYO', 'emma', 'manu', 1, '123', 0),
(15, 'S', 's', 's', 2, 's', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
