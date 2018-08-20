CREATE DATABASE zilker;
create table CUSTOMER(
	CUST_ID bigint primary key,
	CUST_NAME varchar(40),
    ANNUAL_REVENUE bigint default 20000,
    CUST_TYPE varchar(20), 
    check(CUST_ID >= 100 and CUST_ID <= 10000), 
    check(CUST_TYPE = 'manufacturer' || CUST_TYPE = 'wholesaler' || CUST_TYPE = 'retailer'));
    
DROP table CUSTOMER;

SELECT database();
use zilker;

select * from user;

create table TRUCK(
	TRUCK_NUM bigint primary key,
    DRIVER_NAME varchar(40) not null
);

DROP table TRUCK;

create table CITY(
	CITY_NAME varchar(20) primary key,
    POPULATION bigint not null
);

DROP table CITY;

create table SHIPMENT(
	SHIPMENT_NUM bigint primary key,
    CUST_ID bigint not null,
    WEIGHT double default 10,
    TRUCK_NUM bigint not null,
    DESTINATION varchar(20) not null,
    SHIP_DATE date default null,
	FOREIGN KEY (CUST_ID) references CUSTOMER (CUST_ID) on delete cascade,
    FOREIGN KEY (TRUCK_NUM) references TRUCK (TRUCK_NUM) on delete cascade,
    FOREIGN KEY (DESTINATION) references CITY (CITY_NAME) on delete cascade,
    CHECK (WEIGHT >= 0 and WEIGHT <=1000));
    
select * from SHIPMENT;
DROP table SHIPMENT;
    
insert into CUSTOMER values (100,'Revathi',1000000,'manufacturer');
insert into CUSTOMER values (101 ,'Richa', 1800000, 'wholesaler');
insert into CUSTOMER values (102, 'Rishi' ,1000000 ,'retailer');
insert into CUSTOMER values (103 ,'Rijesh', 4000000, 'wholesaler');
insert into CUSTOMER values (104, 'Kalyan', 4800000, 'wholesaler');
insert into CUSTOMER values (311, 'Karthik', 5500000, 'retailer');

insert into SHIPMENT values(100, 100, 500, 100, 'London' ,Null);
insert into SHIPMENT values(101, 101, 100, 102, 'Paris', Null);
insert into SHIPMENT values(102, 101, 300, 103, 'London', Null);
insert into SHIPMENT values(103, 101, 10, 102, 'Panama City', STR_TO_DATE('12-12-03', '%d-%m-%Y'));
insert into SHIPMENT values(104, 101, 20, 101, 'Los Angeles', Null);
insert into SHIPMENT values(105, 102, 200, 102, 'Rome', Null);
insert into SHIPMENT values(106, 100, 50, 101, 'Sioux City', STR_TO_DATE('18-09-03', '%d-%m-%Y'));
insert into SHIPMENT values(107, 104, 500, 100, 'Manhattan', Null);
insert into SHIPMENT values(108, 103, 50, 103, 'San Francisco', Null);
insert into SHIPMENT values(109, 104, 25, 101, 'San Francisco', Null);
insert into SHIPMENT values(110, 102, 200, 103, 'London', STR_TO_DATE('11-10-98', '%d-%m-%Y'));
insert into SHIPMENT values(111, 103, 100, 101, 'London', STR_TO_DATE('09-09-99', '%d-%m-%Y'));
insert into SHIPMENT values(112, 104, 500, 100, 'London', STR_TO_DATE('18-06-88', '%d-%m-%Y'));
insert into SHIPMENT values(113, 104, 200, 100, 'London', STR_TO_DATE('11-10-98', '%d-%m-%Y'));
insert into SHIPMENT values(114, 104, 50, 103, 'Manhattan', STR_TO_DATE('29-05-03', '%d-%m-%Y'));
insert into SHIPMENT values(115, 100, 75, 103, 'Los Angeles', STR_TO_DATE('17-09-02', '%d-%m-%Y'));
insert into SHIPMENT values(116, 101, 55, 102, 'Baltimore', STR_TO_DATE('01-07-02', '%d-%m-%Y'));
insert into SHIPMENT values(117, 103, 45, 101, 'Paris', Null);
insert into SHIPMENT values(118, 103, 45, 100, 'Rome', Null);
insert into SHIPMENT values(119, 103, 45, 102, 'Los Angeles', Null);
insert into SHIPMENT values(120, 104, 45, 102, 'London', Null);
insert into SHIPMENT values(121, 100, 150, 102, 'Sioux City', Null);
insert into SHIPMENT values(122, 101, 500, 102, 'Manhattan', Null);
insert into SHIPMENT values(123, 102, 250, 102, 'San Francisco', STR_TO_DATE('31-07-02', '%d-%m-%Y'));
insert into SHIPMENT values(124, 311, 0.5, 102, 'Denver', Null);
insert into SHIPMENT values(125, 311, 100, 102, 'St. Louis', Null);

