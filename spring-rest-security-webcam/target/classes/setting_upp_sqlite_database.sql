drop table if exists app_activity_log;

CREATE TABLE IF NOT EXISTS app_activity_log (
  log_id  INTEGER PRIMARY KEY AUTOINCREMENT,
  username VARCHAR(45) not null,
  user_ip VARCHAR(45),
  photos_sent VARCHAR(100),
  date_accessed DATETIME);

CREATE TABLE IF NOT EXISTS images (
  image_id  INTEGER PRIMARY KEY AUTOINCREMENT,
  filename VARCHAR(100),
  date_recorded DATETIME,
  username VARCHAR(45) not null,
  user_ip VARCHAR(45));

insert into app_activity_log (username, date_accessed,photos_sent)
values ('hjbello',DATETIME('2017-07-09T00:00:00'),'1.jpg');

insert into images (filename, date_recorded, username)
values ('1.jpg',DATETIME('2017-07-09T00:00:00'),'hjbello');

select * from app_activity_log;

select * from images;

