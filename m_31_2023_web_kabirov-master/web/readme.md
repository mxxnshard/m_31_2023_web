liquibase
https://www.baeldung.com/liquibase-refactor-schema-of-java-app

deploy:
* Register in timeweb.ru (or other cloud platform)
* Generate SSH keys: in terminal ssh-keygen
* Copy key from generated SSH-key (.pub) and paste to created VPS
* Connect to our server: ssh -i <path to your .pub key> root@1.1.1.1
* Install needed apps to server: git, java, docker, docker-compose, mvn
* * sudo apt install git
* * sudo apt install openjdk-17-jdk-headless
* * sudo apt install maven
* * sudo apt install docker
* * sudo apt install docker-compose
* clone project from git repo
* run build_image.sh: sh build_image.sh
* go to deploy folder, run cmd: sudo docker-compose up -d
*
* sudo docker ps - running containers
* sudo docker images
* sudo docker image prune -a - delete unused images
* sudo docker-compose up -d
* sudo docker-compose down
* sudo docker logs <deploy_java-app_1> --tail=500 -f

