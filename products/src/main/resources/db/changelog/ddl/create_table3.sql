--liquibase formatted sql
--changeset lidij:create_table_users_roles
create table users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);