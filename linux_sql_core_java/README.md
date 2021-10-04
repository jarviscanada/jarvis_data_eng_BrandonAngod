#Introduction

This project is designed to create a SQL table of the host's usage and info. It will take a snapshot every minute and save it to the table using crontab. To create this project I used Docker, PostgreSQL, Bash, Git, Intelij, and crontab. The idea of the project is so that the user can monitor their usage of their computer and track memory and cpu usage and be able to manipulate that data to see how much they use on average. With this the user can see if they need to upgrade their current setup or possibly just make sure everything is running smoothly. The program requires a little bit of setup to get running but runs smoothly with little to no errors.


#Quick Start
```bash
#Create The psql session
./scripts/psql_docker.sh create <username> <password>
./scripts/psql_docker.sh start

#initilize the tables
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql

#Record Preliminary data
./scripts/host_info.sh
./scripts/host_usage.sh
#Start the cronjob
crontab -e
* * * * * bash /home/centos/dev/jarvis_data_eng_BrandonAngod/linux_sql_core_java/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log


```

#Implementation
In order to implement this project first we need to create a psql container using docker. After the docker creates the psql instance
we must start the instance up. Now that we have our instance ready next we must add our tables to the database.
To do so the DDL.sql file has the commands required to make the appropriate sql table with all the categories required.
Now that the database and tables are all ready we can begin feeding it data periodically.
Using host_info.sh and host_usage.sh we can record the data and send it to the psql database.
In order to keep sending the data periodically we utilize crontab to automate the system to run the program every minute.
Now we can just sit back and relax as every minute a new entry is made.

#Architecture
As you can see in diagram bellow each linux host will send to the database. After it is sent to the database it will be sent to either host_info or host_usage depending on which table is being called.

![](assets/linuxsql.drawio(1).png)
#Scripts
- psql_docker.sh 
  - Create | Start | End the psql session
- host_info.sh 
  - Record host PC's information to the host_info SQL Table
- host_usage.sh
  - Record host PC's usage data to the host_usage SQL Table
- crontab
  - Runs host_usage.sh every minute
- ddl.sql
  - Initialize the SQL tables for the data within host_info.sh and host_usage.sh
- queries.sql
  - Calculates Average Usage
  - Checks for failures
  - Sorts sql table by hardware info

#Database Modeling
The database consists of two tables, host_info and host_usage. The first one is made to hold any information on the hosts system setup. The second will hold all info regarding the hosts usage of the computer and will have many results.

**host_info:**
- ```id``` Primary key for the data.
- ```hostname``` Name of the host computer.
- ```cpu_number``` number of cores in CPU.
- ```cpu_model``` model name of CPU.
- ```cpu_mhz``` clock speed of CPU in MHz.
- ```l2_cache``` amount of l2 cache the system has.
- ```total_mem``` total amount of memory available to the computer.
- ```timestamp``` timestamp of when data was recorded.

**host_usage:**
- ```timestamp``` timestamp of when data was recorded.
- ```host_id ``` id tag to differentiate each computer from each other.
- ```memory_free ``` memory available to the system.
- ```cpu_idle ``` how much of the cpu is currently idle.
- ```cpu_kernal ``` what kernal the cpu is operating with.
- ```disk_io ``` disk write and read speed.
- ```disk_available``` how much disk space is available.


#Test
To test my bash scripts and sql queries I simply gathered data within my tables and used that as testing data. For the averages I also used traditional methods of manually adding and dividing the values in order to make sure that the result produced is accurate.

#Deployment
To deploy the app I used GitHub to store the files, crontab to automate the execution of the script and docker to contain the instance of psql. This way the program will not be too demanding and operate with ease.
#Improvements
-Improved user experience.

-Add additional queries to help user find key information.

-Improve README with a Frequently Asked Questions section.
