
--changeset nvoxland:1
create table book (
id int primary key,
name varchar(255)
);

--changeset nvoxland:2
insert into book (id, name) values ('1', 'test text1');

--changeset nvoxland:3
insert into book (id,  name) values ('2', 'test text2');