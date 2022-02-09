insert into users values (22333444,'juan@mail.com','juan','perez','5F4DCC3B5AA765D61D8327DEB882CF99');
insert into users values (22222222,'jose@mail.com','jose','perez','5F4DCC3B5AA765D61D8327DEB882CF99');

insert into tokens values(1,now(),'abc',22333444);

insert into categories values(1,'comestibles','alimentos bebidas','comestibles.image.url');
insert into categories values(2,'electronica','todo tipo de productos electronicos','electronica.image.url');
insert into categories values(3,'medicamento','medicamentos para todas las dolencias','medicamentos.image.url');
insert into categories values(4,'ropa','Las mejores prendas','ropa.image.url');

insert into products values(1,'coca cola 2lt','coca.image.url','coca',150.00, 1);
insert into products values(2, 'compu gamer', 'compugamer.image.url','compu gamer',200000.00,2);
insert into products values(3, 'compu home', 'compuhome.image.url','compu home',100000.00,2);
insert into products values(4, 'migral', 'migral.image.url','migral',200.00,3);


