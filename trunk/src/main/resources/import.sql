--
-- JBoss, Home of Professional Open Source
-- Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements

insert into User (id, username, password, hashedPassword, email) values (1, 'Herbert', 'vergessen', '1', 'herbert_macht_dienstreisen@gmail.com')
insert into User (id, username, password, hashedPassword, email) values (2, 'George', 'nicht_anwesend', '1', 'war_gerade_afk@gmail.com')

insert into Community (id, name, visible) values (1, 'George', true)
insert into Community (id, name, visible) values (2, 'Der Rest', true)
insert into Community (id, name, visible) values (4, 'Jboss is the Best', true)
insert into Community (id, name, visible) values (5, 'George 1', true)
insert into Community (id, name, visible) values (6, 'Der Rest 1', true)
insert into Community (id, name, visible) values (7, 'Knebelmaster 1', true)
insert into Community (id, name, visible) values (8, 'Jboss is the Best 1', true)
insert into Community (id, name, visible) values (9, 'George 2', true)
insert into Community (id, name, visible) values (10, 'Der Rest 2', true)
insert into Community (id, name, visible) values (11, 'Knebelmaster 2', true)
insert into Community (id, name, visible) values (12, 'Jboss is the Best 2', true)

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