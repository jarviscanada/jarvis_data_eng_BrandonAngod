
#save hostname to a variable
hostname=$(hostname -f)

meminfo_out=`cat /proc/meminfo`
#usage
memory_free=memory_free=$(echo "$meminfo_out"  | egrep '^MemFree:' | awk '{print $2}' | xargs)
#NR= row number
#NF= Field number
cpu_idle=$(vmstat | awk 'NR==3 {print $(NF-2)}')
cpu_kernel=$(vmstat | awk 'NR==3 {print $(NF-1)}')
disk_io=$(vmstat -d | awk 'NR==3 {print $(NF-1)}')
disk_available=$(df -BM / | awk 'NR==2 {print $(NF-2)}')