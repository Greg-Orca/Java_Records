INSERT INTO categories (id, name, description)
VALUES (DEFAULT, 'Techno', 'Techno is a genre of electronic dance music (EDM) which is generally produced for use in a continuous DJ set, with tempo often varying between 120 and 150 beats per minute (bpm).'),
       (DEFAULT, 'House', 'House is a genre of electronic dance music characterized by a repetitive four-on-the-floor beat and a typical tempo of 115 to 130 beats per minute.'),
       (DEFAULT, 'Electronic', 'electronic music, any music involving electronic processing, such as recording and editing on tape, and whose reproduction involves the use of loudspeakers.'),
       (DEFAULT, 'Metal', 'Rock music, also called rock and roll, rock & roll, or rock n roll, is a form of popular music that emerged in the 1950s and is defined as “a form of music with a strong beat”—it is difficult to be much more explicit about it.');



INSERT INTO supplier (name, description)
VALUES ('Amazon', 'Bezos');

INSERT INTO products (id, name, price, category_id, supplier_id)
VALUES (DEFAULT, 'I Hate Models - Intergalactic Emotional Breakdown', 25.99, 1, 1), (DEFAULT, 'Four Tet - There Is Love In You', 28.99, 2, 1), (DEFAULT, 'Floating Points - Crush',30.00,3, 1),
       (DEFAULT,'Rammstein - Deutschland', 22.99, 4, 1), (DEFAULT, 'Nils Frahm - All Melody', 28.99, 3, 1),
       (DEFAULT, 'Floating Points & Pharoah Sanders - Promises', 34.00, 3, 1),
       (DEFAULT, 'Leon Vynehall - Music For The Uninvited', 14.00, 2, 1);

INSERT INTO shop_user (email, password, first_name, last_name, phone_number)
VALUES ('java@saveme.com', 'SOS', 'Send', 'Help', '+36/302345642');

INSERT INTO carts (user_id, total, closed)
VALUES (1, 0.00, false);

