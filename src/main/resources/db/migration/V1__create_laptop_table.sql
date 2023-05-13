create table if not exists laptop(
id int not null auto_increment primary key,
model varchar(60) not null,
yearOfManufacture varchar(60) not null,
price double not null,
RAM int not null,
HDD int not null,
manufacturer_id int not null
);