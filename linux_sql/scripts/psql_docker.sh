#! /bin/sh

#capture CLI arguments (please do not copy comments)
cmd=$1
db_username=$2
db_password=$3

#Start docker
sudo systemctl status docker || systemctl start docker

#check container status (try the following cmds on terminal)
dstatus=`docker container inspect jrvs-psql`
container_status=$(echo $dstatus | awk '{print $16}' | xargs )
#User switch case to handle create|stop|start opetions
case $cmd in
  create)
    # Check if the container is already created
    if [ ! -z "$container_status" ]; then
		  echo 'Container already exists'
		  exit 1
	  fi

    #check # of CLI arguments
    if [ $# -ne 3 ]; then
      echo 'Create requires username and password'
      exit 1
    fi

    docker login -u db_username -p db_password
    #Create container
	  docker volume create pgdata
    docker run --name jrvs-psql -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	  exit 0
	  ;;
######
  start|stop)
    #check instance status; exit 1 if container has not been created
    if [ -z "$container_status" ]; then
      echo 'Container Not Created'
      exit 1
      fi
    #Start or stop the container
      if [ $cmd = "stop" ]; then
          echo "Stopping Docker"
          docker container stop jrvs-psql
          fi
	    if [ $cmd = "start" ]; then
	        echo "Starting Docker"
          docker container start jrvs-psql
          fi

	  exit 0
	  ;;

  *)
	  echo 'Illegal command'
	  echo 'Commands: start|stop|create'
	  exit 1
	  ;;
esac