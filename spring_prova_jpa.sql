-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 08, 2024 alle 13:18
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
(10, 'tarantis', 'org');

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
(300, 300, '2024-02-28', 1, 1, '44.50', 'eventour', 'Caparezza Tour', 'Nuovo tour italiano di Caparezza, il rapper italiano più apprezzato di sempre', 'Dopo due anni di attesa il rapper Caparezza torna in live con un imperdibile appuntamento, una grandissima occasione per rivivere lo show del più grande rapper italiano.', 'Caparezza.jpg', 'Foggia'),
(200, 200, '2024-02-15', 2, 1, '55.00', 'eventour', 'COSMO', 'Guardare fissi il palco non è obbligatorio.', 'Dopo aver fatto ballare i principali superclub di tutta Italia, conquistando 25.000 persone con sold out e raddoppi, COSMO non accenna a fermarsi e sarà protagonista di uno degli appuntamenti di Spilla 2018 con il suo nuovissimo singolo \"QUANDO HO INCONTR', 'cosmo.png', 'Cortile Mole Vanvitelliana'),
(120, 120, NULL, 3, 1, '85.00', 'tarantis', 'Bruce Springsteen', 'Tour Europero di Bruce Springsteen', 'Concerto del Tour Europeo di Bruce Spingsteen che arriva per la prima volta in Ancona.Il concerto si svolgerà allo Stadio del Conero', 'Bruce.jpg', 'Ancona'),
(75, 75, NULL, 4, 2, '22.50', 'tarantis', 'Sei personaggi in cerca d\'autore', 'Opera di Luigi Pirandello', 'Opera teatrale complessa e bellissima che analizza il rapporto fra teatro e vita ed esplora nel profondo la follia dell\'esistenza', 'Pirandello.jpg', 'Milano'),
(180, 180, NULL, 5, 1, '18.50', 'eventour', 'Mannarino', 'Nuova tour italiana di Mannarino.', 'Concerto dell\'artista romano Alessandro Mannarino che torna nella sua città natale con il nuovo album Apriti Cielo.L\'artista Romano si esibirà durante la manifestazione Rock in Roma che si svolgerà allo Ippodromo Capannelle', 'Mannarino.jpg', 'Roma'),
(250, 250, NULL, 6, 1, '22.00', 'eventour', 'Liberato', 'Milano Liberata', 'Liberato in concerto a Milano', 'liberato.png', 'Milano'),
(100, 100, NULL, 7, 4, '20.00', 'figaro', 'Picasso', 'Esposizione dei lavori del maestro Picasso', 'I migliori lavori di Picasso saranno disponibili per un periodo limitato. Con alclune delle opere inedite per il palcoscenico italiano', 'picasso.jpg', 'Padova');

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
(1, 'Come procedo per registrarmi al sito?', 'Se non sei registrato a EVENTO, clicca sul link REGISTRATI (sempre visibile in alto a destra della pagina ) oppure sul pulsante ACCEDI E REGISTRATI presente nella pagina della tabella prezzi di ogni evento. Una volta compilati e confermati tutti i dati, ti verrà inviato un messaggio all\'indirizzo e-mail indicato. La mail conterrà un link di attivazione che ti servirà per completare la registrazione.'),
(2, 'Come faccio a modificare i miei dati di registrazione?', 'Dopo aver effettuato l\'accesso con Login e password, clicca sul tuo Login nell\'area MY VIVATICKET - I MIEI DATI. Ti ricordiamo che trovi la sezione MY VIVATICKET sempre visibile in altro a destra nella pagina.');

-- --------------------------------------------------------

--
-- Struttura della tabella `ordine`
--

CREATE TABLE `ordine` (
  `biglietti` int(11) NOT NULL,
  `idevento` int(11) DEFAULT NULL,
  `idordine` int(11) NOT NULL,
  `user` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(b'1', 'pass', NULL, 'cla', 'claudionuovamail@gmail.com'),
(b'1', 'pass', NULL, 'cli', NULL),
(b'1', 'pass', NULL, 'cliff', NULL),
(b'1', 'pass', NULL, 'clint', NULL),
(b'1', 'pass', NULL, 'danilo', NULL),
(b'1', 'pass', NULL, 'dino', NULL),
(b'1', 'pass', NULL, 'eventour', NULL),
(b'1', 'pass', NULL, 'figaro', NULL),
(b'1', 'pass', NULL, 'tarantis', NULL);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT per la tabella `evento`
--
ALTER TABLE `evento`
  MODIFY `idevento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT per la tabella `faq`
--
ALTER TABLE `faq`
  MODIFY `idfaq` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `ordine`
--
ALTER TABLE `ordine`
  MODIFY `idordine` int(11) NOT NULL AUTO_INCREMENT;

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
