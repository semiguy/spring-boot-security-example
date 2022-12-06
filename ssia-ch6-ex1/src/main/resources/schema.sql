-- user 테이블
CREATE TABLE IF NOT EXISTS `spring`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT, -- 자동증가
  `username` VARCHAR(45) NOT NULL, -- 사용자 이름
  `password` TEXT NOT NULL, -- bcrypt 또는 scrypt 방식의 암호 해시
  `algorithm` VARCHAR(45) NOT NULL, -- 현재 레코드의 암호에 적용된 해시 방식에 따라 BCRYPT 또는 SCRYPT 값
  PRIMARY KEY (`id`));

-- authority 테이블
CREATE TABLE IF NOT EXISTS `spring`.`authority` (
  `id` INT NOT NULL AUTO_INCREMENT, -- 자동증가
  `name` VARCHAR(45) NOT NULL, -- 인증의 이름
  `user` INT NOT NULL, -- user 테이블에 대한 외래 키
  PRIMARY KEY (`id`));

-- product 테이블
CREATE TABLE IF NOT EXISTS `spring`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT, -- 자동증가
  `name` VARCHAR(45) NOT NULL, -- 제품의 이름
  `price` VARCHAR(45) NOT NULL, -- 제품의 가격
  `currency` VARCHAR(45) NOT NULL, -- 통화(예: USD, EUR 등)
  PRIMARY KEY (`id`));