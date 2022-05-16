Table of Contents
* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop-Cluster)
* [Hive Project](#Hive-Project)
* [Improvements](#Improvements)

# Introduction

The Hadoop project is a big data analytics project that uses a Hadoop cluster and Zeppelin notebook to address business problems relating to a dataset.
The Hadoop cluster is provisioned through Google Cloud Platform and includes a master node and a pair of worker nodes.
The dataset is analyzed using Apache Hive and a Zeppelin Notebook.
The notebook contains cells addressing nine business problems.
Both Google Cloud Storage and HDFS stored the dataset at various points throughout the project.
The testing process involved trial and error tests for each cell.
The project's deployment used GCP to provision the master and worker nodes.

# Hadoop Cluster
## Cluster Architecture
![Image of Hadoop Cluster Architecture](./assets/Hadoop%20Cluster%20Architecture.png)

The Hadoop cluster architecture contains a master node and two worker nodes.
The nodes form a master/worker architecture, where the master node manages the worker nodes.
The master node contains a Hive instance, Zeppelin notebooks, an HDFS name node, and a YARN resource manager.
The worker nodes contain an HDFS data node (managed by the master node's HDFS name node) and a YARN node manager (managed by the master node's YARN resource manager).
Google Cloud Platform provisions the cluster.

## Big Data Tools

The project employed various big data tools. Below is a brief description of each.

#### YARN

YARN stands for Yet Another Resource Negotiator.
The Hive service sends jobs to the YARN resource manager.
YARN then distributes the jobs across the worker nodes.

#### HDFS

HDFS stands for Hadoop Distributed File System.
It is a file system designed to store big data.
To accomplish this, HDFS uses multiple devices to store the data.
In this project, the HDFS distributes the dataset across two worker nodes.

#### Hive

Hive is an open-source data warehouse software used to process large datasets in HDFS.
It uses a SQL-like query language called HQL.
The HQL statements provide an alternative to MapReduce programming for users with limited Java experience.
Hive also uses a Hive service and a metastore.
In this project, Zeppelin connects with the Hive instance, which uses its services and metastore to generate a distributed application.
Then it is sent to the YARN resource manager.

#### Zeppelin

Zeppelin is a notebook software used for writing queries.
It runs in the master node and connects to Hive through a JDBC connection.
Hive then communicates with YARN, which distributes the task of executing the queries to the worker nodes.

## Hardware Specifications

Image: 2.0 (Debian 10, Hadoop 3.2, Spark 3.1)

#### Master Node

- Machine Type: n1-highmem-2 (2 vCPU, 13 GB memory)
- Disk Size: 100 GB

#### Worker Node
- Machine Type: n1-highmem-2 (2 vCPU, 13 GB memory)
- Disk Size: 100 GB

# Hive Project

The Hive project uses a Zeppelin Notebook to analyze the dataset.
The notebook addresses nine business problems.
Some queries took a significant amount of time to return the result, opening the possibility of optimization.
One particular query was optimized using two different approaches.
The first approach involved running queries on a partitioned table to search only a subset of the data.
The second approach involved the use of a columnar file.
The data was copied into a parquet table so that the query only needed to search the specified columns.

Below is an image of the notebook:

![Image of Zeppelin Notebook](./assets/ZeppelinNotebookCapture.png)

# Improvements
Below are a few improvements to consider:
1. Add more markdown cells with more detailed descriptions of the code
2. Add additional business problems to be addressed in the Zeppelin Notebook
3. Add a more detailed introduction and conclusion markdown cells