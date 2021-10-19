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

# Create variables to store commands for hardware specifications
lscpu_out=$(lscpu)
mem_info=$(cat /proc/meminfo)

# Create variables to store individual hardware specifications
hostname2=$(hostname -f)
cpu_number2=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture2=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model2=$(echo "$lscpu_out" | egrep "^Model name:" | awk '{print $3,$4,$5,$6,$7}' | xargs)
cpu_mhz2=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache2=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk '{$0=substr($0,1,length($0)-1); print $3}' | xargs)
total_mem2=$(echo "$mem_info" | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp2=$(date '+%F %H:%M:%S')

# Save password as an environment variable
export PGPASSWORD=$psql_password

# Insert data into the host_info table
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "INSERT INTO "host_info" ("id", "hostname", "cpu_number", "cpu_architecture", "cpu_model", "cpu_mhz", "l2_cache", "total_mem", "timestamp_") VALUES (DEFAULT, '"$hostname2"', "$cpu_number2", '"$cpu_architecture2"', '\"$cpu_model2\"', "$cpu_mhz2", "$l2_cache2", "$total_mem2", '\"$timestamp2\"');"

exit $?