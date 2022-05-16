FROM debian:stable

RUN apt-get update && \
    apt-get install -y apache2-utils && \
    mkdir /app

COPY entry.sh /app
COPY post.json /app
WORKDIR /app

CMD ["/bin/sh", "/app/entry.sh"]