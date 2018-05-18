SET @prepSQL = NULL;

SELECT GROUP_CONCAT(
	DISTINCT CONCAT ('COUNT(IF(s.description=''',s.description,''', s.description, NULL)) AS ',	LCASE(REPLACE(s.description," ","_"))	
    ) ORDER BY s.status_id
) INTO @prepSQL FROM orders AS o INNER JOIN status AS s ON s.status_id = o.status_id;

SET @prepSQL = CONCAT('SELECT o.payment_method_id, ', @prepSQL, ' FROM
    orders AS o
        INNER JOIN
    status AS s ON o.status_id = s.status_id 
    WHERE EXTRACT(year FROM order_date) = ''2015''
	GROUP BY o.payment_method_id
    ORDER BY o.payment_method_id');
/*SELECT @prepSQL;*/

PREPARE statement FROM @prepSQL;
EXECUTE statement;
DEALLOCATE PREPARE statement;
/*SELECT 
    o.payment_method_id, COUNT(IF (s.description='created',s.description,NULL)) as created,
    COUNT(IF (s.description='delivered',s.description,NULL)) as delivered,
    COUNT(IF (s.description='partially delivered',s.description,NULL)) as partially_delivered,
    COUNT(IF (s.description='canceled',s.description,NULL)) as canceled
FROM
    orders AS o
        INNER JOIN
    status AS s ON o.status_id = s.status_id 
    WHERE EXTRACT(year FROM order_date) = '2015'
	GROUP BY o.payment_method_id
    ORDER BY o.payment_method_id;
*/