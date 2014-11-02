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
insert into User (id, username, password, email) values (1, 'Herbert', 'vergessen', 'herbert_macht_dienstreisen@gmail.com')
insert into User (id, username, password, email) values (2, 'George', 'nicht_anwesend', 'war_gerade_afk@gmail.com')

insert into Community (id, name) values (1, 'George')
insert into Community (id, name) values (2, 'Der Rest')
insert into Community (id, name) values (3, 'Knebelmaster')
insert into Community (id, name) values (4, 'Jboss is the Best')

insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 2)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (1, 3)
insert into USER_HAS_COMMUNITY (user_id, community_id) values (2, 1)