ALTER TABLE registered_user ALTER COLUMN artist_id DROP NOT NULL;
ALTER TABLE registered_user ALTER COLUMN producer_id DROP NOT NULL;

ALTER TABLE regular_user ALTER COLUMN liked_songs DROP NOT NULL;
ALTER TABLE regular_user ALTER COLUMN disliked_songs DROP NOT NULL;
ALTER TABLE regular_user ALTER COLUMN competitions_voted DROP NOT NULL;

INSERT INTO registered_user (name, email, user_name, dob, location, is_regular_user, is_admin, is_artist, is_producer, artist_id, producer_id, regular_user_id)
    VALUES ('Piet', 'pietje@hatseflats.nl', 'cRazYPeTE', '12/08/1992', 'Nederland', true, false, false, false, null, null, 1234567890);

INSERT INTO registered_user (name, email, user_name, dob, location, is_admin, is_artist, is_regular_user, is_producer, artist_id, producer_id, regular_user_id)
    VALUES ('Henk', 'henkie@hatseflats.nl', 'henkiepenkie', '13/12/2000', 'Nederland', true, false, false, false, null, null, 9876543210);

INSERT INTO regular_user (id, registered_user_id, liked_songs, disliked_songs, competitions_voted)
    VALUES (1234567890, 1, null, null , null);

INSERT INTO regular_user (id, registered_user_id, liked_songs, disliked_songs, competitions_voted)
    VALUES (9876543210, 2, null, null , null);