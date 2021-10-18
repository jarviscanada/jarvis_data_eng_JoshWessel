#! /bin/sh

# Run ddl.sql script to create tables
cd /home/centos/dev/jarvis_data_eng_Josh/linux_sql/scripts
./ddl.sql start

# Create variables to store commands for usage data
mem_info=$(cat /proc/meminfo)
disk_info=$(vmstat)
disk_info2=$(vmstat -d)
disk_info3=$(df -BM /)

# Create a hostname variable to compare with host_info in order to find the matching id
export hostname2=$(hostname -f)

# Create variables to store individual usage data statistics
export memory_free=$(echo "$mem_info" | egrep "^MemFree:" | awk '{print $2 / 1000}' | xargs)
export cpu_idle=$(echo "$disk_info" | egrep "[0|1]" | awk '{print $15}' | xargs)
export cpu_kernel=$(echo "$disk_info" | egrep "[0|1]" | awk '{print $14}' | xargs)
export disk_io=$(echo "$disk_info2" | egrep "[0|1]" | awk '{print $10}' | xargs)
export disk_available=$(echo "$disk_info3" | egrep "^/dev/sda" | awk '{print $4}' | xargs | rev | cut -c2- | rev)
export timestamp2=$(date '+%F %H:%M:%S')

# Insert data into the host_usage table
psql -h localhost -U postgres -d host_agent -c "INSERT INTO "host_usage" ("host_id", "memory_free", "cpu_idle", "cpu_kernel", "disk_io", "disk_available", "timestamp_") VALUES ((SELECT id FROM host_info WHERE hostname = '"$hostname2"'), "$memory_free", "$cpu_idle", "$cpu_kernel", "$disk_io", "$disk_available", '\"$timestamp2\"');"


