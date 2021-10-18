#! /bin/sh

# Save arguments to variables
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Check to make sure the expected number of arguments is entered
if [ $# -ne 5 ]; then
  echo "Incorrect number of arguments. Please enter (1) host, (2) port, (3) database name, (4) user, (5) password"
  exit 1
fi

# Run ddl.sql script to create tables
#cd /home/centos/dev/jarvis_data_eng_Josh/linux_sql/scripts
#./ddl.sql start

# Create variables to store commands for usage data
mem_info=$(cat /proc/meminfo)
disk_info=$(vmstat)
disk_info2=$(vmstat -d)
disk_info3=$(df -BM /)

# Create a hostname variable to compare with host_info in order to find the matching id
hostname2=$(hostname -f)

# Create variables to store individual usage data statistics
memory_free=$(echo "$mem_info" | egrep "^MemFree:" | awk '{print $2 / 1000}' | xargs)
cpu_idle=$(echo "$disk_info" | egrep "[0|1]" | awk '{print $15}' | xargs)
cpu_kernel=$(echo "$disk_info" | egrep "[0|1]" | awk '{print $14}' | xargs)
disk_io=$(echo "$disk_info2" | egrep "[0|1]" | awk '{print $10}' | xargs)
disk_available=$(echo "$disk_info3" | egrep "^/dev/sda" | awk '{print $4}' | xargs | rev | cut -c2- | rev)
timestamp2=$(date '+%F %H:%M:%S')

# Save password
export PGPASSWORD=$psql_password

# Insert data into the host_usage table
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "INSERT INTO "host_usage" ("host_id", "memory_free", "cpu_idle", "cpu_kernel", "disk_io", "disk_available", "timestamp_") VALUES ((SELECT id FROM host_info WHERE hostname = '"$hostname2"'), "$memory_free", "$cpu_idle", "$cpu_kernel", "$disk_io", "$disk_available", '\"$timestamp2\"');"

exit $?
