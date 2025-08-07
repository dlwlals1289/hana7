SET FOREIGN_KEY_CHECKS = 0;
truncate table Reply;
truncate table BoardContent;
truncate table Board;
truncate table Member;
SET FOREIGN_KEY_CHECKS = 1;

insert into Member(nickname, email, bloodType)
values ('hongx', 'hongx@gmail.com', 'A'),
       ('kimx', 'kimx@gmail.com', 'B');


insert into Subscriber(email, nickname, pwd, social)
values('sample@gmail.com', 'sub0', '$2a$10$nApvTucgdEquHDeO5pFcte9b7eeoDtM9.gaQFk8oJssUF3ZKY1/ZS', 0),
        ('sample2@gmail.com', 'sub0', '$2a$10$nApvTucgdEquHDeO5pFcte9b7eeoDtM9.gaQFk8oJssUF3ZKY1/ZS', 0);

insert into SubscriberRole(role, email) values(0, 'sample2@gmail.com'), (2, 'sample@gmail.com');



INSERT INTO Board(hit, id, createdAt, updatedAt, writer, title)
VALUES (0, 1, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title01'),
       (0, 2, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title2'),
       (0, 3, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title3'),
       (0, 4, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title4'),
       (0, 5, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title5'),
       (0, 6, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title6'),
       (0, 7, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title7'),
       (0, 8, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title8'),
       (0, 9, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 2, 'Title9'),
       (0, 10, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 2, 'Title10');

INSERT INTO `BoardContent`(board, id, createdAt, updatedAt, content)
VALUES (1, 1, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (2, 2, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (3, 3, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (4, 4, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (5, 5, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (6, 6, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (7, 7, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (8, 8, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (9, 9, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx'),
       (10, 10, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 'xxx');

INSERT INTO `Reply`(board, id, createdAt, updatedAt, replyer, reply)
VALUES (1, 1, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 2, 'Reply1'),
       (1, 2, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 2, 'Reply2'),
       (1, 3, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 2, 'Reply3'),
       (1, 4, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 1, 'Reply4'),
       (1, 5, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 1, 'Reply5'),
       (1, 6, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 2, 'Reply6'),
       (1, 7, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 2, 'Reply7'),
       (1, 8, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 2, 'Reply8'),
       (1, 9, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 2, 'Reply9'),
       (1, 10, '2025-07-29 07:22:06', '2025-07-29 07:22:06', 2, 'Reply10');


insert into Subscriber(email, nickname, pwd, social)
values ('sample@gmail.com', 'sub0', '$2a$10$DG261y/zDkAJw/.6gHExcuBWTLc/MkQUypiUkygdp5LWkEZ7YLgS.', 0);

insert into SubscriberRole(role, email)
values (0, 'sample@gmail.com'),
       (2, 'sample@gmail.com');
