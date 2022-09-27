create table if not exists users
(
    id_user           bigserial
        constraint users_pk
            primary key,
    name_users        varchar(50)                                  not null,
    surname_users     varchar(70)                                  not null,
    is_deleted        boolean      default false                   not null,
    buys              double precision                             not null,
    login_user        varchar(100) default '3g'::character varying not null,
    password_users    varchar(200) default '4j'::character varying not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6)    not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6)    not null
);

alter table users
    owner to postgres;

create index if not exists users_name_users_index
    on users (name_users);

create index if not exists users_surname_users_index
    on users (surname_users);

create index if not exists users_buys_index
    on users (buys);

create index if not exists users_name_users_surname_users_index
    on users (name_users, surname_users);

create index if not exists users_is_deleted_index
    on users (is_deleted);

create unique index if not exists users_id_user_uindex
    on users (id_user);

create table if not exists suppliers
(
    id_suppliers      bigserial
        constraint suppliers_pk
            primary key,
    name_suppliers    varchar(50)                               not null,
    address_suppliers varchar(50)                               not null,
    phone_suppliers   varchar(50)                               not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table suppliers
    owner to postgres;

create index if not exists suppliers_address_suppliers_index
    on suppliers (address_suppliers);

create index if not exists suppliers_name_suppliers_index
    on suppliers (name_suppliers);

create unique index if not exists suppliers_id_suppliers_uindex
    on suppliers (id_suppliers);

create table if not exists phone
(
    id_phone          bigserial
        constraint phone_pk
            primary key,
    brand             varchar(50)                               not null,
    model             varchar(50)                               not null,
    color             varchar(50)                               not null,
    description       varchar(200)                              not null,
    price             double precision                          not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    in_stock          boolean      default false                not null
);

alter table phone
    owner to postgres;

create table if not exists supplied_phone
(
    id_supplied_phone integer                                   not null
        constraint supplied_phone_phone_id_phone_fk
            references phone
        constraint supplied_phone_suppliers_id_suppliers_fk
            references suppliers,
    id_phone          bigint                                    not null,
    id_suppliers      integer                                   not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table supplied_phone
    owner to postgres;

create index if not exists supplied_phone_id_supplied_phone_index
    on supplied_phone (id_supplied_phone);

create unique index if not exists phone_id_phone_uindex
    on phone (id_phone);

create index if not exists phone_brand_index
    on phone (brand);

create index if not exists phone_color_index
    on phone (color);

create index if not exists phone_id_phone_index
    on phone (id_phone);

create index if not exists phone_in_stock_index
    on phone (in_stock);

create index if not exists phone_model_index
    on phone (model);

create index if not exists phone_price_index
    on phone (price);

create table if not exists delivery_point
(
    id_point          bigserial
        constraint delivery_point_pk
            primary key,
    address_point     varchar(100)                              not null,
    hours             varchar(50)                               not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table delivery_point
    owner to postgres;

create unique index if not exists delivery_point_id_point_uindex
    on delivery_point (id_point);

create index if not exists delivery_point_address_point_index
    on delivery_point (address_point);

create index if not exists delivery_point_hours_index
    on delivery_point (hours);

create table if not exists "order"
(
    id_order          bigserial
        constraint order_pk
            primary key
        constraint order_delivery_point_id_point_fk
            references delivery_point
            on update cascade on delete cascade
        constraint order_phone_id_phone_fk
            references phone
            on update cascade on delete cascade
        constraint order_users_id_user_fk
            references users
            on update cascade on delete cascade,
    id_user           bigint                                    not null,
    id_phone          bigint                                    not null,
    id_point          bigint                                    not null,
    payment_type      varchar(20)                               not null,
    total_sum         double precision                          not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table "order"
    owner to postgres;

create unique index if not exists order_id_order_uindex
    on "order" (id_order);

create index if not exists order_id_phone_index
    on "order" (id_phone);

create index if not exists order_id_point_index
    on "order" (id_point);

create index if not exists order_payment_type_index
    on "order" (payment_type);

create index if not exists order_total_sum_index
    on "order" (total_sum);

create index if not exists order_id_user_index
    on "order" (id_user);

create table if not exists roles
(
    id_role           bigserial
        constraint role_pk
            primary key,
    role_name         varchar(20)                               not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table roles
    owner to postgres;

create unique index if not exists role_id_role_uindex
    on roles (id_role);

create index if not exists role_role_name_index
    on roles (role_name);

create table if not exists l_role_users
(
    id_roles_users bigserial
        constraint l_role_users_pk
            primary key,
    id_role        bigint not null
        constraint l_role_users_role_id_role_fk
            references roles
            on update cascade on delete cascade,
    id_user        bigint not null
        constraint l_role_users_users_id_user_fk
            references users
            on update cascade on delete cascade
);

alter table l_role_users
    owner to postgres;

create unique index if not exists l_role_users_id_roles_users_uindex
    on l_role_users (id_roles_users);

create index if not exists l_role_users_id_user_id_role_index
    on l_role_users (id_user, id_role);

