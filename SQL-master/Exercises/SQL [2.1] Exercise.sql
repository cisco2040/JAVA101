SELECT 
    c.ship_to_id, COUNT(c.ship_to_id) AS carts
FROM
    cart AS c
        INNER JOIN
    status AS s ON c.status_id = s.status_id
WHERE
    s.description <> 'created'
GROUP BY c.ship_to_id;