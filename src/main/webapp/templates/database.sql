DROP TABLE IF EXISTS public.shop_user CASCADE;
CREATE TABLE public.shop_user (
                             id serial NOT NULL PRIMARY KEY,
                             email text NOT NULL,
                             password text NOT NULL,
                             first_name text NOT NULL,
                             last_name text NOT NULL,
                             phone_number text NOT NULL

);

DROP TABLE IF EXISTS public.carts CASCADE ;
CREATE TABLE public.carts (
                              id serial NOT NULL PRIMARY KEY,
                              user_id bigint NOT NULL,
                              total int,
                              closed boolean
);

DROP TABLE IF EXISTS public.order_items CASCADE;
CREATE TABLE public.order_items (
                                    id serial NOT NULL PRIMARY KEY,
                                    cart_id bigint NOT NULL,
                                    prod_id bigint NOT NULL,
                                    qty int NOT NULL
);

DROP TABLE IF EXISTS public.products CASCADE;
CREATE TABLE public.products (
                                 id serial NOT NULL PRIMARY KEY,
                                 name text NOT NULL,
                                 price decimal NOT NULL,
                                 category_id int NOT NULL,
                                 currency text DEFAULT 'EUR',
                                 description text DEFAULT '',
                                 supplier_id int NOT NULL
);

DROP TABLE IF EXISTS public.categories CASCADE;
CREATE TABLE public.categories (
                                   id serial NOT NULL PRIMARY KEY,
                                   name text NOT NULL,
                                   department text DEFAULT 'Vinyl',
                                   description text DEFAULT ' '
);

DROP TABLE IF EXISTS public.supplier CASCADE;
CREATE TABLE public.supplier (
                                   id serial NOT NULL PRIMARY KEY,
                                   name text NOT NULL,
                                   description text NOT NULL

);


DROP TABLE IF EXISTS public.address CASCADE;
CREATE TABLE public.address (
                                id serial NOT NULL PRIMARY KEY,
                                country text NOT NULL,
                                city text NOT NULL,
                                street_address text NOT NULL,
                                state text NOT NULL,
                                zip text NOT NULL,
                                user_id int NOT NULL

);
ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.shop_user(id);
ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fk_cart_id FOREIGN KEY (cart_id) REFERENCES public.carts(id),
    ADD CONSTRAINT fk_prod_id FOREIGN KEY (prod_id) REFERENCES public.products(id);
ALTER TABLE ONLY public.products
    ADD CONSTRAINT  fk_category_id FOREIGN KEY (category_id) REFERENCES public.categories(id),
    ADD CONSTRAINT  fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);
ALTER TABLE ONLY public.address
    ADD CONSTRAINT fk__id FOREIGN KEY (user_id) REFERENCES public.shop_user(id);
