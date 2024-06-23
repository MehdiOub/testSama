## Build the project
.\mvnw clean package -DskipTests

## Create The Docker Image
docker build -t :v1 .

## Display The created Image
docker images

## Run the Container
docker run -d -p 8036:8036 --name -v1 :v1

## Display The Running Container
docker ps





## MinIo
1- go to http://localhost:9001/login
login: minio  and pass: minio (see .env file of docker compose)

2- go to identity >> users menu
create a user:
login : zyn pass: zyn@2024 role: read and write

3- edit the user and click on service account >> create
copy access key and secret key in application.prop

4- connect as zyn using login and pass and create your bucket

5- test using
http://localhost:8036/api/cloud/upload/bucket/your-default-bucket
form-data >> file + upload your file


## Sonar
1- go to http://localhost:9000
login: admin  and pass: admin (by default and change login and pass)

2- go to http://localhost:9000/projects/create and click on create a local project

3- select: Use the global setting and click create

4- select: create localy

5- generate token

6- choose maven and copy the command


