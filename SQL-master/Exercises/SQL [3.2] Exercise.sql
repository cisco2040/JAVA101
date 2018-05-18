SELECT 
    c.description AS category,
    u.description AS uom,
    COUNT(c.description) AS items
FROM
    item as i
        INNER JOIN
    category_item as ci ON i.item_id = ci.item_id
        INNER JOIN
    category as c ON c.category_id = ci.category_id
        INNER JOIN
    uom as u ON i.uom_id = u.uom_id
GROUP BY category , uom
ORDER BY category , uom;