#! /bin/sh

# Run ddl.sql script to create tables
cd /home/centos/dev/jarvis_data_eng_Josh/linux_sql/scripts
./ddl.sql start

# Create variables to store commands for hardware specifications
lscpu_out=$(lscpu)
mem_info=$(cat /proc/meminfo)

# Create variables to store individual hardware specifications
export hostname2=$(hostname -f)
export cpu_number2=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
export cpu_architecture2=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
export cpu_model2=$(echo "$lscpu_out" | egrep "^Model name:" | awk '{print $3,$4,$5,$6,$7}' | xargs)
export cpu_mhz2=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
export l2_cache2=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk '{$0=substr($0,1,length($0)-1); print $3}' | xargs)
export total_mem2=$(echo "$mem_info" | egrep "^MemTotal:" | awk '{print $2}' | xargs)
export timestamp2=$(date '+%F %H:%M:%S')

# Insert data into the host_info table
psql -h localhost -U postgres -d host_agent -c "INSERT INTO "host_info" ("id", "hostname", "cpu_number", "cpu_architecture", "cpu_model", "cpu_mhz", "l2_cache", "total_mem", "timestamp_") VALUES (DEFAULT, '"$hostname2"', "$cpu_number2", '"$cpu_architecture2"', '\"$cpu_model2\"', "$cpu_mhz2", "$l2_cache2", "$total_mem2", '\"$timestamp2\"');"

