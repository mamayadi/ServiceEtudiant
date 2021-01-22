-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 22 jan. 2021 à 21:26
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `serviceetudiant`
--

-- --------------------------------------------------------

--
-- Structure de la table `etablissement`
--

CREATE TABLE `etablissement` (
  `id` int(11) NOT NULL,
  `libelle` varchar(20) NOT NULL,
  `adresse` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `libelle` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`id`, `libelle`) VALUES
(1, 'Fiche de notes'),
(2, 'Attestation de presence');

-- --------------------------------------------------------

--
-- Structure de la table `service_user`
--

CREATE TABLE `service_user` (
  `id` int(11) NOT NULL,
  `id_service` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_demande` varchar(20) NOT NULL,
  `etat_demande` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `service_user`
--

INSERT INTO `service_user` (`id`, `id_service`, `id_user`, `date_demande`, `etat_demande`) VALUES
(1, 1, 1, 'nharda el sob7', 'demande'),
(2, 2, 1, 'now', 'demande'),
(3, 2, 2, 'test', 'demande'),
(4, 2, 2, 'test', 'demande'),
(5, 1, 3, 'test', 'termine'),
(6, 1, 3, 'test', 'termine'),
(7, 1, 3, 'test', 'termine'),
(8, 1, 3, 'test', 'termine'),
(9, 1, 3, 'test', 'termine');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `iu` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `id_etablissement` int(11) NOT NULL,
  `login` varchar(20) NOT NULL,
  `passwd` varchar(20) NOT NULL,
  `autorisation` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `iu`, `nom`, `prenom`, `id_etablissement`, `login`, `passwd`, `autorisation`) VALUES
(1, 1111, 'mohamed', 'ayadi', 1, 'medayadi', '123', 'test'),
(2, 111, 'med', 'ayadi', 1, 'medayadi', '123', 'test'),
(3, 111, 'med', 'ayadi', 1, 'medayadi', '123', 'test'),
(4, 111, 'med', 'ayadi', 1, 'medayadi', '123', 'test'),
(5, 111, 'med', 'ayadi', 1, 'medayadi', '123', 'test'),
(6, 111, 'med', 'ayadi', 1, 'medayadi', '123', 'test');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `etablissement`
--
ALTER TABLE `etablissement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `service_user`
--
ALTER TABLE `service_user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `etablissement`
--
ALTER TABLE `etablissement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `service_user`
--
ALTER TABLE `service_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
