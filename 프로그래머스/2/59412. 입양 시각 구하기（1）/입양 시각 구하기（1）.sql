-- 코드를 입력하세요
SELECT hour(datetime) as `HOUR`, count(*) as `COUNT`
from animal_outs 
WHERE hour(datetime) BETWEEN 9 AND 19
group by hour(datetime)
order by hour(datetime)
