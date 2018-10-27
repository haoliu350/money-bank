--2017 01 08
--Create tables, sequences
--modified 2017 01 14

----------------------------------------------------------------------------------------------------------
--item
create table item(
item_id number,
item_model varchar2(300) not null enable,
item_sku number,
item_desc varchar2(300),
primary key (item_id)
);
CREATE SEQUENCE item_id_seq MINVALUE 1 MAXVALUE 999999999999999999999999999 START WITH 150000 INCREMENT BY 1 CACHE 20;

alter table item 
add chg_dt date;


----------------------------------------------------------------------------------------------------------
--inventory

/*
TODO:
*/
drop table inventory;

create table inventory (
inventory_id number,
item_id number not null enable, --fk
order_dtl_id number not null enable, --fk
item_num number,
item_location varchar2(200),
inv_notes varchar2(1000),
primary key (inventory_id),
CONSTRAINT item_order_id_unq_constraint UNIQUE (item_id, order_dtl_id)
);
CREATE SEQUENCE inventory_id_seq MINVALUE 1 MAXVALUE 999999999999999999999999999 START WITH 20000 INCREMENT BY 1 CACHE 20;

ALTER TABLE inventory
ADD CONSTRAINT inv_item_id_fk
   FOREIGN KEY (item_id)
   REFERENCES item (item_id);
   
ALTER TABLE inventory
ADD CONSTRAINT inv_order_dtl_id_fk
   FOREIGN KEY (order_dtl_id)
   REFERENCES order_dtl (order_dtl_id);



------------------------------------------------------------------------------------------------------------------------------------------------------
--utils


create table account_info(
account_id number,
dealer_nm varchar2(100),
username varchar2(100),
account_abrv varchar2(50),
primary key(account_id)
);

CREATE SEQUENCE account_id_seq MINVALUE 1 MAXVALUE 999999 START WITH 1 INCREMENT BY 1 NOCACHE;

alter table account_info 
add chg_dt date;


create table receiver_info(
receiver_id number,
receiver_nm varchar2(100),
receiver_desc varchar2(100),
receiver_abrv varchar2(50),
primary key(receiver_id)
);
CREATE SEQUENCE receiver_id_seq MINVALUE 1 MAXVALUE 999999 START WITH 60 INCREMENT BY 1 NOCACHE;

alter table receiver_info 
add chg_dt date;




create table payment_method(
payment_method_id number,
bank_nm varchar2(200) not null enable,
holder_nm varchar2(100) not null enable,
paymnt_mthd_apprv varchar2(50) not null enable,
primary key (payment_method_id)
);
CREATE SEQUENCE payment_method_id_seq MINVALUE 1 MAXVALUE 999999 START WITH 200 INCREMENT BY 1 NOCACHE;

alter table payment_method 
add chg_dt date;




drop table inv_status;
create table inv_status(
INV_STATUS_ID number,
INV_STATUS_NM varchar2(50),
INV_STATUS_DESC varchar2(100),
primary key (INV_STATUS_ID)
);
drop sequence INV_STATUS_ID_SEQ;
CREATE SEQUENCE INV_STATUS_ID_SEQ MINVALUE 1 MAXVALUE 999999 START WITH 20 INCREMENT BY 1 NOCACHE;

-----------------------------------------------------------------------------------------------------------
--order

drop table order_dtl;

create table order_dtl(
order_dtl_id number,
order_mstr_id number not null enable, --fk
item_id number not null enable,  --fk
item_uni_price number not null enable,
item_num number not null enable,
pickup_flag varchar2(1),
order_dtl_sum_price number, 
order_dtl_notes varchar2(1000),
primary key (order_dtl_id)
);

drop sequence order_dtl_id_seq;
CREATE SEQUENCE order_dtl_id_seq MINVALUE 1 MAXVALUE 999999999999999999999999999 START WITH 5000000 INCREMENT BY 20 CACHE 10;

ALTER TABLE order_dtl
ADD CONSTRAINT order_mstr_id_fk
   FOREIGN KEY (order_mstr_id)
   REFERENCES order_mstr (order_mstr_id);
   
ALTER TABLE order_dtl
ADD CONSTRAINT ord_dtl_item_id_fk
   FOREIGN KEY (item_id)
   REFERENCES item (item_id); 

alter table order_dtl
add chg_dt date;
   


drop table order_mstr;

create table order_mstr(
order_mstr_id number,
account_id number not null enable, --fk
account_payable number,
account_paid number,
payment_mth_id number, ---fk
ord_status_id number,  --fk
order_date date,
self_pickup_flag varchar2(1),
order_notes varchar2(1000),
primary key (order_mstr_id)
);
drop sequence order_mstr_id_seq;
CREATE SEQUENCE order_mstr_id_seq MINVALUE 1 MAXVALUE 999999999999999999999999999 START WITH 280000 INCREMENT BY 100 CACHE 10;

