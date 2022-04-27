-- First I am going to insert data to the bank_account table
-- The info that needs to be inserted is:  bank account number, bank account name, bank account ammount, transactions, times deposited money,
-- times withdrew money, has bank account (all in that order)
insert into bankaccount 
values
(10, 'Checking Account', 10000, true);



-- Next I am going to insert data into the user account
-- The info that needs to be inserted is: id, user name, password, times user loged in (all in that order)
insert into useraccount 
values
(10, 'Geoffrey', 'Pass', 35);

-- Adding data to the transacion table
-- The info that need to be add into the table is: account number, transactions, times deposited money, times withdrew money
insert into transactiontable 
values
(10, 30, 50, 60);

-- Going to create a view
create view all_account_info as
select u.id, u.user_name, u.password, u.times_user_logins, b.bank_account_number, b.bank_account_name, b.bank_account_ammount, t.transactions, t.times_deposite_money, t.times_withdrew_money  
from bankaccount b
join useraccount u on b.bank_account_number = u.id 
join transactiontable t on account_number = t.account_number; 

-- view all accounts information
select * from all_account_info;


-- Shows all the information from the user account table
create view user_account as
select u.id, u.user_name, u."password", u.times_user_logins 
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
