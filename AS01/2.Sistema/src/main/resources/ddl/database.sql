create sequence hibernate_sequence;

create table country
(
  id           bigint not null
    constraint country_pkey
      primary key,
  name         varchar(255),
  abbreviation varchar(255)
);

create table date_definition
(
  dtype        varchar(31) not null,
  id           bigint      not null
    constraint date_definition_pkey
      primary key,
  day_of_week  integer,
  month        integer,
  ordinal      integer,
  days_offset  integer,
  holiday_id   bigint      not null,
  day_of_month integer
);

create table holiday
(
  id                 bigint not null
    constraint holiday_pkey
      primary key,
  description        text,
  date_definition_id bigint
    constraint fkn1p4hdicbmwwu2se9jnnq5alm
      references date_definition,
  region_id          bigint not null
);

alter table date_definition
  add constraint fkqyt77nqy6bvndh3asl92itxjc
    foreign key (holiday_id) references holiday;

create table state
(
  id           bigint not null
    constraint state_pkey
      primary key,
  name         varchar(255),
  abbreviation varchar(255),
  country_id   bigint not null
    constraint fkghic7mqjt6qb9vq7up7awu0er
      references country
      on delete cascade
);

create table city
(
  id       bigint not null
    constraint city_pkey
      primary key,
  name     varchar(255),
  state_id bigint not null
    constraint fk6p2u50v8fg2y0js6djc6xanit
      references state
      on delete cascade
);
