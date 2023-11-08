-- 코드를 입력하세요
SELECT p.product_id, p.product_name, (p.price * o.amount) as total_sales
from food_product p join (select product_id,SUM(amount) as amount from food_order where produce_date like '2022-05%' group by product_id) o 
where p.product_id = o.product_id
order by total_sales desc, product_id;
