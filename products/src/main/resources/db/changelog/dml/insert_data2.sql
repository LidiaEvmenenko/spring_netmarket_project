--liquibase formatted sql
--changeset lidij:add_data_users_roles_1
insert into users_roles (user_id, role_id)
values (1, 1),
(7, 1);