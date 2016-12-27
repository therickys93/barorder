FROM therickys93/ubuntu14java
ADD . /barorder
WORKDIR /barorder
RUN ./gradlew clean check stage
CMD /bin/bash run.sh