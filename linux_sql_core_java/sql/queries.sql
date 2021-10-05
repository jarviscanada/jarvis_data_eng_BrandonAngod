/* Retrieves the list from host_info and order it by the number of cores*/
SELECT *FROM host_info
ORDER BY cpu_number DESC;

/* Retrieves the average from total memory subtracted by free memory giving you the used memory.*/
SELECT AVG(host_info.total_mem - host_usage.memory_free) AS avg_mem FROM host_info,host_usage;

/* Rounds time to the closest 5th minute and groups them to let user know how many times there is a timestamp within there*/
SELECT date_trunc('hour', timestamp) + interval '5 min' * round(date_part('minute', timestamp) / 5.0) AS Timestamps, COUNT(*) FROM host_usage
GROUP BY Timestamps;


