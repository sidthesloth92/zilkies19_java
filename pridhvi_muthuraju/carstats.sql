
drop database carstats
create database carstats
use carstats

insert into users(user_name,user_password,admin_status) values('admin','admin','ADMIN');

SET FOREIGN_KEY_CHECKS=0;
truncate table specification;
truncate table make;
truncate table car;
truncate table car_type;
truncate table users;
truncate table rating;
truncate table statistics;
truncate table request;
SET FOREIGN_KEY_CHECKS=1;

select * from specification
select * from make
select * from car_type
select * from car
select * from users
select * from statistics
select * from rating
select * from request

create table specification(car_id int auto_increment primary key,car_name varchar(30),
engine_type enum('PETROL','DIESEL'),cylinder int,displacement int,transmission int,
power int not null,torque int not null,fuel_capacity int,wheelbase int not null,
kerb_weight int not null,airbag varchar(30),abs varchar(30),drivetrain enum('FWD','RWD','AWD'),
price int not null,car_status enum('APPROVED','UNAPPROVED'));

create table make(make_id int auto_increment primary key,make_name varchar(30));

create table car_type(car_type_id int auto_increment primary key,car_type_name varchar(20));


create table car(car_id int auto_increment primary key,foreign key(car_id) 
references specification(car_id) on delete cascade,make_id int,car_type_id int,
foreign key(make_id) references make(make_id),
foreign key(car_type_id) references car_type(car_type_id));

create table users(first_name varchar(20),last_name varchar(20),user_name varchar(20) primary key
,email_id varchar(30),user_password 	varchar(20),admin_status enum('ADMIN','USER'));

create table rating(car_id int,user_name varchar(20),primary key(car_id,user_name)
,foreign key(car_id) references specification(car_id) on delete cascade
,foreign key(user_name) references users(user_name) on delete cascade,
rating enum('1','2','3','4','5'),review varchar(500));

ALTER TABLE rating
ADD review_subject varchar(50);

create table statistics(car_id int,statistics_year int not null,primary key(car_id,statistics_year),
sale_count int,foreign key(car_id) references specification(car_id) on delete cascade);

create table request(request_id int auto_increment primary key,car_id int,user_name varchar(20),
foreign key(car_id) references specification(car_id) on delete cascade,
foreign key(user_name) references users(user_name) on delete cascade);

select SUM(sale_count) from statistics where statistics_year=2015