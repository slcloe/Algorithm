select a.id, count(b.parent_id) as child_count
from ecoli_data a left outer join ecoli_data b
on a.id = b.parent_id
group by a.id
order by a.id