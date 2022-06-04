create schema if not exists chat;

drop table if exists chat.users;

create table if not exists chat.users
(
    id     serial primary key,
    login  text not null,
    passwd text
);
