SELECT 
    *
FROM
    item
WHERE
    description LIKE '%set%'
        AND unit_price BETWEEN 500 AND 23000;