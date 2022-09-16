# MyBatis DB연결 세팅

###paging 설정 변경

- boards.xml (paging) : totalCount, ceil(count(*)/5) totalPage,
- boards.xml (findAll) : FETCH NEXT 5 ROWS ONLY
- BoardsService (게시글목록보기) : int startNum = page * 5;

안에 숫자 (5) 수정하면 보이는 갯수 달라짐

### 설정방법
- MyBatisConfig 파일 필요
- resources/mapper/*.xml 파일 필요
- Users 엔티티 필요
- UsersDao 인터페이스 생성 필요

### MariaDB 사용자 생성 및 권한 주기
```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```

### 테이블 생성
```sql

USE greendb;

create table users(
    id int primary KEY AUTO_INCREMENT,
    username varchar(20),
    password varchar(20),
    email varchar(50),
    createdAt TIMESTAMP
);

create table boards(
    id int primary KEY AUTO_INCREMENT,
    title varchar(15greendb0),
    content longtext,
    usersId int,
    createdAt TIMESTAMP,
    CONSTRAINT fk_users_id FOREIGN KEY(usersId) REFERENCES users(id)
);


```

### 더미데이터 추가
```sql
insert into users(username, password, email, createdAt) values('ssar', '1234', 'ssar@nate.com', NOW());
insert into users(username, password, email, createdAt) values('cos', '1234', 'cos@nate.com', NOW());
insert into users(username, password, email, createdAt) values('hong', '1234', 'hong@nate.com', NOW());
COMMIT;
```

### 회원 탈퇴시 null값을 익명으로 변경.
```
ALTER TABLE users CONVERT TO CHARACTER SET utf8;
ALTER TABLE boards CONVERT TO CHARACTER SET utf8;
둘다 해줘야함. 두 테이블의 collation 을 맞춰주기 위함.

안되면 utf-8설정 검색해서 처리.
SELECT b.id, b.title, b.createdAt, if(u.username IS null, '익명', u.username)as username FROM boards b
LEFT OUTER JOIN users u ON b.usersId = u.id
```