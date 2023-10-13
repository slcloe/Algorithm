-- 코드를 입력하세요
# with t1 as
# SELECT ingredient_type , a.flavor, total_order
#  from first_half a join icecream_info b 
#  on (a.flavor = b.flavor)
# SELECT ingredient_type sum(total_order) from t1 group by ingredient_type;


SELECT ingredient_type, sum(total_order) 
from first_half a join icecream_info b 
 on (a.flavor = b.flavor)
 group by ingredient_type
 order by sum(total_order);
