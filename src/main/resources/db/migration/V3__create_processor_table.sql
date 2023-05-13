create table if not exists processor(
id int not null auto_increment primary key,
brand varchar(60) not null,
model varchar(60) not null,
numberOfCores int not null,
processorFrequency int not null
);