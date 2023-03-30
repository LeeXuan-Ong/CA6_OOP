DROP DATABASE IF EXISTS `musicstore`;

CREATE TABLE `instruments` (
  `insId` int(11) NOT NULL AUTO_INCREMENT,
  `insName` varchar(50) DEFAULT NULL,
  `insStocks` int(11) DEFAULT NULL,
  `insDesc` text DEFAULT NULL,
  `insPrice` decimal(10,2) DEFAULT NULL,
  `insCategory` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`insId`)
)

INSERT INTO `instruments` (`insName`, `insStocks`, `insDesc`, `insPrice`, `insCategory`) VALUES
('Guitar', 10, 'This is a guitar', '100.00', 'String'),
('Piano', 10, 'This is a piano', '100.00', 'String'),
('Drums', 10, 'This is a drum', '100.00', 'Percussion'),
('Violin', 10, 'This is a violin', '100.00', 'String'),
('Trumpet', 10, 'This is a trumpet', '100.00', 'Brass'),
('Saxophone', 10, 'This is a saxophone', '100.00', 'Brass'),
('Flute', 10, 'This is a flute', '100.00', 'Woodwind'),
('Clarinet', 10, 'This is a clarinet', '100.00', 'Woodwind'),
('Trombone', 10, 'This is a trombone', '100.00', 'Brass'),
( 'Cello', 10, 'This is a cello', '100.00', 'String');

