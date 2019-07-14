create table accounts (
		accountId int(11) not null,
        username varchar(20) ,
		password varchar(20) not null,
        active bit not null,
        userrole varchar(20) not null,
        primary key (accountId)
    );
 
    create table orderdetails (
        orderDetailId int(11) not null,
		orderId int(11) not null,
        productId int(11) not null,
		quantity integer not null,
		price double precision not null,
        amount double precision not null,
        primary key (orderDetailId)
    );
 
    create table orders (
        orderId int(11) not null,
		orderDate datetime not null,
        orderNum integer not null,
        amount double precision not null,
		customerName varchar(255) not null,
        customerAddress varchar(255) not null,
        customeremail varchar(128) not null,
        customerPhone varchar(128) not null,
        primary key (orderId)
    );
 
    create table products (
		productId int(11) not null,
        code varchar(20) not null,
		name varchar(255) not null,
		price double precision not null,
		image longblob,
        createDate datetime not null,
        primary key (productId)
    );
 
    alter table orders
        add constraint orderNum  unique (orderNum);
 
    alter table orderdetails
        add constraint FK_ORDER_DETAIL_ORD
        foreign key (orderId)
        references orders (orderId);
 
    alter table orderdetails
        add constraint FK_ORDER_DETAIL_PROD
        foreign key (productId)
        references products (productId);
 
---------------------------------------
INSERT INTO accounts (accountId, username, password, active, userrole) 
VALUES (1, 'employee1', '123', 1, 'EMPLOYEE');

INSERT INTO accounts (accountId, username, password, active, userrole) 
VALUES (2, 'manager1', '123', 1, 'MANAGER');
 
----------------
INSERT INTO products (productId, code, name, price, createDate)
VALUES (1, 'S001', 'Core Java', '100', current_timestamp());
 
INSERT INTO products (productId, code, name, price, createDate)
values (2, 'S002', 'Spring for Beginners', 50, current_timestamp() );
 
INSERT INTO products (productId, code, name, price, createDate)
values (3, 'S003', 'Swift for Beginners', 120, current_timestamp() );
 
INSERT INTO products (productId, code, name, price, createDate)
values (4, 'S004', 'Oracle XML Parser', 120, current_timestamp() );
 
INSERT INTO products (productId, code, name, price, createDate)
values (5, 'S005', 'CSharp Tutorial for Beginers', 110, current_timestamp() );