drop table if exists app_activity_log;

CREATE  TABLE app_activity_log (
  log_id  INTEGER PRIMARY KEY AUTOINCREMENT,
  username VARCHAR(45) not null,
  user_ip VARCHAR(45),
  photos_sent VARCHAR(100),
  date_accessed DATETIME);

insert into app_activity_log (username, date_accessed,photos_sent)
values ('hjbello',DATETIME('2017-07-09T00:00:00'),'1.jpg');

select * from app_activity_log;
