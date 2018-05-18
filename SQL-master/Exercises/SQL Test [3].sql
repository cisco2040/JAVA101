SELECT 
    *
FROM
    (SELECT 
        order_id, MIN(line_amount) AS min_line_amount
    FROM
        order_line
    WHERE
        status_id BETWEEN '3100' AND '3300'
    GROUP BY order_id) AS sub
WHERE
    sub.min_line_amount > 1500;