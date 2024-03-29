INSERT INTO authorities(name) VALUES('ROLE_USER');
INSERT INTO authorities(name) VALUES('ROLE_ARTIST');
INSERT INTO authorities(name) VALUES('ROLE_PRODUCER');
INSERT INTO authorities(name) VALUES('ROLE_ADMIN');

-- ALTER TABLE profile ALTER COLUMN liked_songs DROP NOT NULL;
-- ALTER TABLE profile ALTER COLUMN disliked_songs DROP NOT NULL;
-- ALTER TABLE profile ALTER COLUMN competitions_voted DROP NOT NULL;

-- INSERT INTO file_upload_response (file_name, content_type, url)
-- VALUES ('JackBlack.jpg', 'image/jpeg', 'http://localhost:8080/download/JackBlack.jpg'),
--        ('tenaciousD-JackBlack.jpg', 'image/jpeg', 'http://localhost:8080/download/tenaciousD-JackBlack.jpg');
--
-- INSERT INTO profile (username, profile_body_img_file_name, profile_img_file_name, first_name, last_name, dob, age, location, story)
-- VALUES ('TenaciousD', 'tenaciousD-JackBlack.jpg', 'JackBlack.jpg', 'Jack', 'Black', '01-01-1965', 57, 'usa', 'I am an American comedian, actor and musician. I am one half of the comedy and satirical rock duo Tenacious D. Which has two albums as well as a television series and a film. My acting career is extensive, starring primarily as bumbling, cocky, but internally self-conscious outsiders in comedy films. I was a member of the Frat Pack, a group of comedians who have appeared together in several Hollywood films, and have been nominated for a Golden Globe award. I have also won an MTV Movie Award, and a Nickelodeon Kids Choice Award.'),
--        ('banaan', '', '', 'ban', 'aan', '01-06-2021', 1, 'chiquita', 'nom nom nom');


INSERT INTO profile (username, first_name, last_name, dob, age, location, story)
VALUES ('TenaciousD', 'Jack', 'Black', '01-01-1965', 57, 'usa', 'I am an American comedian, actor and musician. I am one half of the comedy and satirical rock duo Tenacious D. Which has two albums as well as a television series and a film. My acting career is extensive, starring primarily as bumbling, cocky, but internally self-conscious outsiders in comedy films. I was a member of the Frat Pack, a group of comedians who have appeared together in several Hollywood films, and have been nominated for a Golden Globe award. I have also won an MTV Movie Award, and a Nickelodeon Kids Choice Award.'),
       ('banaan', 'ban', 'aan', '01-06-2021', 1, 'chiquita', 'nom nom nom');

INSERT INTO users (username, email, user_profile, password, enabled)
VALUES ('TenaciousD', 'jb@tenaciousd.com', 'TenaciousD', '$2a$12$Wl6BMguNo6fvVDkCTYUor.G5bQIsF4cjwW6kbXc.QLt1sr6s.upFa', true),
       ('banaan', 'ban@aan.nl', 'banaan', '$2a$12$rp9l6iOyraVCK/vCIA2k8eJMpQ7hVU7BKAy9M8wUuPMXS8bqx1zd2', true);

INSERT INTO user_authorities (user_username, authority_id)
VALUES ('TenaciousD', 'ROLE_ARTIST'),
       ('TenaciousD', 'ROLE_USER'),
       ('banaan', 'ROLE_USER');

