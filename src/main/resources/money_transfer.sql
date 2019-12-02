DROP TABLE IF EXISTS User;
CREATE TABLE User (
id LONG PRIMARY KEY   AUTO_INCREMENT NOT NULL , 
name VARCHAR(30) NOT NULL,
surname VARCHAR(30) NOT NULL,
email_address VARCHAR(30) NOT NULL,
phone_number VARCHAR(15) NOT NULL
);

CREATE UNIQUE INDEX index_user on User(id);

INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('muhammed','baglar','muhammedbaglar@gmail.com','05439004050');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('ahmet','baglar','ahmetbaglar@gmail.com','05429003212');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('kubra','baglar','kubrabaglar@gmail.com','05235334654');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('sedat','baloglu','sedatbaloglu@gmail.com','05419003434');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('sema','yildirim','semayildirim@gmail.com','054234543321');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('sema','kalay','semakalay@gmail.com','05434565454');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('emre','kucuk','emrekucuk@gmail.com','05122343564');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('zehra','erdem','zehraerdem@gmail.com','05404321245');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('mehmet','yaman','mehmetyaman@gmail.com','05394565332');
INSERT INTO User (name , surname ,email_address,phone_number) VALUES ('omer','demir','omerdemir@gmail.com','05467875421');
 

 
DROP TABLE IF EXISTS Account;

CREATE TABLE Account (
id LONG PRIMARY KEY   AUTO_INCREMENT NOT NULL , 
account_number LONG  NOT NULL,
phone_number VARCHAR(15)  NOT NULL,
balance DECIMAL(19,3) NOT NULL,
currency_code VARCHAR(3) NOT NULL
);

CREATE UNIQUE INDEX index_account on Account(id);

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('252502102','05439004050',103.343,'TRY');
INSERT INTO Account (account_number,phone_number ,balance,currency_code) VALUES('254312334','05439004050',323.564,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('432345765','05429003212',546.753,'TRY');
INSERT INTO Account (account_number,phone_number ,balance,currency_code) VALUES('646567865','05429003212',312.223,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('435675322','05235334654',986.765,'TRY');
INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('232216643','05235334654',908.123,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('252502143','05419003434',103.343,'TRY');
INSERT INTO Account (account_number,phone_number ,balance,currency_code) VALUES('254312375','05419003434',323.564,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('432345675','05434565454',546.753,'TRY');
INSERT INTO Account (account_number,phone_number ,balance,currency_code) VALUES('646568765','05429003212',312.223,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('435679322','054234543321',986.765,'TRY');
INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('232212343','054234543321',908.123,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('252452102','05122343564',103.343,'TRY');
INSERT INTO Account (account_number,phone_number ,balance,currency_code) VALUES('257612334','05122343564',323.564,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('431235765','05404321245',546.753,'TRY');
INSERT INTO Account (account_number,phone_number ,balance,currency_code) VALUES('646747865','05404321245',312.223,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('435895322','05394565332',986.765,'TRY');
INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('232234643','05394565332',908.123,'GBP');

INSERT INTO Account (account_number,phone_number,balance,currency_code) VALUES ('252986710','05467875421',103.343,'TRY');
INSERT INTO Account (account_number,phone_number ,balance,currency_code) VALUES('925490733','05467875421',323.564,'GBP');




DROP TABLE IF EXISTS Transaction;

CREATE TABLE Transaction (transaction_no LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
start_date DATE NOT NULL,
end_date DATE NOT NULL,
amount  DECIMAL(19,3) NOT NULL,
sender_account LONG NOT NULL,
receiver_account LONG NOT NULL,
sender_currency_code VARCHAR(3) NOT NULL,
receiver_currency_code VARCHAR(3) NOT NULL,
statu VARCHAR(1) NOT NULL,
is_reverse number NOT NULL,
transaction_token varchar(10) NOT NULL
);

CREATE UNIQUE INDEX index_transaction on Transaction(transaction_no);
 
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,252502102,432345765,'TRY','TRY','W',0,'ununjzgvvs');
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,435675322,252502143,'TRY','TRY','W',0,'ylrtwheqrp');
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,432345675,435679322,'TRY','TRY','W',0,'hduoactuaz');
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,252452102,431235765,'TRY','TRY','W',0,'qqexoyqruq');
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,435895322,252986710,'TRY','TRY','W',0,'thfvxonvhw');


INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,254312334,646567865,'GBP','GBP','W',0,'ectxyduqqz');
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,232216643,254312375,'GBP','GBP','W',0,'iiovdeienm');
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,646568765,232212343,'GBP','GBP','W',0,'zfsuklcqvx');
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,257612334,646747865,'GBP','GBP','W',0,'nygfudrghd');
INSERT INTO Transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES
(TO_DATE('2019-11-27', 'YYYY-MM-DD'),TO_DATE('2019-11-27', 'YYYY-MM-DD'),23,232234643,925490733,'GBP','GBP','W',0,'reiasxurzh');

