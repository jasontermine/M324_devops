FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_USER=tester
ENV MYSQL_PASSWORD=testing
ENV MYSQL_DATABASE=todo

COPY ./init.sql /docker-entrypoint-initdb.d/