create table if not exists "questions_and_answers"
(
    id int not null primary key,
    question text not null,
    answer text not null,
    usage_marker boolean not null
);