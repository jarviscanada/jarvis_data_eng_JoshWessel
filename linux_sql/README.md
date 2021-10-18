# Introduction

The project is to develop a system that collects hardware information and resource usage data from all Linux hosts connected on a local network and then storing the data in a postgres SQL database for analysis. This process takes place within a container created and managed by Docker. Within this container, a PSQL instance is created and used to store the collected data. Several scripts are used throughout the process. One starts docker, while two others collect hardware information and usage data from all connected Linux hosts. The data is then stored in a psql database. The data is then used in a sql script to ...

# Quick Start

The following sequence of commands can be used to quickly access and execute each stage of the project.

```Bash
./psql_docker.sh start
./host_info.sh
./host_usage.sh
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
# Run ddl.sql script to create tables
# Create variables to store commands for hardware specifications
# Create variables to store individual hardware specifications
# Insert data into the host_info table
```

host_usage.sh
```Bash
# Run ddl.sql script to create tables
# Create variables to store commands for usage data
# Create a hostname variable to compare with host_info in order to find the matching id
# Create variables to store individual usage data statistics
# Insert data into the host_usage table
```

## Database Modelling

# Test

# Deployment

# Improvements
