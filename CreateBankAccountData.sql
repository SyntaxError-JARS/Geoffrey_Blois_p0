
-- This created the schema I am going to work in
create schema bankaccount;


-- These three create table commands allow me to create the three tables I believe I need for this bank application
create table useraccount(
	id serial primary key,
	user_name varchar(20) not null unique,
	password varchar(20) not null unique,
	email varchar(20) not null unique,
	first_Name varchar(30) not null,
	last_name varchar(30) not null,
	times_user_logins int not null
);


create table bankaccount(
	bank_id serial not null unique,
	bank_account_number int primary key,
	bank_account_name varchar(25) not null,	
	bank_account_amount int not null,
	has_bank_account bool default false
);

create table transactiontable(
	id serial primary key,
	account_number int not null,
	transactions_type varchar(25),
	transaction_note text
);

-- Alter all account before adding data!!!!!!!!!!
-- This alter table will allow me to connect the user account table and bank account table together for referentail integerty
alter table bankaccount  
add constraint fk_bank_id foreign key(bank_id) references useraccount(id);

-- This alter table will allow me to connect the bankaccount table and accounttransation table together
alter table transactiontable 
add constraint fk_account_number foreign key(account_number) references bankaccount(bank_account_number);

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

