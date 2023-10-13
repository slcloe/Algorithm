-- 코드를 입력하세요
with t1 AS
(
    SELECT count(*) as cnt, member_id
    from rest_review 
    group by member_id
),
t2 AS
(
    select a.member_id
    from t1 a
    join ( select * from t1 order by cnt desc limit 1) b
    on a.cnt = b.cnt
),
t3 AS
(
    select t2.member_id, member_name
    from t2
    join member_profile a
    on t2.member_id = a.member_id
)
select member_name, review_text, date_format(review_date, "%Y-%m-%d")
from t3
join rest_review a
on t3.member_id = a.member_id
order by review_date, review_text




