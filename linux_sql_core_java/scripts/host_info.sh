#Check # of args
if [ $# -ne  5 ]
then
  echo "Missing Arguments"
  exit 1
fi
#Save Arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Save hostname
hostname=$(hostname -f)

#Password
export PGPASSWORD='password'

#Save for later to reduce number of times this comamnd is called
lscpu_out=`lscpu`
#CPU Cores         lscpu                 CPU(s)            retrieve 2nd arg
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
#Architecture         lscpu                 Architecture:            retrieve 2nd arg
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
#CPU Model         lscpu                 Model            retrieve 2nd arg
cpu_model=$(echo "$lscpu_out"  | egrep "^Model:" | awk '{print $2}' | xargs)
#MHZ         lscpu                 CPU MHZ                 retrieve 3rd arg
cpu_mhz=$(echo "$lscpu_out"  | egrep '^CPU[[:space:]]MHz:' | awk '{print $3}' | xargs)
#L2 Cache         lscpu                 L2 Cache:            retrieve 3nd arg
l2_cache=$(echo "$lscpu_out"  | egrep 'L2[[:space:]]cache:' | awk '{print $3}' | xargs)

meminfo_out=`cat /proc/meminfo`
total_mem=$(echo "$meminfo_out"  | egrep '^MemTotal\:' | awk '{print $2}' | xargs)
timestamp=$(date '+%Y-%m-%d %H:%M:%S') #current timestamp in `2019-11-26 14:40:19` format


#output to psql
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -w << EOF
\dt
\c host_agent
\dt
INSERT INTO host_info VALUES ('1','$hostname','$cpu_number','$cpu_architecture','$cpu_model','$cpu_mhz','$l2_cache','$total_mem','$timestamp');
SELECT *FROM host_info
EOF
exit 0