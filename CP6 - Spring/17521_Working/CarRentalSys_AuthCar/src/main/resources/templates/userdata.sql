
insert into ROLES (NAME)  values  ('ADMIN');
insert into ROLES (NAME)  values  ('MANAGER');
insert into ROLES (NAME)  values  ('EMPLOYEE');

insert into USERS (USERNAME, PASSWORD, ENABLED)  values  ('haha', '$2y$12$X9gC1vxNZwPWXefptoWcZO9Ufu9WRlUhSqFDJ0iEi6oukU/kQJL/6', 1);
insert into USERS (USERNAME, PASSWORD, ENABLED)  values  ('jems', '$2y$12$404PvmiYTHj7IA4WhQM8D.IlrKN9XSRzEH6swKp70zu/fVYvE8HrS', 1);
insert into USERS (USERNAME, PASSWORD, ENABLED)  values  ('sridhar', '$2y$12$XQbLg3mItcw75DfNBrp9kOhtafGJcMtACJZ/7mIo8N5rQYucdpdmW', 1);

insert into CAR_STATUS (CAR_STTS_NAME)  values  ('AVAILABLE');
insert into CAR_STATUS (CAR_STTS_NAME)  values  ('BOOKED');
insert into CAR_STATUS (CAR_STTS_NAME)  values  ('MAINTENANCE');