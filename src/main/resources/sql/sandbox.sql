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
