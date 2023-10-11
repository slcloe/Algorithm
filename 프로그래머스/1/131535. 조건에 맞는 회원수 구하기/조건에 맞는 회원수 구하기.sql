-- 코드를 입력하세요
# SELECT count(*) from user_info where age >= 20 & age <= 29 & YEAR(joined) = 2021;
SELECT count(*) from user_info where year(joined) = 2021 and age <= 29 and age >= 20;