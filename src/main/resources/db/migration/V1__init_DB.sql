create sequence user_seq_id start 1 increment 1;
create table usr (
  id int8 not null,
  email varchar(255),
  username varchar(255),
  password varchar(255),
  firstname varchar(255),
  lastname varchar(255),
  middlename varchar(255),
  age int4,
  active boolean,
  primary key (id)
);

create sequence post_seq_id start 1 increment 1;
create table post (
  id int8 not null,
  text varchar(255),
  title varchar(255),
  primary key (id)
);

create sequence home_seq_id start 1 increment 1;
create table home (
  id int8 not null,
  name varchar(255),
  address varchar(255),
  user_id int8,
  CONSTRAINT home_user_fk FOREIGN KEY (user_id) REFERENCES usr (id),
  primary key (id)
);

create sequence room_seq_id start 1 increment 1;
create table room (
  id int8 not null,
  name varchar(255),
  temperature float ,
  humidity float ,
  light int4,
  home_id int8,
  CONSTRAINT room_home_fk FOREIGN KEY (home_id) REFERENCES home (id),
  primary key (id)
);