-- First I am going to insert data to the bank_account table
-- The info that needs to be inserted is:  bank Id, bank account number, bank account name, bank account ammount, transactions, times deposited money,
-- times withdrew money, has bank account (all in that order)
insert into bankaccount 
values
(1, 15, 'Checking Account', 10000, true);



-- Next I am going to insert data into the user account
-- The info that needs to be inserted is: id, user name, password, eamil, First name, Last Name, times user loged in (all in that order)
insert into useraccount 
values
(1, 'Luculentmovie00', 'Pass', 'gblois@yahoo.com','Geoffrey', 'Blois', 35);

-- Adding data to the transacion table
-- The info that need to be add into the table is:id number account number, type of transaction, Memo of transaction.
insert into transactiontable 
values
(9, 15, 'Deposit', 'Paid 10000 dollars from a sale of a car');

-- Going to create a view
create view all_account_info as
select u.id, u.user_name, u.password, u.email, u.phone_number, u.times_user_logins, b.bank_account_number, b.bank_account_name, b.bank_account_ammount, t.transactions, t.times_deposite_money, t.times_withdrew_money  
from bankaccount b
join useraccount u on b.bank_account_number = u.id 
join transactiontable t on account_number = t.account_number; 

-- view all accounts information
select * from all_account_info;


-- Shows all the information from the user account table
create view user_account as
select u.id, u.user_name, u."password", u.email, u.phone_number, u.times_user_logins 
from useraccount u;

select * from user_account;

-- Shows all the information from the bank account table
create view bank_account as
select b.bank_account_number, b.bank_account_name, b.bank_account_ammount, b.has_bank_account
from bankaccount b;

select * from bank_account;

-- Shows all the information from the transaction account table
create view actions_account as
select t.account_number, t.transactions, t.times_deposite_money, t.times_withdrew_money  
from transactiontable t;

select * from actions_account;

-- These will need to be dropped if I want to drop the tables
drop view all_account_info;
drop view user_account;
drop view bank_account;
drop view actions_account;
-- update commands 

-- This update all users to Johndoe
update useraccount 
set user_name='Johndoe';

-- delete commands
delete from useraccount
where user_name='Johndoe';

delete from bankaccount
where bank_account_number=10;

delete from transactiontable
where account_number=10;


update bankaccount set bank_account_amount = bank_account_amount + 1000 where bank_id = 3;







