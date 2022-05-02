drop table if exists transaction_details;
drop table if exists transactions;
drop table if exists products;
drop table if exists category;
drop table if exists users;
drop table if exists address;

create table if not exists address(
	address_id serial primary key,
	street_address int not null,
	street_name VARCHAR (50) not null,
	city VARCHAR(50) not null,
	state VARCHAR(50) not null,
	zip_code int not null
);

create table if not exists users(
	user_id serial primary key,
	username VARCHAR(50) not null unique,
	user_password VARCHAR(50) not null,
	user_first_name VARCHAR(100) not null,
	user_last_name VARCHAR (100) not null,
	user_email VARCHAR(150) not null unique,
	user_credit_card VARCHAR(25) not null,
	user_phone VARCHAR(18),
	user_address int not null,
	constraint fk_address
	foreign key (user_address)
	references address(address_id)
);

create table if not exists category(
	category_id serial primary key,
	category_name VARCHAR(20) not null unique
);

create table if not exists products(
	product_id serial primary key,
	product_name VARCHAR(50) not null,
	product_price float not null,
	product_description VARCHAR(50),
	product_category int not null,
	constraint fk_category
	foreign key (product_category)
	references category(category_id)
);

create table if not exists transactions(
	transaction_id serial primary key,
	transaction_date VARCHAR(50),
	transaction_total int not null,
	t_user_id int not null,
	constraint fk_user_id
	foreign key (t_user_id)
	references users(user_id)
);

create table if not exists transaction_details(
	td_id serial primary key,
	t_id int not null,
	prod_id int not null,
	quantity int not null,
	tdetails_total int not null,
	constraint fk_t_id
	foreign key (t_id)
	references transactions(transaction_id),
	constraint fk_category_td
	foreign key (prod_id)
	references products(product_id)
);