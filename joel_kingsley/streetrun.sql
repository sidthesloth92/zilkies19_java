 create database streetrun;
 use streetrun;
 
 create table users(
 user_id bigint primary key auto_increment,
 first_name varchar(30) not null,
 last_name varchar(30) not null,
 last_known_lat decimal(9,6) not null,
 last_known_long decimal(9,6) not null);
 
 
 create table accounts(
 user_id bigint unique not null,
 user_name varchar(30) unique not null,
 user_password varchar(20) not null,
 email varchar(50) not null,
 alternate_email varchar(50) not null,
 foreign key(user_id) references users(user_id) on delete cascade);

 
 create table groups(
 group_id bigint primary key auto_increment,
 admin_id bigint not null,
 group_name varchar(30) not null,
 foreign key(admin_id) references users(user_id) on delete cascade);

 create table messages(
 message_id bigint primary key auto_increment,
 message varchar(250) not null,
 owner_id bigint not null,
 receiver_id bigint not null,
 deleted boolean default false,
 foreign key(owner_id) references users(user_id) on delete cascade);
 
 
 create table message_group(
 message_id bigint unique not null,
 group_id bigint not null,
 foreign key(message_id) references messages(message_id) on delete cascade,
 foreign key(group_id) references groups(group_id) on delete cascade);
 
 create table user_group(
 user_id bigint not null,
 group_id bigint not null,
 foreign key(user_id) references users(user_id) on delete cascade,
 foreign key(group_id) references groups(group_id) on delete cascade);
 
 create table activity(
 activity_id bigint primary key auto_increment,
 user_id bigint not null,
 distance_traveled decimal(5,2) not null,
 time_taken time not null,
 avg_pace time not null,
 calories int not null,
 start_lat decimal(9,6) not null,
 start_long decimal(9,6) not null,
 end_lat decimal(9,6) not null,
 end_long decimal(9,6) not null,
 activity_date date not null,
 foreign key(user_id) references users(user_id) on delete cascade);
 
 create table leaderboard(
 user_id bigint unique not null,
 total_distance_traveled decimal(5,2) not null,
 cumulative_avg_pace time not null,
 foreign key(user_id) references users(user_id) on delete cascade);
 
 drop table users;
 drop table accounts;
 drop table groups;
 drop table messages;
 drop table message_group;
 drop table user_group;
 drop table activity;
 drop table leaderboard;