#!/bin/bash

rm ./WEB-INF/classes/ -rf

CLASSPATH="./src"
CLASSPATH=$CLASSPATH:./lib/gson-2.2.2.jar
CLASSPATH=$CLASSPATH:./lib/servlet-api.jar
CLASSPATH=$CLASSPATH:./lib/org.apache.commons.io.jar
CLASSPATH=$CLASSPATH:./lib/mysql-connector-java-8.0.23.jar

for path in ./**/*.java ./src/DAO/*.java ./src/Entities/*.java;
do javac $path -d ./WEB-INF/classes/ -classpath $CLASSPATH;
done
