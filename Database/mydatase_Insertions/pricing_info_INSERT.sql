/*
-- Query: SELECT * FROM tradeblotter.pricing_info
LIMIT 0, 1000

-- Date: 2016-08-27 11:51
*/

use mydatabase;
create table pricing_info (
TradeID int(11) auto_increment PRIMARY KEY ,
Side char(1) ,
TimeUpdated datetime,
TradeStatus varchar(15),
Product varchar(45),
Qty int(11),
Price decimal(8,2),
Firm varchar(45)
);

INSERT INTO `pricing_info` (`TradeID`,`Side`,`TimeUpdated`,`TradeStatus`,`Product`,`Qty`,`Price`,`Firm`) VALUES (1,'B','2016-05-20 10:20:59','Completed','Reliance',10,1001.00,'SushilKiFirm');
INSERT INTO `pricing_info` (`TradeID`,`Side`,`TimeUpdated`,`TradeStatus`,`Product`,`Qty`,`Price`,`Firm`) VALUES (2,'B','2016-05-22 10:20:59','InCompleted','Puma',15,1500.00,'Akaria');
INSERT INTO `pricing_info` (`TradeID`,`Side`,`TimeUpdated`,`TradeStatus`,`Product`,`Qty`,`Price`,`Firm`) VALUES (3,'S','2016-06-20 10:20:59','InCompleted','Reebok',100,100.00,'SolidTrades');
INSERT INTO `pricing_info` (`TradeID`,`Side`,`TimeUpdated`,`TradeStatus`,`Product`,`Qty`,`Price`,`Firm`) VALUES (4,'B','2016-04-22 10:20:59','InCompleted','Adidas',9,120.00,'Belano');
INSERT INTO `pricing_info` (`TradeID`,`Side`,`TimeUpdated`,`TradeStatus`,`Product`,`Qty`,`Price`,`Firm`) VALUES (5,'B','2016-07-20 10:20:59','InCompleted','GoldmanSachs',500,1000.00,'Heloz');
INSERT INTO `pricing_info` (`TradeID`,`Side`,`TimeUpdated`,`TradeStatus`,`Product`,`Qty`,`Price`,`Firm`) VALUES (6,'S','2016-08-17 11:20:59','Completed','Emarald',90,1120.00,'Axtria');
INSERT INTO `pricing_info` (`TradeID`,`Side`,`TimeUpdated`,`TradeStatus`,`Product`,`Qty`,`Price`,`Firm`) VALUES (7,'B','2016-07-03 10:20:59','InCompleted','DLF',50,1700.00,'Lamo');
