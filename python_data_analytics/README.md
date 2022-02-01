Table of Contents
* [Introduction](#Introduction)
* [Implementation](#Implementation)
* [Improvements](#Improvements)

# Introduction



# Implementation
## Project Architecture

![Image of Python Project Architecture](./assets/Python%20Project%20Architecture.png)

The diagram above illustrates the project's architecture.
The dataset to be analyzed is first taken from the Azure SQL server within the LGS web app.
Then it is exported as a SQL file and loaded into a PSQL data warehouse.
The container used to store the database uses a Docker network to connect to the container that contains the Jupyter Notebook, allowing data analysis from within a Jupyter Notebook.

## Data Analytics and Wrangling

The Jupyter Notebook used to perform the data analysis can be found at the following link:
[retail_data_analytics_wrangling.ipynb](https://github.com/jarviscanada/jarvis_data_eng_JoshWessel/blob/feature/python_data_analytics/python_data_analytics/retail_data_analytics_wrangling.ipynb)


# Improvements
Below are a few improvements to consider:
1. Explore the data by other columns, such as country and item purchased
2. Improve the visuals of the charts and graphs and create a dashboard using all charts
3. Create functions to make it easier to perform the same operations on an updated version of the dataset in the future