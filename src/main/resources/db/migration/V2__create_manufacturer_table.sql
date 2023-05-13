create table if not exists manufacturer(
id int not null auto_increment primary key,
name varchar(60) not null,
yearOfFoundation varchar(60) not null,
workersCount int not null,
annualBudget double not null
);