INSERT INTO LECTURES_SCHEDULE(ID, START, END)
VALUES (1, TIMESTAMP '2021-06-01 10:00:00', TIMESTAMP '2021-06-01 11:45:00'),
       (2, TIMESTAMP '2021-06-01 12:00:00', TIMESTAMP '2021-06-01 13:45:00'),
       (3, TIMESTAMP '2021-06-01 14:00:00', TIMESTAMP '2021-06-01 15:45:00');

INSERT INTO LECTURE(ID, MAX_MEMBERS, TITLE, LECTURE_SCHEDULES_ID)
VALUES (1, 5, 'Programowanie w C++', 1),
       (2, 5, 'Programowanie w Java', 1),
       (3, 5, 'Programowanie w Python', 1),
       (4, 5, 'Testy w C++', 2),
       (5, 5, 'Testy w Java', 2),
       (6, 5, 'Testy w Python', 2),
       (7, 5, 'Wstep do JavaScript', 3),
       (8, 5, 'Wstep do PHP', 3),
       (9, 5, 'Wstep do Kotlin', 3);

INSERT INTO USER(ID,EMAIL,LOGIN)
VALUES (1,'user1@gmail.pl','user1'),
(2,'user2@gmail.pl','user2'),
(3,'user3@gmail.pl','user3'),
(4,'user4@gmail.pl','user4'),
(5,'user5@gmail.pl','user5'),
(6,'user6@gmail.pl','user6'),
(7,'user7@gmail.pl','user7'),
(8,'user8@gmail.pl','user8');

INSERT INTO USERS_LECTURES(USER_ID,LECTURE_ID) VALUES (1,1),
(1,4),
(2,1),
(3,1),
(3,3),
(4,1),
(5,1);
