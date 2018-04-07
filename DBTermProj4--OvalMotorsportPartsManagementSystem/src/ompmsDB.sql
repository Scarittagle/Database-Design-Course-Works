-- create database
create database OvalMotorsport charset utf8;

-- select database
use OvalMotorsport;


create table if not exists customers(
cid int primary key auto_increment comment'AutoIncrease customerid',
cname varchar(20)not null comment 'Customer Name',
password varchar(20)not null,
email varchar(40) not null comment 'Customer Email',
s_address varchar(40) not null comment 'Shipping address'
)charset utf8;

-- create tables
create table if not exists parts(
stock_num int primary key auto_increment comment'stock number',
price decimal(10,2) not null,
title varchar(20)not null,
brand varchar(20)not null,
model varchar(20)not null,
Years char(4)not null,
MP_num varchar(20)not null comment 'Manufacturer part number',
category varchar(20) not null,
qty_in_stock int not null
)charset utf8;


create table if not exists shopping_cart(
sid int primary key auto_increment comment'shipping chart id',
cid int,
stock_num int,
subtotal decimal(10,2)
)charset utf8;

create table if not exists orders(
order_id int primary key auto_increment comment'order number',
t_number char(8) not null comment'tracking number',
pay_option varchar(10) not null comment'payment option',
s_date date not null comment'shipping date',
sid int not null comment'shipping chart id'
)charset utf8;

create table if not exists orderlist(
qty int not null comment'quantity',
order_id int comment'order number',
stock_num int comment'stock number'
)charset utf8;

create table if not exists payment_option(
order_id int comment'order number',
c_number char(16) not null comment'card number',
security_num char(3) not null comment'security number',
Ex_date char(5) not null comment'Expiration data'
)charset utf8;
-- insert data
-- insert into customers values(default,'Mopke votch','VGF3123','adriel22.matosa@testoh.gq','123 6th St.Melbourne,FL 32904');
-- insert into customers values(default,'Hugu Jass','wqwe2111','adqwe212.gaerasa@gmeah.gq','71 Pilgrim Avenue Chevy Chase, MD 20815');
-- insert into customers values(default,'Wu Bo','bobo123','bo.wu@qq.com','14061 aefzx dr, MD 20815');
select stock_num,price, qty_in_stock from parts where brand='Honda' AND title='Accord';
-- insert data to parts
insert into parts values(default,'200','Front Bumper','Honda','Accord','86','HDB009','Body','5');
insert into parts values(default,'300','Rear Bumper','Honda','Accord','86','HDB010','Body','5');
insert into parts values(default,'198','Front Bumper','Honda','Accord','86','HDB013','Body','5');
