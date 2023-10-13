-- 코드를 입력하세요
SELECT product_code, sum(sales_amount * price) as sales
from product a
join offline_sale b
on a.product_id = b.product_id
group by product_code
order by sales desc, product_code