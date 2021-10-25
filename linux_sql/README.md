# Linux Cluster Monitoring Agent

# Introduction

The project objective is to develop a system that collects hardware information and resource usage data from Linux hosts connected to a local network.
A PostgreSQL database contains the data.
This process takes place within a container created and managed by Docker.
A PSQL instance is created within the container and stores the collected data in two different tables.
The system uses five different scripts to complete the project.
The scripts are used to start Docker, set up PSQL tables, collect hardware information and usage data, and query data.
Crontab automates the collection of usage data from the host device by running the appropriate script every minute while the system is active.
Then, the queried data addresses various questions related to the hardware information and usage data from all connected hosts.

# Quick Start

To quickly execute each stage of the project, use the following sequence of commands.

```Bash
# 1. Start Docker
./psql_docker.sh start

# 2. Setup tables
./ddl.sql

# 3. Run host_info script
./host_info.sh [host] [port] [database_name] [user] [password]

# 4. Setup crontab to automate host_usage script
crontab -e
* * * * * bash /./host_usage.sh [host] [port] [database_name] [user] [password] &> /tmp/host_usage.log

# 5. Run queries script to retrieve usage data
./queries.sql
```

# Implementation

**1. Setup**

The first step in the implementation process was to set up GitHub and Docker.
This step included setting up a GitHub repo with a series of branches. These branches include master, release, develop, and feature branches. This model is the branching model used throughout the development of this project.
This step also involved installing Docker, creating a container, and setting up a PSQL database within the container.
A script simplifies the process of starting the container.

**2. Create PSQL tables**

This step involved the creation of a pair of PSQL tables using a SQL script.
Below is a list of all fields in each table.

The `host_info` table contains various hardware specifications, including the following:
* `id` The number of the record in the table. This field also serves as the primary key
* `hostname` The name of the host device
* `cpu_number` The number of CPU's
* `cpu_architecture` The framework of the host device
* `cpu_model` The model name of the host device
* `cpu_mhz` CPU main frequency
* `l2_cache` Memory in second level cache in kb
* `total_mem` The total memory of the host device
* `timestamp` The current date and time

The `host_usage` table contains various usage statistics, including the following:
* `host_id` A reference to the corresponding `id` value from the `host_info` table
* `memory_free` Free memory in mb
* `cpu_idle` Percent of time CPU is idle
* `cpu_kernel` Percent of time CPU is running kernel code
* `disk_io` Number of disk I/O
* `disk_available` Available disk in mb
* `timestamp_` The current date and time

**3. Collect Hardware Information and Usage Data**

This step involved the collection of various data points from the host device.
This process included the use of various command-line functions in Bash.
These command-line functions display various data points related to hardware specifications, memory usage, CPU and disk usage, etc.
The use of trimming techniques reduced the outputs to only a single value.
Using scripts, these pieces of data were then saved to variables and inserted into the PSQL database.

**4. Automate the Collection of Usage Data**

The next step in the process was to automate the collection of usage data from the host using Crontab.
Crontab automatically runs the script that collects usage data and inserts it into the PSQL database.
Crontab runs this script once every minute that the system is active.

**5. Query To Address Questions**

The final step in the process was to use the collected usage data to address various questions. The questions to be addressed are listed below:
1. Group hosts by hardware info
2. Average memory usage (as a percentage) displayed over 5-minute intervals
3. Detect host failure

Queried data from the usage data table in the PSQL database address various questions by retrieving data related to each question.
The 'queries.sql' script contains the SQL code used to query the data to address each question.

## Architecture

The diagram below depicts the architecture of the project.
Please note that although the diagram depicts a scenario where a switch connects multiple hosts, the implementation of this project involved only one host device.

The host device uses bash and SQL scripts to access, insert, and query data from the database.
All hosts running the appropriate scripts will collect their hardware and usage data and send it to the database on Linux host 1.
Although all connected Linux hosts insert data into the database, only Linux host 1 can query data from the database.

![Image of Cluster Diagram](./assets/Cluster_Diagram.png)

## Scripts

The project uses five different scripts. Please find descriptions of each below.

**`psql_docker.sh`**

The `psql_docker.sh` script can start, stop, or create a Docker container.
```Bash
# Start docker if not already started
# Check command, accept only start, stop, and create commands
# Start, stop, or create depending on command
```

