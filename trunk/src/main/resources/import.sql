--
-- Persistence start up data import sql file.
-- You can use this file to load seed data into the database using SQL statements. 
-- Set hibernate.hbm2ddl.auto = create-drop in persistence.xml configuration to run this statements.
-- @author SWD12
--

-- TODO: Hybernate errors occur while application deploy, check how they can be solved. Commented out 
-- all my changes and tries to not disturb any body! Anyway its not sure this file is the reason.

--drop table if exists USER;

--create table USER 
--(
--	id BIGINT(19) NOT NULL,	
--	username VARCHAR(255),
--	password VARCHAR(256) NOT NULL,
--	hashedPassword VARCHAR(255),
--	email VARCHAR(255),
--	telephone VARCHAR(255),
--	token VARCHAR(255) 
--);


insert into USER (id, username, password, hashedPassword, email) values (1, 'Herbert', 'vergessen', '1', 'herbert_macht_dienstreisen@gmail.com')
insert into USER (id, username, password, hashedPassword, email) values (2, 'George', 'nicht_anwesend', '1', 'war_gerade_afk@gmail.com')

--drop table if exists COMMUNITY;

--create table COMMUNITY 
--(
--	id BIGINT(19) NOT NULL,	
--	name VARCHAR(255),
--	visible BOOLEAN(1)
--);

insert into COMMUNITY (id, name, visible) values (1, 'George', true)
insert into COMMUNITY (id, name, visible) values (2, 'Der Rest', true)
insert into COMMUNITY (id, name, visible) values (4, 'Jboss is the Best', true)
insert into COMMUNITY (id, name, visible) values (5, 'George 1', true)
insert into COMMUNITY (id, name, visible) values (6, 'Der Rest 1', true)
insert into COMMUNITY (id, name, visible) values (7, 'Knebelmaster 1', true)
insert into COMMUNITY (id, name, visible) values (8, 'Jboss is the Best 1', true)
insert into COMMUNITY (id, name, visible) values (9, 'George 2', true)
insert into COMMUNITY (id, name, visible) values (10, 'Der Rest 2', true)
insert into COMMUNITY (id, name, visible) values (11, 'Knebelmaster 2', true)
insert into COMMUNITY (id, name, visible) values (12, 'Jboss is the Best 2', true)

--drop table if exists USER_HAS_COMMUNITY;

--create table USER_HAS_COMMUNITY 
--(
--	user_id BIGINT(19) NOT NULL,	
--	community_id BIGINT(19) NOT NULL
--);

insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 1)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 2)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 3)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 4)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 5)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 6)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 7)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 8)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 9)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 10)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 11)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 12)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (2, 9)