/*Entries for USER Table*/
INSERT into tradeblotter.users (FirstName,LastName,Username,Password,LogoutTime) values ('Bhavya','Tak','btak','bt123','2012-06-18 10:34:09' );
INSERT into tradeblotter.users (FirstName,LastName,Username,Password,LogoutTime) values ('Vijay','Meena','vmeena','vm123','2012-06-18 10:36:09');
INSERT into tradeblotter.users (FirstName,LastName,Username,Password,LogoutTime) values ('Sushil','Singh','ssingh','ss123','2012-06-18 22:38:09');
INSERT into tradeblotter.users (FirstName,LastName,Username,Password,LogoutTime) values ('Nitish','Kumar','nkumar','nk123','2012-08-11 22:48:09');
INSERT into tradeblotter.users (FirstName,LastName,Username,Password,LogoutTime) values ('Vineet','V','vv','vv123','2012-06-18 7:38:09');
INSERT into tradeblotter.users (FirstName,LastName,Username,Password,LogoutTime) values ('Ashutosh','Kumar','akumar','ak123','2016-06-18 22:38:09');
INSERT into tradeblotter.users (FirstName,LastName,Username,Password,LogoutTime) values ('Rucha','H','rh','rh123','2015-10-18 22:38:09');

/*Entries for GroupStatic Table*/
insert into groupstatic(GroupID,GroupName,CreatedBy) values(1,'Group1','ssingh');
insert into groupstatic(GroupID,GroupName,CreatedBy) values(2,'Group2','akumar');
insert into groupstatic(GroupID,GroupName,CreatedBy) values(3,'Group3','nkumar');
insert into groupstatic(GroupID,GroupName,CreatedBy) values(4,'group4','btak');
insert into groupstatic(GroupID,GroupName,CreatedBy) values(5,'group5','btak');

/*Entries for GroupStatic Table*/
insert into groupdynamic(Username,GroupID) values('ssingh',1);
insert into groupdynamic(Username,GroupID) values('vv',1);
insert into groupdynamic(Username,GroupID) values('rh',1);
insert into groupdynamic(Username,GroupID) values('nkumr',1);
insert into groupdynamic(Username,GroupID) values('vv',2);
insert into groupdynamic(Username,GroupID) values('rh',2);
insert into groupdynamic(Username,GroupID) values('akumr',2);
insert into groupdynamic(Username,GroupID) values('vmeena',3);
insert into groupdynamic(Username,GroupID) values('nkumar',3);
insert into groupdynamic(Username,GroupID) values('rh',3);
insert into groupdynamic(Username,GroupID) values('btak',4);
insert into groupdynamic(Username,GroupID) values('btak',5);

/*Entries for ScratchPad Table*/
insert into tradeblotter.scratchpad(Notes,Username) values('My name is vijay meena.','vmeena');
insert into tradeblotter.scratchpad(Notes,Username) values('I am vineet.','vv');
insert into tradeblotter.scratchpad(Notes,Username) values('My name is rucha','rh');

/*Entries for pricing_info Table*/
INSERT into tradeblotter.pricing_info (Side,TimeUpdated,TradeStatus,Product,Qty,Price,Firm) values('S','2016-08-17 11:20:59','Completed','Emarald',90,1120,'Axtria');
INSERT into tradeblotter.pricing_info (Side,TimeUpdated,TradeStatus,Product,Qty,Price,Firm) values('B','2016-07-03 10:20:59','InCompleted','DLF',50,1700,'Lamo');
INSERT into tradeblotter.pricing_info (Side,TimeUpdated,TradeStatus,Product,Qty,Price,Firm) values('B','2016-07-20 10:20:59','InCompleted','GoldmanSachs',500,1000,'Heloz');
INSERT into tradeblotter.pricing_info (Side,TimeUpdated,TradeStatus,Product,Qty,Price,Firm) values('S','2016-06-20 10:20:59','InCompleted','Reebok',100,100,'SolidTrades');

/*Entries for Message Table */
insert into messages(GroupID,Sender,Message,Time) values(1,'ssingh','Hi frnds,i am sleeping','2016-8-20  8:57:17');
insert into messages(GroupID,Sender,Message,Time) values(2,'vmeena','Hi frnds,h r u ??','2016-8-10  8:57:16');
insert into messages(GroupID,Sender,Message,Time) values(2,'vv','Hi frnds,i am Reading','2016-8-20  10:37:14');
insert into messages(GroupID,Sender,Message,Time) values(1,'ssingh','Hi frnds,i am playing','2016-8-20  1:12:09');
insert into messages(GroupID,Sender,Message,Time) values(1,'ssingh','Hi frnds,i am in USA','2016-8-20  7:08:17');








