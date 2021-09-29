export PGPASSWORD='password'
#save hostname to a variable
hostname=$(hostname -f)

meminfo_out=`cat /proc/meminfo`
#usage
timestamp=$(date '+%Y-%m-%d %H:%M:%S') #current timestamp in `2019-11-26 14:40:19` format
memory_free=$(echo "$meminfo_out"  | egrep '^MemFree:' | awk '{print $2}' | xargs)
#NR= row number
#NF= Field number
cpu_idle=$(vmstat | awk 'NR==3 {print $(NF-2)}')
cpu_kernel=$(vmstat | awk 'NR==3 {print $(NF-1)}')
disk_io=$(vmstat -d | awk 'NR==3 {print $(NF-1)}')
disk_available=$(df -BM / | awk 'NR==2 {print $(NF-2)}')

echo $memory_free
echo $cpu_idle
echo $cpu_kernel
echo disk_io
echo disk_available

psql -h localhost -U postgres -w << EOF
\dt
\c host_agent
\dt
INSERT INTO host_usage VALUES ('$timestamp','1','$memory_free','$cpu_idle','$cpu_kernel','$disk_io','$disk_available');
SELECT *FROM host_usage
EOF

#psql -h localhost -U postgres -d postgres -W -c "INSERT INTO PUBLIC.host_info ($meminfo,$memory_free,$cpu_idle,$cpu_kernel,$disk_io,$disk_available)"
