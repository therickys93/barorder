FROM therickys93/alpinejava
ADD . /barorder
WORKDIR /barorder
RUN ./gradlew clean stage
CMD /bin/bash run.sh
