-- 코드를 입력하세요
WITH t1 AS
(SELECT a.book_id, a.category, a.author_id, a.price, a.published_date, b.author_name
 from book a
 join author b
 on a.author_id = b.author_id
),
t2 AS
(SELECT a.category, a.author_id, a.author_name, b.sales * a.price as total_sales
 from t1 a
 join book_sales b
 on a.book_id = b.book_id and year(sales_date) = 2022 and month(sales_date) = 1
)
SELECT author_id, author_name, category, sum(total_sales) from t2
group by author_name, category
order by author_id, category desc