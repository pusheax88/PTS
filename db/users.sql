--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

INSERT INTO role VALUES (1, 'admin');
INSERT INTO role VALUES (2, 'it_spec');
INSERT INTO role VALUES (3, 'user');

INSERT INTO web_user VALUES (1, 'pts', 'admin', 1);
INSERT INTO web_user VALUES (2, 'it_spec01', 'it_spec', 2);
INSERT INTO web_user VALUES (3, 'user01', 'user', 3);


--
-- PostgreSQL database dump complete
--

