FROM therickys93/alpinejava
ADD . /barorder
WORKDIR /barorder
RUN ./gradlew clean check stage
CMD /bin/bash run.sh