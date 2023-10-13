-- 코드를 입력하세요
SELECT category, sum(a.sales) as total_sales from book_sales a
join book b
on a.book_id = b.book_id and year(sales_date) = 2022 and month(sales_date) = 1
group by category
order by category;