insert into TRUCK values(100, 'Jensen');
insert into TRUCK values(101, 'Sasi');
insert into TRUCK values(102, 'Hrithik');
insert into TRUCK values(103, 'Jake Stinson');


insert into CITY values('London', 100000000);
insert into CITY values('Paris', 120000000);
insert into CITY values('Rome', 200000000);
insert into CITY values('Panama City', 1230000000);
insert into CITY values('San Francisco', 20000000);
insert into CITY values('Sioux City', 5000000000);
insert into CITY values('Manhattan', 10000000);
insert into CITY values('Los Angeles', 7000);
insert into CITY values('Baltimore', 2000);
insert into CITY values('Denver', 1000);
insert into CITY values('St. Louis', 5000);

#a
select distinct CUSTOMER.cust_name from CUSTOMER inner join SHIPMENT on CUSTOMER.CUST_ID = SHIPMENT.CUST_ID where SHIPMENT.DESTINATION = 'Sioux City';
#b
select SHIPMENT.DESTINATION from CUSTOMER inner join SHIPMENT on CUSTOMER.CUST_ID = SHIPMENT.CUST_ID where CUSTOMER.ANNUAL_REVENUE < 1000000;
#c
select distinct CITY.CITY_NAME, CITY.POPULATION from CITY inner join SHIPMENT on CITY.CITY_NAME = SHIPMENT.DESTINATION where SHIPMENT.WEIGHT > 100;
#d
select distinct CUSTOMER.CUST_NAME from CUSTOMER inner join SHIPMENT on CUSTOMER.CUST_ID = SHIPMENT.CUST_ID where SHIPMENT.WEIGHT < 1 and CUSTOMER.ANNUAL_REVENUE > 5000000;
#e
select distinct CUSTOMER.CUST_NAME from CUSTOMER inner join SHIPMENT on CUSTOMER.CUST_ID = SHIPMENT.CUST_ID where (SHIPMENT.WEIGHT < 1 or SHIPMENT.DESTINATION = 'San Francisco') and CUSTOMER.ANNUAL_REVENUE > 5000000;
#f
select distinct TRUCK.DRIVER_NAME from TRUCK where TRUCK_NUM in (select TRUCK_NUM from SHIPMENT where DESTINATION in (select CITY_NAME from CITY where POPULATION > 1000000) and CUST_ID in (select CUST_ID from CUSTOMER where ANNUAL_REVENUE > 2000000));
#g
select distinct CITY.CITY_NAME from CITY inner join SHIPMENT on CITY.CITY_NAME = SHIPMENT.DESTINATION where SHIPMENT.CUST_ID in (select CUST_ID from CUSTOMER where ANNUAL_REVENUE > 15000000);
#h
select distinct TRUCK.DRIVER_NAME from TRUCK inner join SHIPMENT on TRUCK.TRUCK_NUM = SHIPMENT.TRUCK_NUM where SHIPMENT.WEIGHT > 100;
#i
select distinct CUSTOMER.CUST_NAME, CUSTOMER.ANNUAL_REVENUE from CUSTOMER inner join SHIPMENT on CUSTOMER.CUST_ID = SHIPMENT.CUST_ID where SHIPMENT.WEIGHT > 100;
#j
select distinct CUSTOMER.CUST_NAME, CUSTOMER.ANNUAL_REVENUE from CUSTOMER inner join SHIPMENT on CUSTOMER.CUST_ID = SHIPMENT.CUST_ID where SHIPMENT.TRUCK_NUM = (SELECT TRUCK_NUM from TRUCK where DRIVER_NAME = 'Jensen');
#k
select distinct CUSTOMER.CUST_NAME from CUSTOMER where (select count(TRUCK_NUM) from TRUCK) = (select count(distinct TRUCK_NUM) from SHIPMENT where SHIPMENT.CUST_ID = CUSTOMER.CUST_ID);
#l
select distinct CITY.CITY_NAME from CITY where (select count(CUST_ID) from CUSTOMER) = (select count(distinct SHIPMENT.CUST_ID) from SHIPMENT where SHIPMENT.DESTINATION = CITY.CITY_NAME);
#m
select distinct TRUCK.DRIVER_NAME from TRUCK where (select count(CITY_NAME) from CITY) = (select count(distinct SHIPMENT.DESTINATION) from SHIPMENT where SHIPMENT.TRUCK_NUM = TRUCK.TRUCK_NUM);
#n
select distinct CUSTOMER.CUST_NAME from CUSTOMER inner join SHIPMENT on CUSTOMER.CUST_ID = SHIPMENT.CUST_ID where CUSTOMER.CUST_TYPE = 'manufacturer' or SHIPMENT.DESTINATION = 'St. Louis';
#o
select distinct CITY.CITY_NAME from CITY inner join SHIPMENT on CITY.CITY_NAME = SHIPMENT.DESTINATION where SHIPMENT.WEIGHT = 100 and SHIPMENT.CUST_ID = 311 and CITY.POPULATION > 1000000;
#p
select TRUCK_NUM from TRUCK where DRIVER_NAME = 'Jake Stinson' and TRUCK_NUM NOT IN (select TRUCK_NUM from SHIPMENT where DESTINATION = 'Denver');
#q
select CUST_NAME from CUSTOMER where ANNUAL_REVENUE > 1000000 and EXISTS (select * from SHIPMENT inner join CITY on SHIPMENT.DESTINATION = CITY.CITY_NAME where CITY.POPULATION < 10000 and SHIPMENT.WEIGHT < 1 and CUSTOMER.CUST_ID = SHIPMENT.CUST_ID);
#r.a
create view customers_below_1_million_revenue as select distinct CUST_NAME from CUSTOMER where ANNUAL_REVENUE < 1000000;
#r.b
create view customers_between_1_and_5_million_revenue as select distinct CUST_NAME from CUSTOMER where ANNUAL_REVENUE >= 1000000 and ANNUAL_REVENUE <= 5000000;
#r.c
create view customers_above_5_million_revenue as select distinct CUST_NAME from CUSTOMER where ANNUAL_REVENUE > 5000000;
#s.a
create view drivers_shipped_to_above_5_million_revenue_los_angeles_customers as select distinct TRUCK.DRIVER_NAME from TRUCK inner join SHIPMENT on TRUCK.TRUCK_NUM = SHIPMENT.TRUCK_NUM where SHIPMENT.DESTINATION = 'Los Angeles' and SHIPMENT.CUST_ID in (select CUST_ID from CUSTOMER where ANNUAL_REVENUE > 5000000);
#s.b
create view population_city_shipments_from_1_to_5_million_revenue_customers as select distinct CITY.POPULATION from CITY inner join SHIPMENT on CITY.CITY_NAME = SHIPMENT.DESTINATION where SHIPMENT.CUST_ID in (select CUST_ID from CUSTOMER where ANNUAL_REVENUE >= 1000000 and ANNUAL_REVENUE <= 5000000);
#s.c
create view truck_driver_and_population_of_below_1_million_revenue_shipments as select distinct TRUCK.DRIVER_NAME, CITY.POPULATION from (((TRUCK inner join SHIPMENT on TRUCK.TRUCK_NUM = SHIPMENT.TRUCK_NUM) inner join CUSTOMER on SHIPMENT.CUST_ID = CUSTOMER.CUST_ID) inner join CITY on SHIPMENT.DESTINATION = CITY.CITY_NAME) where CUSTOMER.ANNUAL_REVENUE < 1000000;

select * from truck_driver_and_population_of_below_1_million_revenue_shipments;