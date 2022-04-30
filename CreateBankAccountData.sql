
-- This created the schema I am going to work in
create schema bankaccount;


-- These three create table commands allow me to create the three tables I believe I need for this bank application
create table useraccount(
	id serial primary key,
	user_name varchar(20) not null unique,
	password varchar(20) not null unique,
	email varchar(20) not null unique,
	phone_number varchar(20) not null unique,
	times_user_logins int not null
);


create table bankaccount(
	bank_account_number int primary key,
	bank_account_name varchar(25) not null,	
	bank_account_ammount int not null,
	has_bank_account bool default false
);

create table transactiontable(
	account_number int primary key,
	transactions int not null,
	times_deposite_money int not null,
	times_withdrew_money int not null
);

-- Alter all account before adding data!!!!!!!!!!
-- This alter table will allow me to connect the user account table and bank account table together for referentail integerty
alter table useraccount
add constraint fk_bankaccount foreign key(id) references bankaccount(bank_account_number);

-- This alter table will allow me to connect the bankaccount table and accounttransation table together
alter table bankaccount 
add constraint fk_transactiontable foreign key(bank_account_number) references transactiontable(account_number);

-- If for some reason I mess up I can use these commands to drop the tables and start again.
-- execute first
drop table useraccount;
-- execute second
drop table bankaccount;
-- execute third
drop table transactiontable;

-- Added a way to drop all of the tables and its foreign keys
drop table if exists transactiontable, useraccount, bankaccount 
cascade;

