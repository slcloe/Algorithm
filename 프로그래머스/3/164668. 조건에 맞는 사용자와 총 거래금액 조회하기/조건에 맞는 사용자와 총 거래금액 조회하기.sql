-- 코드를 입력하세요
SELECT a.writer_id user_id, nickname, sum(price) total_sales from used_goods_board a
join used_goods_user b
on a.writer_id = b.user_id and a.status = 'DONE'
group by a.writer_id
having total_sales >= 700000
order by total_sales
;
