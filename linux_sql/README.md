# Introduction

The project is to develop a system that collects hardware information and resource usage data from all Linux hosts connected on a local network and then storing the data in a postgres SQL database for analysis. This process takes place within a container created and managed by Docker. Within this container, a PSQL instance is created and used to store the collected data. Several scripts are used throughout the process. One starts docker, while two others collect hardware information and usage data from all connected Linux hosts. The data is then stored in a psql database. The data is then used in a sql script to ...

# Quick Start

The following sequence of commands can be used to quickly access and execute each stage of the project.

```Bash
./psql_docker.sh start
./host_info.sh
```

# Implementation

## Architecture

![Image of Cluster Diagram](./assets/Cluster_Diagram.png)

## Scripts

The project uses x scripts. Please find pseudocode with descriptions of each script below.

psql_docker.sh
```Bash
# Start docker if not already started
# Check command, accept only start, stop and create commands
# Start, stop, or create depending on command
```

ddl.sql
```Bash
# Create host_info table if not already created
# Create host_usage table if not already created
```

host_info.sh
```Bash
# Save arguments to variables
# Check to make sure the expected number of arguments is entered
# Create variables to store commands for hardware specifications
# Create variables to store individual hardware specifications
# Save password as an environment variable
# Insert data into the host_info table
```

host_usage.sh
```Bash
# Save arguments to variables
# Check to make sure the expected number of arguments is entered
# Create variables to store commands for usage data
# Create a hostname variable to compare with host_info in order to find the matching id
# Create variables to store individual usage data statistics
# Save password as an environment variable
# Insert data into the host_usage table
```

## Database Modelling

The project uses two tables within the psql database. The first table stores the hardware specifications of the host device. The second table stores various usage statistics of the host device. The schema for each table can be found below.

host_info schema

 id | hostname | cpu_number | cpu_architecture | cpu_model | cpu_mhz | l2_cache | total_mem | timestamp |
--- | -------- | ---------- | ---------------- | --------- | ------- | -------- | --------- | --------- |
 1 | jrvs-remote-desktop | 2 | x86_64 | Intel(R) | 2200 | 256 | 8000000 | 2021-10-18 13:57:20 |

host_usage schema

 host_id | memory_free | cpu_idle | cpu_kernel | disk_io | disk_available | timestamp |
-------- | ----------- | -------- | ---------- | ------- | -------------- | --------- |
 1 | 3000 | 95 | 0 | 0 | 24000 | 2021-10-18 13:57:20 |

# Test

# Deployment

The application was deployed using GitHub, Docker, and Crontab. GitHub was used for source control. Most GitHub actions were conducted using bash directly in the Linux cli terminal (pushing, pulling, adding/deleting/modifying branches). Some GitHub actions took place through the remote repository on GitHub.com, namely pull requests and some branch management. Docker was used to create and manage a container in which the application is built. Crontab was also used to automate the host_usage script, which collects usage data from the host device every minute.

# Improvements








