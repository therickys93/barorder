FROM therickys93/alpinenginx
RUN rm /etc/nginx/conf.d/* && ln -sf /dev/stdout /var/log/nginx/access.log \
	&& ln -sf /dev/stderr /var/log/nginx/error.log
COPY proxy.conf /etc/nginx/conf.d/
COPY 50x.html /usr/share/nginx/html