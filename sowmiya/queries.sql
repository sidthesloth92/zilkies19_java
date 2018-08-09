#1
SELECT distinct customer.CUST_ID,customer.CUST_NAME fROM customer,shipment 
where (customer.CUST_ID=shipment.cust_id
and shipment.destination='Sioux City');
#2
SELECT shipment.destination from customer,shipment where(customer.CUST_ID=shipment.cust_id
and customer.ANNUAL_REVENUE<1000000);
#3
SELECT distinct city_name,population from city JOIN
shipment where (city.city_name=shipment.destination and weight>100);
#4
SELECT CUST_NAME FROM customer JOIN shipment where(customer.CUST_ID=shipment.cust_id and ANNUAL_REVENUE>=5000000 and weight<1);
#5
SELECT CUST_NAME FROM customer JOIN shipment
where(customer.CUST_ID=shipment.cust_id and (ANNUAL_REVENUE>5000000 and( weight<1 or destination='San Francisco')));
#6
select driver_name from truck
join shipment on shipment.truck_id=truck.truck_id
join city on city.city_name=shipment.destination
natural join customer
where ANNUAL_REVENUE>20000000 and population>1000000;
#7
SELECT destination FROM shipment JOIN customer 
where(customer.CUST_ID=shipment.cust_id and ANNUAL_REVENUE>15000000);
#8
SELECT distinct driver_name FROM truck Join shipment
where(shipment.truck_id=truck.truck_id and weight>100);
#9
SELECT distinct CUST_NAME,ANNUAL_REVENUE FROM customer JOIN shipment
WHERE (customer.CUST_ID=shipment.cust_id and weight>100);
#10
SELECT CUST_NAME,ANNUAL_REVENUE from customer where CUST_ID IN (SELECT cust_id FROM shipment join truck
where(shipment.truck_id = truck.truck_id and driver_name='Jensen'));
#11
select CUST_NAME from customer join shipment on
customer.CUST_ID=shipment.cust_id 
group by shipment.cust_id
having count(distinct shipment.truck_id)=(select count(*) from truck);
#12
select destination from shipment
group by destination
having count(distinct cust_id)=(select count(*) from customer); 
#13
select driver_name from truck join shipment
on truck.truck_id=shipment.truck_id
group by truck.truck_id
having count(distinct destination)=(select count(*) from shipment);

#14
SELECT CUST_NAME from customer where CUST_ID IN(SELECT cust_id FROM shipment 
join city where(city.city_name=shipment.destination and city_name='st.Louis'))or CUST_TYPE='manufacturer';
#15
SELECT destination from shipment join city
where (city.city_name=shipment.destination and weight=100 
and cust_id=311 and city.population>1000000);
#16
select distinct truck.truck_id from truck join shipment on truck.truck_id=shipment.truck_id where driver_name='Jake Stinson' and destination not in('Denver');
#17
select distinct  CUST_NAME from customer join shipment on customer.CUST_ID=shipment.cust_id join city on shipment.destination
=city.city_name where ANNUAL_REVENUE>10000000 and weight<1 and population<10000;
#18 a
create view under_1 as
select CUST_NAME,CUST_ID from customer
where ANNUAL_REVENUE<1000000;
#18 b
create view between1_5 as
select CUST_NAME,CUST_ID from customer
where ANNUAL_REVENUE>1000000 and ANNUAL_REVENUE<5000000;
#18 c
create view over_5 as
select CUST_NAME,CUST_ID from customer
where ANNUAL_REVENUE>5000000;
#19 acreate view driver as
select driver_name from truck
inner join shipment on truck.truck_id=shipment.truck_id
natural join  over_5
where destination='Los Angeles';
#19 b
select distinct population from city join shipment on 
city.city_name=shipment.destination 
natural join between1_5;
#19 c
select distinct driver_name,population from truck join shipment on
truck.truck_id=shipment.truck_id join city on
city.city_name=shipment.destination
natural join under_1;





