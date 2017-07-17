FROM therickys93/ubuntu14java
ADD . /barorder
WORKDIR /barorder
RUN ./gradlew clean stage
CMD /bin/bash run.sh
