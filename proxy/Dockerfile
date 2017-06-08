FROM therickys93/alpinenginx
RUN rm /etc/nginx/conf.d/*
COPY proxy.conf /etc/nginx/conf.d/
COPY 50x.html /usr/share/nginx/html