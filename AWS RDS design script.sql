drop schema if exists timesheet;
create schema timesheet;
use timesheet;

create table employees (
	id int primary key auto_increment,
    email varchar(50) unique,
    password varchar(50)
);

create table documents (
	id int primary key auto_increment,
    employeeId int,
    timesheetId int,
    type varchar(50),
    link varchar(100)
);
alter table documents add foreign key (employeeId) references employees(id);

create table contacts (
	id int primary key auto_increment,
    employeeId int,
	name varchar(50),
	phone varchar(50),
	address varchar(50),
	isEmergency boolean
);
alter table contacts add foreign key (employeeId) references employees(id);
