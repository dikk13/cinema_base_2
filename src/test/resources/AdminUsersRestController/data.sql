insert into users (id, avatar_url, birthday, email, enabled, first_name, last_name, password)
values ('100', '/uploads/users/avatar/100', '2023-01-12', 'email100@mail.ru', 'true', 'Имя100', 'Фамилия100', 'password'),
       ('101', '/uploads/users/avatar/101', '2023-01-12', 'email101@mail.ru', 'true', 'Имя101', 'Фамилия101', 'password'),
       ('102', '/uploads/users/avatar/102', '2023-01-12', 'email102@mail.ru', 'true', 'Имя102', 'Фамилия102', 'password');

insert into role (id, role) values ('1', 'ADMIN'),
                                   ('2', 'USER'),
                                   ('3', 'PUBLICIST');

insert into user_roles (user_id, role_id) values ('100', '1'),
                                                 ('100', '2'),
                                                 ('101', '2'),
                                                 ('102', '2'),
                                                 ('102', '3');

