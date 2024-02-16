-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 16, 2024 alle 13:13
-- Versione del server: 10.4.22-MariaDB
-- Versione PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring_prova_jpa`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `autorizzazioni`
--

CREATE TABLE `autorizzazioni` (
  `id` int(11) NOT NULL,
  `user` varchar(25) DEFAULT NULL,
  `ruolo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `autorizzazioni`
--

INSERT INTO `autorizzazioni` (`id`, `user`, `ruolo`) VALUES
(1, 'cliff', 'user'),
(2, 'cla', 'user'),
(4, 'cli', 'user'),
(5, 'clint', 'user'),
(6, 'dino', 'user'),
(7, 'danilo', 'user'),
(8, 'figaro', 'org'),
(9, 'eventour', 'org'),
(10, 'tarantis', 'org'),
(18, 'admin', 'admin'),
(22, 'pio', 'user'),
(27, 'gabri', 'user'),
(31, 'tizioso', 'user');

-- --------------------------------------------------------

--
-- Struttura della tabella `evento`
--

CREATE TABLE `evento` (
  `bigliettimax` int(11) NOT NULL,
  `bigliettirim` int(11) DEFAULT NULL,
  `datajpa` date DEFAULT NULL,
  `idevento` int(11) NOT NULL,
  `idtipologia` int(11) DEFAULT NULL,
  `prezzo` decimal(38,2) NOT NULL,
  `user` varchar(25) DEFAULT NULL,
  `nome` varchar(50) NOT NULL,
  `descbreve` varchar(255) DEFAULT NULL,
  `descestesa` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `luogo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `evento`
--

INSERT INTO `evento` (`bigliettimax`, `bigliettirim`, `datajpa`, `idevento`, `idtipologia`, `prezzo`, `user`, `nome`, `descbreve`, `descestesa`, `image`, `luogo`) VALUES
(300, 90, '2024-02-16', 1, 1, '44.50', 'eventour', 'Caparezza Tour', 'Nuovo tour italiano di Caparezza, il rapper italiano più apprezzato di sempre', 'Dopo due anni di attesa il rapper Caparezza torna in live con un imperdibile appuntamento, una grandissima occasione per rivivere lo show del più grande rapper italiano.', 'Caparezza.jpg', 'Foggia'),
(200, 190, '2024-03-01', 2, 1, '55.00', 'eventour', 'COSMO', 'Guardare fissi il palco non è obbligatorio.', 'Dopo aver fatto ballare i principali superclub di tutta Italia, conquistando 25.000 persone con sold out e raddoppi, COSMO non accenna a fermarsi e sarà protagonista di uno degli appuntamenti di Spilla 2018 con il suo nuovissimo singolo.', 'cosmo.png', 'Cortile Mole Vanvitelliana'),
(120, 90, '2024-02-28', 3, 1, '85.00', 'tarantis', 'Bruce Springsteen', 'Tour Europero di Bruce Springsteen', 'Concerto del Tour Europeo di Bruce Spingsteen che arriva per la prima volta in Ancona.Il concerto si svolgerà allo Stadio del Conero', 'Bruce.jpg', 'Ancona'),
(75, 23, '2024-03-15', 4, 2, '22.50', 'tarantis', 'Sei personaggi in cerca d\'autore', 'Opera di Luigi Pirandello', 'Opera teatrale complessa e bellissima che analizza il rapporto fra teatro e vita ed esplora nel profondo la follia dell\'esistenza', 'Pirandello.jpg', 'Milano'),
(180, 180, '2024-06-13', 5, 1, '18.50', 'eventour', 'Mannarino', 'Nuova tour italiana di Mannarino.', 'Concerto dell\'artista romano Alessandro Mannarino che torna nella sua città natale con il nuovo album Apriti Cielo.L\'artista Romano si esibirà durante la manifestazione Rock in Roma che si svolgerà allo Ippodromo Capannelle', 'Mannarino.jpg', 'Roma'),
(250, 250, '2024-04-11', 6, 1, '22.00', 'eventour', 'Liberato', 'Milano Liberata', 'Liberato in concerto a Milano', 'liberato.png', 'Milano'),
(120, 120, '2024-02-22', 7, 4, '20.00', 'figaro', 'Picasso', 'Esposizione dei lavori del maestro Picasso', 'I migliori lavori di Picasso saranno disponibili per un periodo limitato. Con alclune delle opere inedite per il palcoscenico italiano', 'picasso.jpg', 'Padova'),
(280, 276, '2024-02-04', 22, 1, '120.00', 'figaro', 'Il flauto magico', 'Splendido singspiel in due atti musicato da Wolfgang Amadeus Mozart', 'Il flauto magico è un Singspiel in due atti musicato da Wolfgang Amadeus Mozart nel 1791, su libretto di Emanuel Schikaneder e con il contributo di Karl Ludwig Giesecke.', 'Flautomagico.jpg', 'Milano'),
(230, 228, '2024-02-07', 32, 1, '55.00', 'figaro', 'Pink Floyd Legend', 'I Pink Floyd Legend sono oggi riconosciuti come il gruppo italiano che rende, in ogni loro spettacolo, il miglior omaggio alla musica dei Pink Floyd, grazie alla realizzazione di show perfetti.', 'SHINE Pink Floyd Moon, opera rock di Micha van Hoecke sulle musiche della leggendaria band inglese, suonate dal vivo dalla Pink FLoyd Legend. Ponte fra rock e classico, lucente scalinata dalla terra alla luna.', 'pink.jpg', 'Ancona'),
(122, 102, '2024-02-06', 33, 1, '50.00', 'figaro', 'Lez Zeppelin', 'Se cerchi una tribute band unica dei Led Zeppelin non farti scappare questo straordinario evento.', 'Le \"Lez Zeppelin\" è il primo quartetto completamente femminile, acclamate dalla critica. La mente del gruppo è Steph Paynes, prima chitarra e fondatrice del gruppo.', 'led.jpg', 'Milano');

-- --------------------------------------------------------

--
-- Struttura della tabella `faq`
--

CREATE TABLE `faq` (
  `idfaq` int(11) NOT NULL,
  `domanda` varchar(200) NOT NULL,
  `risposta` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `faq`
--

INSERT INTO `faq` (`idfaq`, `domanda`, `risposta`) VALUES
(2, 'Come faccio a modificare i miei dati di registrazione?', 'Dopo aver effettuato l\'accesso con Login e password, clicca sul tuo Profilo in alto a destra e  una volta che sei nel tuo profilo puoi modificare i tuoi dati di registrazione tranne il nome utente che è unico.'),
(3, 'Come procedo per registrarmi al sito?', 'Se non sei registrato a Ticket Love, clicca sul link SignUp(sempre visibile in alto a destra della pagina ). Una volta compilati e confermati tutti i dati, verrai reindirizzato alla home e potrai fare login attraverso il pulsante in alto a destra.'),
(8, 'domanda', 'risposta');

-- --------------------------------------------------------

--
-- Struttura della tabella `ordine`
--

CREATE TABLE `ordine` (
  `biglietti` int(11) NOT NULL,
  `idevento` int(11) DEFAULT NULL,
  `idordine` int(11) NOT NULL,
  `user` varchar(25) DEFAULT NULL,
  `totpagamento` double NOT NULL,
  `pagato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `ordine`
--

INSERT INTO `ordine` (`biglietti`, `idevento`, `idordine`, `user`, `totpagamento`, `pagato`) VALUES
(10, 3, 2, 'gabri', 850, 1),
(20, 3, 3, 'gabri', 1700, 1),
(200, 1, 4, 'cli', 8900, 1),
(10, 4, 8, 'cli', 225, 1),
(10, 4, 10, 'cli', 225, 1),
(19, 4, 11, 'cli', 427.5, 1),
(10, 33, 12, 'tizioso', 500, 1),
(13, 4, 13, 'cliff', 292.5, 1),
(10, 33, 14, 'gabri', 500, 1),
(10, 1, 15, 'gabri', 445, 1),
(2, 32, 16, 'gabri', 110, 1),
(4, 22, 17, 'gabri', 480, 1),
(10, 2, 18, 'gabri', 550, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `tipologia`
--

CREATE TABLE `tipologia` (
  `idtipologia` int(11) NOT NULL,
  `tipologia` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `tipologia`
--

INSERT INTO `tipologia` (`idtipologia`, `tipologia`) VALUES
(1, 'Concerto'),
(2, 'Convegno'),
(3, 'Piscina'),
(4, 'Mostra');

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `enabled` bit(1) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nomeorg` varchar(25) DEFAULT NULL,
  `user` varchar(25) NOT NULL,
  `mail` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`enabled`, `password`, `nomeorg`, `user`, `mail`) VALUES
(b'1', 'admin', NULL, 'admin', 'amministratoreloveticket@gmail.com'),
(b'1', 'pass', NULL, 'cla', 'claudionuovamail@gmail.com'),
(b'1', 'pass', NULL, 'cli', 'climaillibero@gmail.com'),
(b'1', 'pass', NULL, 'cliff', 'cliffhanger223@gmail.com'),
(b'1', 'pass', NULL, 'clint', NULL),
(b'1', 'pass', NULL, 'danilo', NULL),
(b'1', 'pass', NULL, 'dino', NULL),
(b'1', 'pass', NULL, 'eventour', NULL),
(b'1', 'pass', NULL, 'figaro', NULL),
(b'1', 'pass', NULL, 'gabri', 'gabrimail@gmail.com'),
(b'1', 'pass', NULL, 'pio', NULL),
(b'1', 'pass', NULL, 'tarantis', NULL),
(b'1', 'pass', NULL, 'tizioso', NULL);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `autorizzazioni`
--
ALTER TABLE `autorizzazioni`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_b6kx4u8g6x0m74pegprgk0ow` (`user`);

--
-- Indici per le tabelle `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`idevento`),
  ADD KEY `FKrfpp8hr2opw85eok21hn7ig6b` (`idtipologia`),
  ADD KEY `FKasnnoj4o12h297axbkggoulie` (`user`);

--
-- Indici per le tabelle `faq`
--
ALTER TABLE `faq`
  ADD PRIMARY KEY (`idfaq`);

--
-- Indici per le tabelle `ordine`
--
ALTER TABLE `ordine`
  ADD PRIMARY KEY (`idordine`),
  ADD KEY `FKijqvlthk058fsc8s9gwf1wgqa` (`idevento`),
  ADD KEY `FKsqx6lajbfj6k7mgvkadtqj4g5` (`user`);

--
-- Indici per le tabelle `tipologia`
--
ALTER TABLE `tipologia`
  ADD PRIMARY KEY (`idtipologia`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`user`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `autorizzazioni`
--
ALTER TABLE `autorizzazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT per la tabella `evento`
--
ALTER TABLE `evento`
  MODIFY `idevento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT per la tabella `faq`
--
ALTER TABLE `faq`
  MODIFY `idfaq` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `ordine`
--
ALTER TABLE `ordine`
  MODIFY `idordine` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT per la tabella `tipologia`
--
ALTER TABLE `tipologia`
  MODIFY `idtipologia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `autorizzazioni`
--
ALTER TABLE `autorizzazioni`
  ADD CONSTRAINT `FK545i8osov2cbxcmqoxrfwh24` FOREIGN KEY (`user`) REFERENCES `utente` (`user`) ON DELETE CASCADE;

--
-- Limiti per la tabella `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `FKasnnoj4o12h297axbkggoulie` FOREIGN KEY (`user`) REFERENCES `utente` (`user`),
  ADD CONSTRAINT `FKrfpp8hr2opw85eok21hn7ig6b` FOREIGN KEY (`idtipologia`) REFERENCES `tipologia` (`idtipologia`);

--
-- Limiti per la tabella `ordine`
--
ALTER TABLE `ordine`
  ADD CONSTRAINT `FKijqvlthk058fsc8s9gwf1wgqa` FOREIGN KEY (`idevento`) REFERENCES `evento` (`idevento`),
  ADD CONSTRAINT `FKsqx6lajbfj6k7mgvkadtqj4g5` FOREIGN KEY (`user`) REFERENCES `utente` (`user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
