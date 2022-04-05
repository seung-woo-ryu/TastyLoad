DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

CREATE TABLE users (
  user_uid      bigint NOT NULL AUTO_INCREMENT, --사용자 PK
  id            varchar(10) NOT NULL,           --사용자 아이디
  name          varchar(10) NOT NULL,           --사용자 이름
  email         varchar(50) NOT NULL,           --사용자 이메일
  passwd        varchar(80) NOT NULL,           --사용자 비밀번호
  joined_date   date NOT NULL DEFAULT CURRENT_TIMESTAMP(), --가입 날짜
  PRIMARY KEY (user_uid),
  CONSTRAINT unq_user UNIQUE (id,email)
);

CREATE TABLE restaurants (
  restaurant_uid           bigint NOT NULL AUTO_INCREMENT, --가게 PK
  name                     varchar(10) NOT NULL,           --가게 이름
  score                    bigint NOT NULL,                --가게 점수

  PRIMARY KEY (restaurant_uid)
);

CREATE TABLE reviews (
  review_uid         bigint NOT NULL AUTO_INCREMENT, --리뷰 PK
  user_uid           bigint NOT NULL,                --리뷰 작성자 PK (users 테이블 참조)
  restaurant_uid     bigint NOT NULL,                --리뷰 맛집 PK (products 테이블 참조)
  score              bigint NOT NULL,                --리뷰 좋아요 수
  hash_value         varchar(100) NOT NULL,          --리뷰 해쉬 값
  content            varchar(1000) NOT NULL,         --리뷰 내용
  create_date        date NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 리뷰 작성된 날짜
  is_nft             boolean NOT NULL,               --NFT 존재하는지
  PRIMARY KEY (review_uid),
  CONSTRAINT fk_reviews_to_users FOREIGN KEY (user_uid) REFERENCES users (user_uid) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_reviews_to_restaurants FOREIGN KEY (restaurant_uid) REFERENCES restaurants (restaurant_uid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE comments (
  comment_uid        bigint NOT NULL AUTO_INCREMENT, --댓글 PK
  review_uid         bigint NOT NULL,                --댓글 리뷰 PK (reviews 테이블 참조)
  user_uid           bigint NOT NULL,                --댓글 작성자 PK (users 테이블 참조)
  content            varchar(100) NOT NULL,          --댓글 내용
  created_date       date NOT NULL DEFAULT CURRENT_TIMESTAMP(), --댓글 작성된 날짜
  PRIMARY KEY (comment_uid),
  CONSTRAINT fk_comments_to_users FOREIGN KEY (user_uid) REFERENCES users (user_uid) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_comments_to_reviews FOREIGN KEY (review_uid) REFERENCES reviews (review_uid) ON DELETE CASCADE ON UPDATE CASCADE
);
