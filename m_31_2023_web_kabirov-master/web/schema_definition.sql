create table users
(
    id          varchar(255) primary key,
    first_name  varchar(255) not null,
    second_name varchar(255) null,
    email       varchar(255) not null,
    pass        varchar(255) not null
);

create table seasons
(
    id   varchar(255) primary key,
    name varchar(255) not null
);

create table insects
(
    id         varchar(255) primary key,
    user_id    varchar(255) not null references users (id),
    season_id  varchar(255) not null references seasons (id),
    name       varchar(255) not null,
    size       int          not null,
    created_at timestamp    not null default current_timestamp
);