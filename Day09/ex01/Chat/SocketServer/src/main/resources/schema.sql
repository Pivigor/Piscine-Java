create schema if not exists chat;

drop table if exists chat.users;

create table if not exists chat.users
(
    id     serial primary key,
    login  text not null,
    passwd text
);

create table if not exists chat.msgs
(
    id        serial primary key,
    authorID  int  not null references chat.users (id),
    message text not null,
    datetime    time
);