**`ddl.sql`**

The `ddl.sql` script creates two tables within the PSQL database.
`host_info` stores the host's hardware information.
`host_usage` stores the host's usage data (memory, CPU, disk).
```Bash
# Create host_info table if not already created
# Create host_usage table if not already created
```

**`host_info.sh`**

The `host_info.sh` script takes in five arguments (host, port, database name, user, password)
and uses them to insert the host device's hardware information into the `host_info` table.
```Bash
# Save arguments to variables
# Check to make sure the expected number of arguments is entered
# Create variables to store commands for hardware specifications
# Create variables to store individual hardware specifications
# Save password as an environment variable
# Insert data into the host_info table
```

**`host_usage.sh`**

The `host_usage.sh` script takes in five arguments (host, port, database name, user, password)
and uses them to insert the host device's usage data into the `host_usage` table.
Using Crontab, this script is automated to run every minute.
```Bash
# Save arguments to variables
# Check to make sure the expected number of arguments is entered
# Create variables to store commands for usage data
# Create a hostname variable to compare with host_info to find the matching id
# Create variables to store individual usage data statistics
# Save password as an environment variable
# Insert data into the host_usage table
```

**`queries.sql`**

The `queries.sql` script addresses various questions regarding the data collected in the PSQL database.
```Bash
# Group hosts by hardware info
# Find average memory usage
# Detect host failure
```

## Database Modelling

The project uses two tables within the PSQL database.
The first table stores the hardware specifications of the host device.
The second table stores various usage statistics of the host device.
Below is the schema for each table.

`host_info` schema

Field Name | Data Type |
---------- | --------- |
id | SERIAL INT |
hostname | VARCHAR |
cpu_number | INT |
cpu_architecture | VARCHAR |
cpu_model | VARCHAR |
cpu_mhz | INT |
l2_cache | INT |
total_mem | INT |
timestamp_ | TIMESTAMP |

`host_usage` schema

Field Name | Data Type |
---------- | --------- |
host_id | SERIAL INT |
memory_free | NUMERIC |
cpu_idle | INT |
cpu_kernel | INT |
disk_io | INT |
disk_available | INT |
timestamp_ | TIMESTAMP |

# Test

All scripts and queries underwent trial and error testing.

Following the completion of the `psql_docker.sh` script, the script was executed successfully.
The script successfully starts and stops containers.
However, the "create container" functionality of the script has not yet been tested due to the lack of need to create any additional containers.

Some code from the `host_info.sh` and `host_usage.sh` scripts was tested prior to the creation of the scripts.
These tests were performed on the command line in the Linux terminal to verify trimmed variable strings contained only the desired information.
Additional code was later added to the scripts to add required arguments (host, port, database name, user, password).
The completed scripts were then tested using trial and error. The scripts are designed to insert data into tables in a PSQL database,
so to confirm the scripts were working as intended, the tables were queried using the Linux command line to confirm the new records were inserted as expected.

Early versions of both the `host_info.sh` and `host_usage.sh` scripts ran the `ddl.sql` script.
Given that the scripts produced the expected result, it was assumed that the `ddl.sql` script was functioning as expected.
The use of the script was later removed from both shell scripts when it was discovered that it was not necessary to include.
By this time, both tables created in the `ddl.sql` script had been created and were working as expected, leaving no further need to use the script or test it extensively.

SQL queries in the `queries.sql` script were tested by copy and pasting certain code blocks into the Linux command line (after accessing the PSQL database).
Trial and error tests were performed until the expected result was achieved.

# Deployment

GitHub, Docker, and Crontab were the primary applications used in the deployment of the application.
GitHub served as the version control software.
Most GitHub actions took place directly in the Linux CLI terminal (pushing, pulling, adding/deleting/modifying branches).
Some GitHub actions took place through the remote repository on github.com, namely pull requests and some branch management.
Docker creates and manages a container that runs the application.
Crontab was also used to automate the `host_usage` script, which collects usage data from the host device every minute.

# Improvements

Below are a few improvements to consider.

1. Consider additional questions and add queries to the `queries.sql` script accordingly.
2. Perform more extensive testing on all scripts.
3. Output query data in a format that is easier to read/use (writing to a text file rather than the command line).