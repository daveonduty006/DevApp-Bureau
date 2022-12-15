CREATE DATABASE IF NOT EXISTS `bdhopital` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `bdhopital`;

CREATE TABLE `patients` (
  `idp` int(11) NOT NULL,
  `nom` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `daten` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sexe` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cp` varchar(7) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fumeur` int(1) NOT NULL,
  CONSTRAINT patients_idp_PK PRIMARY KEY(idp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `patients` VALUES(1, 'Léger', 'Émile', '1917-03-26', 'M', '6456 Rosemont, Montréal, Qc', 'H2P 8F5', 0);
INSERT INTO `patients` VALUES(2, 'Bernard', 'Marie', '1946-02-03', 'F', '6710-A Langelier, Montréal, Qc', 'H4G 6J3', 0);
INSERT INTO `patients` VALUES(3, 'Chartrand', 'Guy', '1959-04-05', 'M', '1234 de l\'Église, Laval, Qc', 'J6N 2K8', 1);
INSERT INTO `patients` VALUES(4, 'Côté', 'André', '1978-09-02', 'M', '2250 Boulevard Thiemens, St Laurent, Qc', 'H4L 3G8', 1);
INSERT INTO `patients` VALUES(5, 'Lavoie', 'Carole', '1973-11-04', 'F', '5101 Bannantyne, Montréal, Qc', 'H2L 3H9', 0);
INSERT INTO `patients` VALUES(6, 'Martin', 'Diane', '1965-12-02', 'F', '6456 Rosemont, Montréal, Qc', 'H1M 5V7', 1);
INSERT INTO `patients` VALUES(7, 'Lacroix', 'Pauline', '1956-01-25', 'F', '6450 de Lorimier, Montréal, Qc', 'H1M 4K5', 0);
INSERT INTO `patients` VALUES(8, 'St-Jean', 'Arthur', '1912-10-07', 'M', '4601 Lafontaine, Montréal, Qc', 'H3P 4F9', 1);
INSERT INTO `patients` VALUES(9, 'Béchard', 'Marc', '1980-08-08', 'M', '555 Pie IX, Montréal, Qc', 'H2J 4K4', 0);
INSERT INTO `patients` VALUES(10, 'Chartrand', 'Mario', '1947-07-23', 'M', '333 boul des Entreprises, Terrebonne, Qc', 'J6W 2P4', 0);
INSERT INTO `patients` VALUES(11, 'Ouimet', 'Louise', '1977-04-05', 'F', '6830 Molson, Québec, Qc', 'G2F 4N6', 1);
INSERT INTO `patients` VALUES(12, 'Després', 'Karine', '1983-12-07', 'F', '6246 McDonald, Montréal, Qc', 'H2M 1A0', 0);
INSERT INTO `patients` VALUES(13, 'Lévesque', 'Joanne', '1966-06-14', 'F', '2366 Harrigan, Toronto, On', 'M8V 3F6', 1);
INSERT INTO `patients` VALUES(14, 'Dupont', 'Albert', '1952-11-09', 'M', '2967 Dumont, Le Gardeur, Qc', 'J5Z 1R4', 0);
INSERT INTO `patients` VALUES(15, 'St-Louis', 'Luc', '1968-01-06', 'M', '3582 Lambert, Montréal-Nord, Qc', 'H1K 5G7', 0);
INSERT INTO `patients` VALUES(16, 'Deshaies', 'Marc-André', '2000-03-08', 'M', '26A 8e avenue, Deux-Montagnes, Qc', 'J7M 2P6', 1);
INSERT INTO `patients` VALUES(17, 'Laforêt', 'Claude', '1967-02-28', 'M', '1245 Langelier, Montréal, Qc', 'H1T 3S8', 0);
INSERT INTO `patients` VALUES(18, 'Paquin', 'Serge', '1948-02-29', 'M', '2212 Cuvillier, Montréal, Qc', 'H3P 4J2', 1);
INSERT INTO `patients` VALUES(19, 'Tremblay', 'Marise', '1912-10-07', 'F', '7772 Berri, Montréal, Qc', 'H2M 3Y4', 1);
INSERT INTO `patients` VALUES(20, 'Zalatan', 'Bruno', '1979-05-04', 'M', '4251 Verdun, Montréal, Qc', 'H2C 4Z8', 0);

ALTER TABLE `patients`
  MODIFY `idp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;
