create table category (id int4 not null, name varchar(255), primary key (id));
create table joke (id int4 not null, content varchar(255), dislikes int4 not null, likes int4 not null, category_id int4 not null, primary key (id));;
create sequence category_seq start 1 increment 1;
create sequence joke_seq start 1 increment 1;

insert into category values (nextval('category_seq'), 'mujo');
insert into category values (nextval('category_seq'), 'policajac');
insert into category values (nextval('category_seq'), 'plavuša');

insert into joke values (nextval('joke_seq'), 'ide mujo u kino', 0, 0, (select id from category where name = 'mujo'));
insert into joke values (nextval('joke_seq'), 'ide mujo na more', 0, 0, (select id from category where name = 'mujo'));
insert into joke values (nextval('joke_seq'), 'ide mujo kod brata', 0, 0, (select id from category where name = 'mujo'));
insert into joke values (nextval('joke_seq'), 'ide mujo doma', 0, 0, (select id from category where name = 'mujo'));
insert into joke values (nextval('joke_seq'), 'ide mujo na plažu', 0, 0, (select id from category where name = 'mujo'));

insert into joke values (nextval('joke_seq'), 'ide policajac', 0, 0, (select id from category where name = 'policajac'));
insert into joke values (nextval('joke_seq'), 'pita policajac', 0, 0, (select id from category where name = 'policajac'));
insert into joke values (nextval('joke_seq'), 'kaže policajac', 0, 0, (select id from category where name = 'policajac'));

insert into joke values (nextval('joke_seq'), 'gleda plavuša', 0, 0, (select id from category where name = 'plavuša'));