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

--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: pts
--

SELECT pg_catalog.setval('hibernate_sequence', 3, true);


--
-- Name: id_seq; Type: SEQUENCE SET; Schema: public; Owner: ytriffy
--

SELECT pg_catalog.setval('id_seq', 48, true);


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO role VALUES (1, 'admin');
INSERT INTO role VALUES (2, 'it_spec');
INSERT INTO role VALUES (3, 'user');


--
-- Data for Name: web_user; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO web_user VALUES (1, 'pts', 'admin', 1);
INSERT INTO web_user VALUES (3, 'user01', 'user', 3);
INSERT INTO web_user VALUES (2, 'it_spec01', 'it_spec', 2);


--
-- Data for Name: action; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO action VALUES (3, '2011-05-16 12:16:28.12', 'Investigation.', 1);


--
-- Data for Name: connection_point; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO connection_point VALUES ('Interface', 2011516127, 'eth1');
INSERT INTO connection_point VALUES ('Interface', 2011516128, 'eth1');


--
-- Data for Name: cp_to_cp; Type: TABLE DATA; Schema: public; Owner: pts
--



--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO location VALUES (2011516125, 'Grimwood');
INSERT INTO location VALUES (2011516123, 'Grimwood');


--
-- Data for Name: network_element; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO network_element VALUES ('Device', 2011516124, 'MyDevice2', 2011516125);
INSERT INTO network_element VALUES ('Device', 2011516122, 'MyDevice1', 2011516123);


--
-- Data for Name: device_to_interface; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO device_to_interface VALUES (2011516124, 2011516127);
INSERT INTO device_to_interface VALUES (2011516122, 2011516128);


--
-- Data for Name: property_history; Type: TABLE DATA; Schema: public; Owner: pts
--



--
-- Data for Name: history_item_to_network_element; Type: TABLE DATA; Schema: public; Owner: pts
--



--
-- Data for Name: property_value; Type: TABLE DATA; Schema: public; Owner: pts
--



--
-- Data for Name: history_item_to_property; Type: TABLE DATA; Schema: public; Owner: pts
--



--
-- Data for Name: network; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO network VALUES (2011516121, NULL, 'MyNetwork');


--
-- Data for Name: property_definition; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO property_definition VALUES ('SNMP', 2011516126, 'snmp prop1', NULL, 'path1');


--
-- Data for Name: network_element_to_property; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO network_element_to_property VALUES (2011516122, 2011516126);


--
-- Data for Name: network_to_network_element; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO network_to_network_element VALUES (2011516121, 2011516124);
INSERT INTO network_to_network_element VALUES (2011516121, 2011516122);


--
-- Data for Name: problem; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO problem VALUES (2, 'Between Sunday May 15, 02:27 and 03:45, our links swiSG2-swiLI1 and swiBU2-swiLI1 were down. As none of the involved routers seems to have been in trouble, we assume a problem with the fibers between Buchs and Vaduz at that time.', '2011-05-15 00:00:00', 'problem with the fibers', '2011-05-15 00:00:00');


--
-- Data for Name: problem_to_action; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO problem_to_action VALUES (2, 3);


--
-- Data for Name: problem_to_network_element; Type: TABLE DATA; Schema: public; Owner: pts
--



--
-- Data for Name: property_value_to_property_definition; Type: TABLE DATA; Schema: public; Owner: pts
--



--
-- Data for Name: snmp_property; Type: TABLE DATA; Schema: public; Owner: pts
--



--
-- Data for Name: ticket_status; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO ticket_status VALUES (1, 'Open');
INSERT INTO ticket_status VALUES (2, 'In Progress');


--
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO ticket VALUES (1, 'Interruption of northern fiber links to Liechtenstein.', 'Liechtenstein_2011-05-16', '2011-05-16 12:15:19.486', 2, 2);


--
-- Data for Name: ticket_to_problem; Type: TABLE DATA; Schema: public; Owner: pts
--

INSERT INTO ticket_to_problem VALUES (1, 2);


--
-- PostgreSQL database dump complete
--

