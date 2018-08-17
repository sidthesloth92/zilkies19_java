create database shippingDB;
use shippingDB;
create table customer(
	cust_id int check (cust_id>=100 && custid<=10000), 
    cust_name varchar(20), 
    annual_revenue int default 20000, 
    cust_type varchar(20) check(cust_type in('retailer', 'manufacturer', 'wholesaler'))
);

alter table customer
add primary key(cust_id);

insert into customer values(100,'Revathi',1000000, 'manufacturer');
insert into customer values(101,'Richa',1800000, 'wholesaler');
insert into customer values(102,'Rishi',1000000, 'retailer');
insert into customer values(103,'Rijesh',4000000, 'wholesaler');
insert into customer values(104,'Kalyan',4800000, 'wholesaler');
insert into customer values(311,'Karthik',5500000, 'retailer');

use shippingDB;
create table shipment(
	shipment_id int,
    cust_id int,
    weight float default 10 check(weight<1000),
    truck_id int,
    destination varchar(20),
    ship_date varchar(10) default null,
    primary key(shipment_id),
    foreign key(cust_id) references customer(cust_id) on delete cascade,
    foreign key(truck_id) references truck(truck_id) on delete set null,
    foreign key(destination) references city(city_name) on delete set null
);

create table truck(
	truck_id int,
    driver_name varchar(20)
);

create table city(
	city_name varchar(20),
    population int
);


alter table city
modify column population bigint;

alter table truck
add primary key(truck_id);

alter table city
add primary key(city_name);


insert into truck values
	(100,'Jensen'),
    (101,'Sasi'),
    (102,'Hrithik'),
    (103,'Jake Stinson')
;

insert into city values
	('London',100000000),
    ('Paris', 120000000),
    ('Rome',  200000000),
    ('Panama City', 1230000000),
    ('San Francisco', 20000000),
    ('Sioux City', 5000000000),
    ('Manhattan', 10000000),
    ('Los Angeles', 7000),
    ('Baltimore', 2000),
    ('Denver', 1000),
    ('St.Louis', 5000)
;
	
insert into shipment values(100,100,500,100,'London',null);
insert into shipment values(101,101,100,102,'Paris',null);
insert into shipment values(102,101,300,103,'London',null);
insert into shipment values(103,101,10,102,'Panama City','12-Dec-03');
insert into shipment values(104,101,20,101,'Los Angeles',null);
insert into shipment values(105,102,200,102,'Rome',null);
insert into shipment values(106,100,50,101,'Sioux City','18-Sep-03');
insert into shipment values(107,104,500,100,'Manhattan',null);
insert into shipment values(108,103,50,103,'San Francisco',null);
insert into shipment values(109,104,25,101,'San Francisco',null);
insert into shipment values(110,102,200,103,'London','11-Oct-98');
insert into shipment values(111,103,100,101,'London','09-Sept-99');
insert into shipment values(112,104,500,100,'London','18-Jun-88');
insert into shipment values(113,104,200,100,'London','11-Oct-98');
insert into shipment values(114,104,50,103,'Manhattan','29-May-03');
insert into shipment values(115,100,75,103,'Los Angeles','17-Sept-02');
insert into shipment values(116,101,55,102,'Baltimore','01-Jul-02');
insert into shipment values(117,103,45,101,'Paris',null);
insert into shipment values(118,103,45,100,'Rome',null);
insert into shipment values(119,103,45,102,'Los Angeles',null);
insert into shipment values(120,104,45,102,'London',null);
insert into shipment values(121,100,150,102,'Sioux City',null);
insert into shipment values(122,101,500,102,'Manhattan',null);
insert into shipment values(123,102,250,102,'San Francisco','31-Jul-02');
insert into shipment values(124,311,0.5,102,'Denver',null);
insert into shipment values(125,311,100,102,'St.Louis',null);


#a
select distinct customer.cust_name, customer.cust_id 
from customer, shipment
where customer.cust_id=shipment.cust_id and shipment.destination='Sioux City';


#b
select shipment.destination 
from shipment,customer
where shipment.cust_id=customer.cust_id and customer.annual_revenue<1000000;

#c
select city.city_name,city.population 
from city
inner join shipment
on shipment.destination=city.city_name
where shipment.weight>100;

#d
select distinct customer.cust_name
from customer 
inner join shipment
on shipment.cust_id=customer.cust_id
where customer.annual_revenue>5000000 and shipment.weight<1;

#e
select distinct customer.cust_name
from customer 
inner join shipment
on shipment.cust_id=customer.cust_id
where customer.annual_revenue>5000000 and (shipment.weight<1 or shipment.destination='San Francisco');

#f
select truck.driver_name
from truck where truck.truck_id in 
(select shipment.truck_id 
from shipment 
inner join customer 
on customer.cust_id=shipment.cust_id 
where customer.annual_revenue>20000000 and shipment.destination in
(select city.city_name from city where city.population>1000000));

#g
select distinct shipment.destination
from shipment inner join customer
on customer.cust_id=shipment.cust_id
where customer.annual_revenue>1500000;

#h
select distinct truck.driver_name
from shipment
join truck
on shipment.truck_id=truck.truck_id
where shipment.weight>100;

#i
select cust_name, annual_revenue 
from customer 
where cust_id 
in (select cust_id from shipment where weight>100);

#j
select distinct cust_name, annual_revenue 
from customer
join shipment
on shipment.cust_id=customer.cust_id
where shipment.truck_id in
(select truck_id from truck where driver_name='Jensen');


#n
select cust_name 
from customer 
where cust_type='manufacturer' 
	or cust_id in(
	select cust_id 
    from shipment 
	where destination='St.Louis');
    
#o
select city_name 
from city
where population>1000000 and city_name in
	(select destination from shipment where
	weight=100 and cust_id=311);
    
#p
select distinct truck.truck_id 
from truck 
inner join shipment
on truck.truck_id=shipment.truck_id
where driver_name='Jake Stinson' and destination not in ('Denver');

#q
select customer.cust_name 
from customer inner join shipment
on customer.cust_id=shipment.cust_id
inner join city on city.city_name=shipment.destination
where customer.annual_revenue<10000 and shipment.destination in(select city.city_name where city.population<10000) and shipment.weight<1;

#18 a
create view under_one as
select CUST_NAME,CUST_ID from customer
where ANNUAL_REVENUE<1000000;
#18 b
create view between_one_and_five as
select CUST_NAME,CUST_ID from customer
where ANNUAL_REVENUE>1000000 and ANNUAL_REVENUE<5000000;
#18 c
create view over_five as
select CUST_NAME,CUST_ID from customer
where ANNUAL_REVENUE>5000000;
#19 a
select driver_name 
from truck
inner join shipment 
on truck.truck_id=shipment.truck_id
natural join  over_five
where destination='Los Angeles';
#19 b
select distinct population 
from city 
join shipment 
on
city.city_name=shipment.destination
natural join between_one_and_five;
#19 c
select distinct driver_name,population 
from truck 
join shipment 
on truck.truck_id=shipment.truck_id 
join city 
on city.city_name=shipment.destination
natural join under_one;
