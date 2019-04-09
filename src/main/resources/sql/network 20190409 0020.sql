--
-- Скрипт сгенерирован Devart dbForge Studio for MySQL, Версия 8.0.80.0
-- Домашняя страница продукта: http://www.devart.com/ru/dbforge/mysql/studio
-- Дата скрипта: 09.04.2019 0:20:51
-- Версия сервера: 5.7.25-log
-- Версия клиента: 4.1
--

-- 
-- Отключение внешних ключей
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Установить режим SQL (SQL mode)
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Установка кодировки, с использованием которой клиент будет посылать запросы на сервер
--
SET NAMES 'utf8';

DROP DATABASE IF EXISTS network;

CREATE DATABASE IF NOT EXISTS network
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Установка базы данных по умолчанию
--
USE network;

--
-- Создать таблицу `user`
--
CREATE TABLE IF NOT EXISTS user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  login varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  nickname varchar(50) DEFAULT NULL,
  admin bit(1) DEFAULT b'0',
  picture blob DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = 'данные полльзователя';

--
-- Создать индекс `login` для объекта типа таблица `user`
--
ALTER TABLE user
ADD UNIQUE INDEX login (login);

--
-- Создать таблицу `room`
--
CREATE TABLE IF NOT EXISTS room (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  caption varchar(50) NOT NULL,
  defaultRoom bit(1) DEFAULT b'0',
  password varchar(50) DEFAULT NULL,
  max_user bigint(20) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = 'описание комнаты';

--
-- Создать индекс `caption` для объекта типа таблица `room`
--
ALTER TABLE room
ADD UNIQUE INDEX caption (caption);

--
-- Создать внешний ключ
--
ALTER TABLE room
ADD CONSTRAINT FK_room_to_user_id FOREIGN KEY (user_id)
REFERENCES user (id);

--
-- Создать таблицу `user_room`
--
CREATE TABLE IF NOT EXISTS user_room (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  room_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = 'соединение 2-х таблиц пользователя и комнаты для их связи';

--
-- Создать внешний ключ
--
ALTER TABLE user_room
ADD CONSTRAINT FK_to_room_id FOREIGN KEY (room_id)
REFERENCES room (id);

--
-- Создать внешний ключ
--
ALTER TABLE user_room
ADD CONSTRAINT FK_to_user_id FOREIGN KEY (user_id)
REFERENCES user (id);

--
-- Создать таблицу `message`
--
CREATE TABLE IF NOT EXISTS message (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  room_id bigint(20) NOT NULL,
  message varchar(255) DEFAULT NULL,
  message_date datetime NOT NULL,
  picture blob DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = 'описание сообщения пользователя вкомнате';

--
-- Создать внешний ключ
--
ALTER TABLE message
ADD CONSTRAINT FK_message_room_id FOREIGN KEY (room_id)
REFERENCES room (id) ON DELETE CASCADE;

--
-- Создать внешний ключ
--
ALTER TABLE message
ADD CONSTRAINT FK_message_user_id FOREIGN KEY (user_id)
REFERENCES user (id);

-- 
-- Вывод данных для таблицы user
--
INSERT INTO user VALUES
(1, 'super', 'super', 'admin', 'super', 'sa', True, NULL),
(2, 'user_second', 'user', 'user', 'second', 'us', False, NULL),
(3, 'va', 'ivan22', 'Vasya', 'Ivanov', 'Ivasya', False, NULL),
(12, 'dima', 'dima123', 'Dima', 'Petrov', 'DDDD', True, NULL);

-- 
-- Вывод данных для таблицы room
--
INSERT INTO room VALUES
(1, 1, 'Main_Boltalka', True, '', 999),
(2, 2, 'Second_boltalka', False, '', 999),
(3, 2, 'Room_third', False, '', 999);

-- 
-- Вывод данных для таблицы user_room
--
INSERT INTO user_room VALUES
(1, 1, 1),
(2, 2, 2),
(4, 2, 3),
(5, 1, 3);

-- 
-- Вывод данных для таблицы message
--
INSERT INTO message VALUES
(1, 1, 1, 'llalalalalala', '2019-03-23 10:37:22', NULL),
(2, 2, 2, 'lololololaaaa', '2019-03-26 10:37:22', NULL),
(3, 3, 2, 'tru-la-la', '2019-03-26 10:37:22', NULL),
(4, 2, 3, 'message for user 1', '2019-03-27 10:00:00', NULL),
(5, 1, 3, 'message for user 3', '2019-03-27 10:00:00', NULL);

--
-- Установка базы данных по умолчанию
--
USE network;

DELIMITER $$

--
-- Создать триггер `def_room_create`
--
CREATE
DEFINER = 'root'@'localhost'
TRIGGER def_room_create
BEFORE INSERT
ON room
FOR EACH ROW
BEGIN
  IF (SELECT
        caption
      FROM room
      WHERE caption LIKE 'Main_Boltalka') THEN
    SET NEW.defaultRoom = TRUE;
  END IF;
  IF (SELECT
        defaultRoom
      FROM room
      WHERE NEW.defaultRoom = TRUE) THEN
    SET NEW.defaultRoom = FALSE;
  END IF;
END
$$

--
-- Создать триггер `def_room_update`
--
CREATE
DEFINER = 'root'@'localhost'
TRIGGER def_room_update
BEFORE UPDATE
ON room
FOR EACH ROW
BEGIN
  IF (SELECT
        defaultRoom
      FROM room
      WHERE NEW.defaultRoom = TRUE) THEN
    SET NEW.defaultRoom = FALSE;
  END IF;
END
$$

DELIMITER ;

-- 
-- Восстановить предыдущий режим SQL (SQL mode)
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Включение внешних ключей
-- 
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;