#! /bin/sh

# Setup variables (will be used as arguments)
cmd=$1
db_username=$2
db_password=$3

# Start Docker if it is not already started
sudo systemctl status docker || systemctl start docker

# Check status of container
docker container inspect jrvs-psql
container_status=$?

case $cmd in
  create)

  # Check if container exists
  if [ $container_status -eq 0 ]; then
    echo 'Container already exists'
    exit 0
  fi

  # Check to make sure there are 3 arguments
  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

  # Create volume and container
  docker volume create pgdata
  export PGPASSWORD='password'
  docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6.23-alpine
  exit $?
  ;;

  start|stop)

  # Check if container has been created
  if [ $container_status -ne 0 ]; then
    echo 'Container has not been created'
    exit 1
  fi

  # start/stop container
  docker container "$cmd" jrvs-psql
  exit $?
  ;;

  *)

  # Print error message if command is invalid
  echo 'Illegal command'
  echo 'Commands: start|stop|create'
  exit 1
  ;;
esac



