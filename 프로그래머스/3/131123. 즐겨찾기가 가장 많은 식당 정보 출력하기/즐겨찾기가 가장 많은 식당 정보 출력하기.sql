-- 코드를 입력하세요
# SELECT food_type, rest_id, rest_name, MAX(favorites) 
# from rest_info
# group by food_type
# order by food_type desc


# a.food_type, a.rest_id, a.rest_name, a.favorites
select  a.food_type, a.rest_id, a.rest_name, a.favorites
from rest_info a
join 
(
select max(favorites) fa, food_type ft from rest_info 
group by food_type 
) b
on b.fa = a.favorites and b.ft = a.food_type
order by food_type desc 
 
