insert into users values(101, 'abc@udemy.com', 'abc', 'Mateus', 'admin', 'ssn101', 'abc', 'address 1');
insert into users values(202, 'def@udemy.com', 'def', 'Mateus', 'admin', 'ssn202', 'def', 'address 2');
insert into users values(303, 'ghi@udemy.com', 'ghi', 'Mateus', 'admin', 'ssn303', 'ghi', 'address 3');
insert into orders (order_id, description, user_id) values(10, 'order1', 101);
insert into orders (order_id, description, user_id) values(20, 'order2', 202);
insert into orders (order_id, description, user_id) values(30, 'order3', 303);
insert into orders (order_id, description, user_id) values(11, 'order11', 101);
insert into orders (order_id, description, user_id) values(12, 'order12', 101);
insert into orders (order_id, description, user_id) values(31, 'order31', 303);