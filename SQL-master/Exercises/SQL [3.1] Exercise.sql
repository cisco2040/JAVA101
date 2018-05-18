SELECT 
    sh.user AS username,
    sh.ship_to_id,
    sh.name AS ship_to,
    c.status_id,
    st.description AS status,
    COUNT(c.status_id) AS carts,
    SUM(c.cart_amount) AS carts_amount,
    AVG(c.cart_amount) AS avg_amount
FROM
    ship_to as sh
        INNER JOIN
    cart as c ON sh.ship_to_id = c.ship_to_id
        INNER JOIN
    status as st ON st.status_id = c.status_id
WHERE
    st.description = 'Delivered'
        OR st.description = 'Created'
GROUP BY ship_to , c.status_id
ORDER BY c.ship_to_id;