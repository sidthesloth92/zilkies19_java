create database ems;
use ems;

show tables;

create table employee(
	emp_id bigint primary key auto_increment,
    emp_name varchar(25) not null,
    emp_password varchar(64) not null,
    account_type varchar(30) not null,
    gender varchar(10) not null,
    marital_status varchar(20) not null,
    designation_id bigint not null,
    dob date not null,
    doj date not null,
    highest_qualification varchar(30) not null,
    blood_group varchar(10) not null,
    pan varchar(12) unique not null,
    aadhar varchar(12) unique not null,
    uan varchar(12) unique not null,
    present_address varchar(100),
    permanent_address varchar(100),
    emp_status varchar(20) not null default "PROBATION",
    foreign key(designation_id) references designation(designation_id) on delete cascade);
    
    
-- drop table employee;
-- drop table designation;
-- desc employee;
-- drop table experience ;
-- drop table emergency_contact;

create table designation (
	designation_id bigint primary key auto_increment,
    designation_name varchar(30) not null);


    
insert into designation values(default,"Developer Trainee");
insert into designation values(default,"Trainee Consultant");
insert into designation values(default,"Junior Developer");
insert into designation values(default,"Senior Developer");

insert into unit values(default,"QA Testing");
insert into unit values(default,"Mobile Development");
insert into unit values(default,"Web Development");
    
create table skill (
	skill_id bigint primary key auto_increment,
    skill_name varchar(30) not null);
    
insert into skill values(default,"C++");
insert into skill values(default,"C#");
insert into skill values(default,"Java");

select * from skill;
    
create table experience (
	emp_id bigint not null,
    skill_id bigint not null,
    foreign key(emp_id) references employee(emp_id) on delete cascade,
    foreign key(skill_id) references skill(skill_id) on delete cascade);
    

    
create table phone (
	phone_id bigint primary key auto_increment,
    emp_id bigint not null,
    phone_number varchar(15) not null,
    phone_type varchar(10) not null,
    foreign key(emp_id) references employee(emp_id) on delete cascade);
    
create table emergency_contact(
	emergency_contact_id bigint primary key auto_increment,
    emp_id bigint not null, 
    emergency_contact_phone varchar(15) not null, 
    emergency_contact_name varchar(25) not null,
    foreign key(emp_id) references employee(emp_id) on delete cascade);
    
create table mail(
	mail_id bigint primary key auto_increment,
	emp_id bigint not null,
    mail_address varchar(254) unique not null,
    mail_type varchar(20) not null,
    foreign key(emp_id) references employee(emp_id) on delete cascade);
    
create table project(
	project_id bigint primary key auto_increment,
    project_name varchar(30) not null,
    location varchar(30) not null,
    reporting_manager_id bigint not null,
    foreign key(reporting_manager_id) references employee(emp_id) on delete cascade);
    
create table unit (
	unit_id bigint primary key auto_increment,
    unit_name varchar(25) not null);
    
create table assign (
	assign_id bigint primary key auto_increment,
    assign_date date not null,
    project_id bigint not null,
    emp_id bigint not null,
    unit_id bigint not null,
    foreign key(emp_id) references employee(emp_id) on delete cascade,
    foreign key(project_id) references project(project_id) on delete cascade,
    foreign key(unit_id) references unit(unit_id) on delete cascade);
    
create table grievance (
	grievance_id bigint primary key not null,
    emp_id bigint not null,
    grievance_message varchar(25) not null,
    emp_response varchar(15) not null default "APPEALED",
    admin_response varchar(15) not null default "PENDING",
    grievance_status varchar(15) not null default "OPEN",
    foreign key(emp_id) references employee(emp_id) on delete cascade);
    


delete from employee;
delete from designation;
delete from skill;
delete from experience;
delete from phone;
delete from emergency_contact;
delete from mail;
delete from project;
delete from unit;
delete from assign;
delete from grievance;