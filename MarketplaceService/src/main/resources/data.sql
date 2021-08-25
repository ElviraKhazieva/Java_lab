insert into product (article, name, price)
values ('d35b469b-9ec8-4f1e-b606-7294bd4c074c', 'ноутбук LENOVO', 49990),
       ('39000936-77af-4ac5-a6d0-f30814eb7add', 'смартфон HUAWEI', 16990),
       ('cc9a94cc-6471-468f-85e4-7726f3a6572c', 'наушники JBL', 4990);

insert into marketplace_order (creation, customer_email, number)
values ('2021-08-25 15:23:29.531554', 'Khaz.evira@yandex.ru', 156049056),
       ('2021-08-25 15:20:41.280956', 'Khaz.evira@yandex.ru', -590168816);

insert into order_product (order_id, product_id)
values (1, 1),
       (2, 2);


