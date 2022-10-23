IF OBJECT_ID(N'users', N'U') IS NOT NULL
    DROP TABLE  users;
GO

IF OBJECT_ID(N'roles', N'U') IS NOT NULL
    DROP TABLE  roles;
GO

IF OBJECT_ID(N'users_roles', N'U') IS NOT NULL
    DROP TABLE  users_roles;
GO

IF OBJECT_ID(N'roles_users', N'U') IS NOT NULL
    DROP TABLE  roles_users;
GO

-- auto-generated definition
create table roles
(
    id   int auto_increment
        primary key,
    role varchar(255) null,
    constraint UK_g50w4r0ru3g9uf6i6fr4kpro8
        unique (role)
);

-- auto-generated definition
create table users
(
    id         int auto_increment
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email)
);

-- auto-generated definition
create table users_roles
(
    user_id  int not null,
    roles_id int not null,
    primary key (user_id, roles_id),
    constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id) references users (id),
    constraint FKa62j07k5mhgifpp955h37ponj
        foreign key (roles_id) references roles (id)
);

-- auto-generated definition
create table roles_users
(
    role_id  int not null,
    users_id int not null,
    primary key (role_id, users_id),
    constraint FK4glr8k8swy5nti6n5x35ofucj
        foreign key (users_id) references users (id),
    constraint FKsmos02hm32191ogexm2ljik9x
        foreign key (role_id) references roles (id)
);

