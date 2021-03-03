# FORM API

#### **1.  Image docker**
I build the image docker: docker build -t ernestoagc/form-api:0.2 .

also you can download these version on 
docker pull ernestoagc/form-api:0.2

![](https://i.imgur.com/lWnZiF7.jpg)

#### **2. DataBase Backup (script).**
In the resource folder  you will find the script to restore de database for these application


![](https://i.imgur.com/f5uerWH.jpg)

#### **3. Running backend application**
we going to use 2 docker container for up the application. The first container is for the mysql service and the second one will be for the backend application.

**running mysql container**
a) execute : 
*docker container run -d --network form-net  --name dbFormTest -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password123 mysql:8.0*

b) Enter into container console:  *docker exec -it dbFormTest  /bin/sh*

c) Put mysql credentials: 
  -mysql -u root -p;
   -password123
   
 d) create database: CREATE DATABASE dbUeat;
 
 **Running container of application**
 execute: 
*docker run -p 8500:8500  -d --link dbFormTest:mysql  --network form-net  -e spring.profiles.active=test  --name=form-api ernestoagc/form-api:0.2*



#### **4. Executing Unit Test**
I've Built for FormController (FormControllerTest.java) 5 unit test with Mockito.

![](https://i.imgur.com/InG2xZp.jpg)
