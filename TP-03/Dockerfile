FROM tomcat:9.0

RUN mkdir /usr/local/tomcat/webapps/book-store/ -p

WORKDIR /usr/local/tomcat/webapps/book-store/

COPY . .

RUN cp ./lib/* /usr/local/tomcat/lib/ -r

RUN sh ./build.sh

EXPOSE 8080

CMD ["catalina.sh", "run"]
