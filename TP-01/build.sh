#!/bin/bash

rm ./WEB-INF/classes/ -rf

javac ./**/*.java -d ./WEB-INF/classes/ -classpath ./src:./lib/servlet-api.jar:./lib/mysql-connector-java-8.0.23.jar:./lib/gson-2.2.2.jar
javac ./src/DAO/*.java -d ./WEB-INF/classes/ -classpath ./src:./lib/servlet-api.jar:./lib/mysql-connector-java-8.0.23.jar:./lib/gson-2.2.2.jar
javac ./src/Entities/*.java -d ./WEB-INF/classes/ -classpath ./src:./lib/servlet-api.jar:./lib/mysql-connector-java-8.0.23.jar:./lib/gson-2.2.2.jar
