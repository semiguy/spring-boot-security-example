-- user ���̺�
CREATE TABLE IF NOT EXISTS `spring`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT, -- �ڵ�����
  `username` VARCHAR(45) NOT NULL, -- ����� �̸�
  `password` TEXT NOT NULL, -- bcrypt �Ǵ� scrypt ����� ��ȣ �ؽ�
  `algorithm` VARCHAR(45) NOT NULL, -- ���� ���ڵ��� ��ȣ�� ����� �ؽ� ��Ŀ� ���� BCRYPT �Ǵ� SCRYPT ��
  PRIMARY KEY (`id`));

-- authority ���̺�
CREATE TABLE IF NOT EXISTS `spring`.`authority` (
  `id` INT NOT NULL AUTO_INCREMENT, -- �ڵ�����
  `name` VARCHAR(45) NOT NULL, -- ������ �̸�
  `user` INT NOT NULL, -- user ���̺� ���� �ܷ� Ű
  PRIMARY KEY (`id`));

-- product ���̺�
CREATE TABLE IF NOT EXISTS `spring`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT, -- �ڵ�����
  `name` VARCHAR(45) NOT NULL, -- ��ǰ�� �̸�
  `price` VARCHAR(45) NOT NULL, -- ��ǰ�� ����
  `currency` VARCHAR(45) NOT NULL, -- ��ȭ(��: USD, EUR ��)
  PRIMARY KEY (`id`));