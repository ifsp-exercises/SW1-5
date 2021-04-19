FROM tomcat:9.0

RUN mkdir /usr/local/tomcat/webapps/employee-manager/ -p

WORKDIR /usr/local/tomcat/webapps/employee-manager/

COPY . .

RUN cp ./lib/* /usr/local/tomcat/lib/ -r

RUN sh ./build.sh

EXPOSE 8080

CMD ["catalina.sh", "run"]
