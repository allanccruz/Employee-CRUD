create table tb_employee (
  	id varchar(255) primary key,
  	name varchar(30) not null,
  	last_name varchar(50) not null,
  	email varchar(255) not null unique,
  	nis_pis varchar(11) not null unique,
  	created_at datetime,
  	updated_at datetime
  );