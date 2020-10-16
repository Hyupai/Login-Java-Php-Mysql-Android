SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `tokens` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(8) NOT NULL,
  `StartDate` timestamp NULL DEFAULT NULL,
  `EndDate` timestamp NULL DEFAULT NULL,
  `UID` varchar(60) DEFAULT NULL,
  `Expiry` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tokens` (`Username`, `Password`, `StartDate`, `EndDate`, `UID`, `Expiry`) VALUES
('MrEoZ', 'mrz', NULL, NULL, NULL, 0);

ALTER TABLE `tokens`
  ADD UNIQUE KEY `Username` (`Username`,`Password`);
COMMIT;
