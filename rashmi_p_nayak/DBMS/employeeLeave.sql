create database EmployeeLeaveDetails;
use EmployeeLeaveDetails;
create table leave_type(leave_id int PRIMARY KEY NOT NULL,leave_type varchar(25),no_of_days int);
insert into leave_type(leave_id,leave_type,no_of_days) values(1,'sick',5),(2,'personal',5),(3,'vacation',10),(4,'flexi',2);
create table holiday_list(hol_date date primary key not null, name varchar(25));
desc holiday_list;
insert into holiday_list(hol_date,name) values('2018-08-15','Independence Day'),('2018-10-02','Gandhi Jayanthi'),('2018-10-13','Flexi Leave'),('2018-11-21','Flexi Leave');
create table pay_detail(dept varchar(25) primary key not null, pay_day int);
create table emp_details(emp_id int primary key not null, emp_name varchar(25),dept varchar(25),constraint foreign key(dept) references pay_detail(dept));
create table dept_details(dept_id int primary key not null,dept_name varchar(25),manager varchar(25));
create table pay_detail(dept_id int,desig varchar(25),pay_day int, constraint primary key(dept_id,desig));
create table emp_details(emp_id int primary key not null,emp_name varchar(25),dept_id int,desig varchar(25),constraint foreign key(dept_id,desig) references pay_detail(dept_id,desig));
create table leave_log(log_id int primary key not null,emp_id int,leave_id int,start_date date,end_date date,approved boolean,constraint foreign key(emp_id) references emp_details(emp_id), foreign key(leave_id) references leave_type(leave_id));
insert into dept_details(dept_id,dept_name,ma;nager) values(1,'finance','John'),(2,'it','Jack'),(3,'marketing','Jill');
insert into pay_detail(dept_id,desig,pay_day) values(1,'trainee',500),(1,'consultant',1000),(2,'developer',2000),(3,'hr',1000),(3,'executive',3000);
create table leave_bal(emp_id int,leave_id int,days int default 0,constraint foreign key(emp_id) references emp_details(emp_id),foreign key(leave_id) references leave_type(leave_id)); 
insert into emp_details values (100, 'Jagan', 2, 'developer'), (101, 'Raj', 1, 'trainee'), (102, 'Aishwarya', 1, 'consultant'), (103, 'Rashmi', 3, 'executive');
insert into leave_bal values (100, '1',default), (100, '2',default), (100, '3',default), (100, '4',default);
insert into leave_bal values (101, '1',default), (101, '2',default), (101, '3',default), (101, '4',default);
insert into leave_bal values (102, '1',default), (102, '2',default), (102, '3',default), (102, '4',default);
insert into leave_bal values (103, '1',default), (103, '2',default), (103, '3',default), (103, '4',default);
/*delimiter |
create trigger ins_check on leuave_log before insert
as
	if exists(
		select * from leave_log join leave_bal on leave_log.leave_id = leave_bal.leave_id and leave_bal.emp_id = leave_log.leave_id where datediff(d,new.start_date,new.end_date) + days <= (select no_of_days from leave_type where leave_id = leave_log.leave_id))
	begin
	set new.approved = true;
    return;
end;    
| delimiter ;
*/

select emp_id from (select emp_id, sum(days) as total from leave_bal group by emp_id order by total) as T where T.total = 0; 

select leave_type.leave_id, leave_type.no_of_days - leave_bal.days from leave_type natural join leave_bal where leave_bal.emp_id = 101;

select emp_name from emp_details natural join leave_log where (month(start_date) = 8 or month(end_date) = 8) and approved = 1;

select dept_name from emp_details natural join(select emp_id,sum(days) as total from leave_bal group by emp_id order by total desc) as T natural join dept_details;

select emp_id from leave_bal group by emp_id having sum(days) = (select min(T.total) from (select emp_id,sum(days) as total from leave_bal group by emp_id order by total) as T);