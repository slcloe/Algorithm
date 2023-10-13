-- 코드를 입력하세요
# SELECT car_id,
#     IF (car_id in 
#         (select car_id 
#          from car_rental_company_rental_history 
#          where start_date <= '2022-10-16' 
#          and end_date >= '2022-10-16')
#        , '대여중' , '대여 가능' 
#        ) as AVAILABILITY
# FROM car_rental_company_rental_history 
# group by car_id
# order by car_id desc

# SELECT car_id,
#     IF (car_id in 
#         (SELECT car_id 
#          FROM car_rental_company_rental_history
#         WHERE '2022-10-16' between start_date and end_date)
#        ,'대여중', '대여 가능')
#        AS AVAILABILITY
# from car_rental_company_rental_history
# group by car_id
# order by car_id desc

with t1 AS (
select distinct car_id , '대여중'
from car_rental_company_rental_history
where '2022-10-16' between start_date and end_date
order by car_id )
select * from t1
union all
select distinct c.car_id, '대여 가능'
from car_rental_company_rental_history c
left outer join t1 on c.car_id = t1.car_id
where t1.car_id is null
order by car_id desc;




# select distinct car_id , '대여 가능'
# from car_rental_company_rental_history
# where '2022-10-16' not between start_date and end_date
# union t1
# order by car_id;