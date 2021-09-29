#save hostname to a variable
hostname=$(hostname -f)

#save number of CPU to a variable
export PGPASSWORD='password'

lscpu_out=`lscpu`
#note: `xargs` is a trick to remove leading and trailing white spaces
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

#hardware
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep '^CPU[[:space:]]MHz:' | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep 'L2[[:space:]]cache:' | awk '{print $3}' | xargs)

meminfo_out=`cat /proc/meminfo`
total_mem=$(echo "$meminfo_out"  | egrep '^MemTotal\:' | awk '{print $2}' | xargs)
timestamp=$(date '+%Y-%m-%d %H:%M:%S') #current timestamp in `2019-11-26 14:40:19` format

psql -h localhost -U postgres -w << EOF
\dt
\c host_agent
\dt
INSERT INTO host_info VALUES ('1','$hostname','$cpu_number','$cpu_architecture','$cpu_model','$cpu_mhz','$l2_cache','$total_mem','$timestamp');
SELECT *FROM host_info
EOF