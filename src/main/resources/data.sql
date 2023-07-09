--JPA 기본 데이터, 시스템 실행시 SQL 파일 실행

insert into user values(1000, sysdate(), 'User1', 'test1', '701010-111111');
insert into user values(1001, sysdate(), 'User2', 'test2', '701010-222222');
insert into user values(1002, sysdate(), 'User3', 'test3', '701010-333333');

insert into post values(1000, 'My first post', 1000);
insert into post values(1001, 'My Second post', 1000);
