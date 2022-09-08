INSERT INTO authorities(name) VALUES('ROLE_USER');
INSERT INTO authorities(name) VALUES('ROLE_ARTIST');
INSERT INTO authorities(name) VALUES('ROLE_PRODUCER');
INSERT INTO authorities(name) VALUES('ROLE_ADMIN');

-- ALTER TABLE profile ALTER COLUMN liked_songs DROP NOT NULL;
-- ALTER TABLE profile ALTER COLUMN disliked_songs DROP NOT NULL;
-- ALTER TABLE profile ALTER COLUMN competitions_voted DROP NOT NULL;

-- INSERT INTO registered_user (name, email, username, dob, location, artist_id, producer_id, regular_user_id, enabled)
--     VALUES ('Piet', 'pietje@hatseflats.nl', 'cRazYPeTE', '12/08/1992', 'Nederland', null, null, 1234567890, true);
--
-- INSERT INTO registered_user (name, email, username, dob, location,  artist_id, producer_id, regular_user_id, enabled)
--     VALUES ('Henk', 'henkie@hatseflats.nl', 'henkiepenkie', '13/12/2000', 'Nederland',  null, null, 9876543210, true);
--
-- INSERT INTO regular_user (id, registered_user_id, username, liked_songs, disliked_songs, competitions_voted)
--     VALUES (1234567890, 1, 'cRazYPeTE', null, null , null);
--
-- INSERT INTO regular_user (id, registered_user_id, username, liked_songs, disliked_songs, competitions_voted)
--     VALUES (9876543210, 2, 'henkiepenkie', null, null , null);