insert into smsplan (id,name,free_messages,price_per_messaage) values (1,'Basic',0,0.001);
insert into smsplan (id,name,free_messages,price_per_messaage) values (2,'Silver',100,0.002);
insert into smsplan (id,name,free_messages,price_per_messaage) values (3,'Gold',1000,0.003);

insert into customer (id,name,plan_id) values (101,'Bank',2);
insert into customer (id,name,plan_id) values (102,'Shop',3);