SELECT *FROM host_info
ORDER BY cpu_number DESC;

SELECT AVG(host_info.total_mem - host_usage.memory_free) AS avg_mem FROM host_info,host_usage;

SELECT date_trunc('hour', timestamp) + interval '5 min' * round(date_part('minute', timestamp) / 5.0) AS Timestamps, COUNT(*) FROM host_usage
GROUP BY Timestamps;


