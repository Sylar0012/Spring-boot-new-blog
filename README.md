# MyBatis DB연결 세팅

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

### 회원 탈퇴시 boards의 usersId값이 null인경우 화면에 안보이는 문제
```
UPDATE boards SET usersId = '15' WHERE usersId =#{id}
15는 익명의 유저로 만들어서 해결
```