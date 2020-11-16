create schema if not exists jdbcbank authorization protossarchon;

set search_path to jdbcbank;

drop table if exists logins cascade;
drop table if exists accounts cascade;
drop table if exists accountusers;
drop table if exists transactions;
drop table if exists logs;

create table logins (
userid serial primary key,
username varchar(16) unique,
passwrd varchar(16),
rolename varchar(8),
givenname varchar(30));

insert into logins (username, passwrd, rolename, givenname)
values
('lskywalker','force','EMPLOYEE','Luke Skywalker'),
('lorgana','alliance','ADMIN','Leia Organa'),
('hsolo','falcon','CUSTOMER','Han Solo'),
('chewie','bowcaster','CUSTOMER','Chewbacca');

create table accounts (
accountnumber serial primary key,
accounttype varchar(8),
balance numeric,
status varchar(8)
);

insert into accounts (accounttype,balance,status)
values
('checking',374.84,'approved'),
('savings ',480.10,'approved'),
('savings ',93.90,'approved'),
('checking',0.00,'pending');

create table accountusers (
primary key (accountnumber, userid),
accountnumber integer references accounts(accountnumber) on delete cascade,
userid integer references logins(userid) on delete cascade
);

insert into accountusers
values
(1,3),
(2,3),
(3,3),
(3,4),
(4,4);

create table transactions (
tid serial primary key,
transactiondate timestamp not null default current_timestamp,
userid integer,
message text);

insert into transactions (userid,message)
values
(3,'Made a withdrawal of $34.00 from account number 1. The account now has $416.84.'),
(3,'Made a deposit of $78.00 into account number 1. The account now has $494.84.'),
(3,'Made a withdrawal of $120.00 from account number 1. The account now has $374.84.'),
(3,'Made a deposit of $120.00 into account number 2. The account now has $480.10.');

create table logs (
lid serial primary key,
ddate date not null default current_date,
message text);

commit;

select *
from logins;

select *
from accounts;

select *
from accountusers;

select *
from transactions;

select *
from logs;

--DELETES A SPECIFIC ACCOUNT IF IT HAS NO ACCOUNTUSERS
/*delete from accounts
where not exists (
select *
from accountusers
where accountnumber = 6)
and accountnumber = 6;*/

--DELETE ALL ACCOUNTS THAT HAVE NO ACCOUNTUSERS
/*delete from accounts a
where not exists
(select *
from accountusers
where accountnumber = a.accountnumber);*/

--update accounts set status = 'approved' where accountnumber = 5;

--delete from accounts where balance = 872.39;

--select * from accounts where accountnumber = 4 and status = 'pending';