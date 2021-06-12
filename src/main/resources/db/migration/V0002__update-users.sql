alter table users add last_login date;

UPDATE public.users SET last_login = '2021-06-01' WHERE id = '8e2c9273-d42d-4f55-a62f-2dff030115a6';
UPDATE public.users SET last_login = '2021-06-03' WHERE id = 'c2ebb862-cc31-4b59-8ee7-0242d44540f7';
UPDATE public.users SET last_login = '2021-04-08' WHERE id = '6b4b2839-baea-45cc-9a42-4f2669f364b5';
UPDATE public.users SET last_login = '2021-05-20' WHERE id = 'cb50e7b2-9546-483f-8957-936be8c90f2f';
UPDATE public.users SET last_login = '2021-06-5' WHERE id = '8f1dfb44-55b6-43ed-ae4d-ad42d9744e4d';
UPDATE public.users SET last_login = '2021-06-11' WHERE id = '00802a4b-8f1b-432b-8d5b-a310340fead4';