ALTER TABLE order_mstr
ADD CONSTRAINT order_mstr_account_id_fk
   FOREIGN KEY (account_id)
   REFERENCES account_info (account_id); 
   
ALTER TABLE order_mstr
ADD CONSTRAINT order_mstr_ord_status_id_fk
   FOREIGN KEY (ord_status_id)
   REFERENCES ord_status (ord_status_id); 
   
ALTER TABLE order_mstr
ADD CONSTRAINT order_mstr_paymnt_mth_id_fk
   FOREIGN KEY (payment_mth_id)
   REFERENCES payment_method (payment_method_id);   
   
alter table order_mstr
add (shipping_cost number, secondary_paymnt_mth_id number);

alter table order_mstr
add coupon_applied number;

alter table order_mstr
add sys_date date;

alter table order_mstr
rename column sys_date to ch_dt;
   


drop table ord_status;

create table ord_status(
ord_status_id number,
ord_status_nm varchar2(10),
ord_status_desc VARCHAR2(200),
primary key (ord_status_id)
);
/*
ENUM add data
dml.sql

ordered
order canceled
shipped
instock
delivered
received
*/


---------------------------------------------------------------------------------------------------------
--delivery

drop table delivery_dtl;

create table delivery_dtl(
delivery_dtl_id number,
delivery_mstr_id number not null enable, --fk
item_id number not null enable,  --fk
unit_deliver_price number not null enable,
item_num number,
delivery_dtl_sum_price number,
self_deliver_flag varchar2(1),
delivery_notes varchar2(1000),
primary key (delivery_dtl_id)
);

drop sequence delivery_dtl_id_seq;
CREATE SEQUENCE delivery_dtl_id_seq MINVALUE 1 MAXVALUE 999999999999999999999999999 START WITH 8000000 INCREMENT BY 5 CACHE 10;

ALTER TABLE delivery_dtl
ADD CONSTRAINT dlvy_dtl_dlvy_mstr_id_fk
   FOREIGN KEY (delivery_mstr_id)
   REFERENCES delivery_mstr (delivery_mstr_id);

ALTER TABLE delivery_dtl
ADD CONSTRAINT dlvy_dtl_item_id_fk
   FOREIGN KEY (item_id)
   REFERENCES item (item_id);

alter table delivery_dtl
add ch_dt date;
   


drop table delivery_mstr;

create table delivery_mstr(
delivery_mstr_id number,
receiver_id number not null enable, --fk
account_receivable number,
account_received number,
delivery_date date,
is_devlivery_completed varchar2(1),
delivery_ntoes varchar2(1000),
primary key (delivery_mstr_id)
);
drop sequence delivery_mstr_id_seq;
CREATE SEQUENCE delivery_mstr_id_seq MINVALUE 1 MAXVALUE 999999999999999999999999999 START WITH 150000 INCREMENT BY 100 CACHE 10;

ALTER TABLE delivery_mstr
ADD CONSTRAINT dlvy_mstr_receiver_id_fk
   FOREIGN KEY (receiver_id)
   REFERENCES receiver_info (receiver_id);

alter table delivery_mstr
add ch_dt date;


--CREATE SEQUENCE customers_seq START WITH 1000 INCREMENT BY 1 NOCACHE NOCYCLE;
/*


Method 1:

ALTER TABLE table_name
ADD CONSTRAINT constraint_name
   FOREIGN KEY (column1, column2, ... column_n)
   REFERENCES parent_table (column1, column2, ... column_n);


Method 2:

CREATE TABLE table_name
(
  column1 datatype null/not null,
  column2 datatype null/not null,
  ...

  CONSTRAINT fk_column
    FOREIGN KEY (column1, column2, ... column_n)
    REFERENCES parent_table (column1, column2, ... column_n)
);


*/


---------------------------------------------------------------------------------------------------------

create table item_distribution (
item_id number,
order_dtl_id number,
receiver_id number,
item_num number not null enable,
uni_price number not null enable,
note varchar2(100),
constraint itm_dstrbtn_composite_pk primary key(item_id, order_dtl_id, receiver_id),
constraint itm_dstrbtn_item_id_fk foreign key (item_id) references item (item_id),
constraint itm_dstrbtn_order_dtl_id_fk foreign key (order_dtl_id) references order_dtl (order_dtl_id),
constraint itm_dstrbtn_receiver_id_fk foreign key (receiver_id) references receiver_info (receiver_id)
);















commit;
/
