create table post (
    id bigserial primary key,
    author_id bigint references simple_user(id),
    text_content varchar(500),
    date timestamptz,
    likes_count integer,
    comments_count integer,
    constraint likes_count_positive check (likes_count >= 0 ),
    constraint comments_count_positive check (comments_count >= 0 )
);

create table simple_user(
  id bigserial primary key,
  nickname varchar(30),
  first_name varchar(30),
  last_name varchar(30),
  birth_date date,
  email varchar(100),
  hash_password varchar(100)
);

create table subscription(
    user_id bigint references simple_user(id),
    follower_id bigint references simple_user(id),
    unique (user_id, follower_id),
    constraint not_equals check (user_id != follower_id)
);

create table post_likes(
    user_id bigint references simple_user(id),
    post_id  bigint references  post(id),
    unique (user_id, post_id)
);

create table comment(
    id bigserial primary key,
    author_id bigint references simple_user(id),
    post_id bigint references post(id),
    text varchar(250),
    date date
);

create table hashtag(
    id bigserial primary key,
    tag varchar(30),
    post_id bigint references post(id)
);

create table post_attachment(
    post_id bigint references post(id),
    path varchar(100)
);

create table comment_attachment(
    comment_id bigint references comment(id),
    path varchar(100)
);