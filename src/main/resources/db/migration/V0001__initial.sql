create table users
(
    id text not null
        constraint users_pk
            primary key,
    name text default 'user'::text not null,
    avatar_uri text not null,
    password text default '0'::text not null,
    email text default 'test@gmail.com'::text not null,
    job text default ''::text not null
);

create unique index users_id_uindex
    on users (id);

create unique index users_email_uindex
    on users (email);



create table likes
(
    "from" text not null
        constraint likes_users_fk
            references users,
    "to" text not null
        constraint likes_users_fk2
            references users,
    "like" boolean not null
);


create table messages
(
    "from" text not null
        constraint messages_users_id_fk
            references users,
    "to" text not null
        constraint messages_users_id_fk_2
            references users,
    text text,
    date timestamp not null
);



INSERT INTO public.users (id, name, avatar_uri, password, email, job) VALUES ('8e2c9273-d42d-4f55-a62f-2dff030115a6', 'Andrew', 'https://i.pinimg.com/564x/09/a9/2c/09a92c1cbe440f31d1818e4fe0bcf23a.jpg', '$2a$12$6ydE5./K7OeV67i25P5Jj.HWF4/EnpULGJSA6xzFd2ffeChJqyZGG', 'andrew@gmail.com', 'Java developer');
INSERT INTO public.users (id, name, avatar_uri, password, email, job) VALUES ('00802a4b-8f1b-432b-8d5b-a310340fead4', 'Andrew', 'https://i.pinimg.com/564x/49/cb/35/49cb350dc49a7de8d098445e9b00bdca.jpg', '$2a$12$YRvl.pGtCcEmdD2hoffeqe3U/9rI90YjN0CapcyMVpYakpPlJPTfO', 'andrey.shkurenko@icloud.com', 'Full Stack developer');
INSERT INTO public.users (id, name, avatar_uri, password, email, job) VALUES ('c2ebb862-cc31-4b59-8ee7-0242d44540f7', 'Marta', 'https://i.pinimg.com/564x/18/6e/eb/186eeb2aa996def98210fbb0788e1ee3.jpg', '$2a$12$79PCfnwLziq2wUBS5U1DEOZiI73QO.0s0PBzRCbU02ftbUzOIYEji', 'marta@gmail.com', 'Recruiter');
INSERT INTO public.users (id, name, avatar_uri, password, email, job) VALUES ('6b4b2839-baea-45cc-9a42-4f2669f364b5', 'George', 'https://i.pinimg.com/564x/7d/31/56/7d31561332f0886426a38cf6e3455586.jpg', '$2a$12$OVUURJSytGJecXRPCMv/nu11VS5T0TJzEFnjRJjs9wFZyXequv5uy', 'george@gmail.com', 'C++ developer');
INSERT INTO public.users (id, name, avatar_uri, password, email, job) VALUES ('cb50e7b2-9546-483f-8957-936be8c90f2f', 'Sophia', 'https://i.pinimg.com/564x/d6/4b/fb/d64bfbe2e6ca5bdc3c0b85f3c1ac507c.jpg', '$2a$12$utYRbnn9OrMIqXZrRXKpBemZxXv.ktQyJvzLWVCzWZ0SwYe3lRex6', 'sophia@gmail.com', 'UI/UX');
INSERT INTO public.users (id, name, avatar_uri, password, email, job) VALUES ('8f1dfb44-55b6-43ed-ae4d-ad42d9744e4d', 'Nicol', 'https://i.pinimg.com/564x/d1/91/fc/d191fc691d2a824422062b58e4b0056f.jpg', '$2a$12$ekB/KrctY8MUinHmfD80QOvtsfP.vK5psSNLtbwNHGUQH8bghmfma', 'nicol@gmail.com', 'Product manager ');







