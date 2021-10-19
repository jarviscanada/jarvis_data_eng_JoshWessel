-- 1. Group hosts by hardware info
SELECT
  cpu_number AS cpu_number,
  id AS host_id,
  total_mem AS total_memory
FROM host_info
ORDER BY total_memory DESC;

-- 2. Average memory usage
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

SELECT
  id AS host_id,
  hostname AS host_name,
  round5(host_usage.timestamp_) AS rounded_timestamp,
  AVG(ROUND((host_info.total_mem - (host_usage.memory_free * 1000)) / host_info.total_mem,4)) AS avg_used_mem_percentage
FROM host_info
LEFT JOIN host_usage ON host_info.id = host_usage.host_id
GROUP BY id, rounded_timestamp;

-- 3. Detect host failure
SELECT
  host_id AS host_id,
  round5(timestamp_) AS rounded_timestamp,
  COUNT(round5(timestamp_)) AS data_points
FROM host_usage
GROUP BY host_id, rounded_timestamp
HAVING COUNT(round5(timestamp_)) < 3
ORDER BY rounded_timestamp;




