select 
    u.user_id,
    u.username,
    td.training_id,
    td.training_date,
    count(*) count
from User u
join Training_details td 
on u.user_id = td.user_id
group by
    u.user_id,
    u.username,
    td.training_id,
    td.training_date
having count(*) > 1
order by td.training_date desc