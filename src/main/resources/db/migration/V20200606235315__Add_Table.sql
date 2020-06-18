create table if not exists user (
  id int auto_increment not null,
  username char(254) not null,
  password varchar(255),
  role char(20) not null,
  is_enabled boolean not null,
  created_at datetime not null,
  created_user char(20) not null,
  updated_at datetime not null,
  updated_user char(20) not null,
  primary key (id)
);

create table if not exists todo (
  id int auto_increment not null,
  user_id int not null,
  title varchar(100) not null,
  content text,
  created_at datetime not null,
  created_user char(20) not null,
  updated_at datetime not null,
  updated_user char(20) not null,
  primary key(id),
  foreign key(user_id) references user(id)
);
