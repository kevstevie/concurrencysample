create table if not exists money
(
    id    bigint primary key auto_increment,
    money bigint
);

create table if not exists conc
(
    id bigint primary key auto_increment
);

insert into money (money)
values (0);

insert into conc (id)
values (1);
