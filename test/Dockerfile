FROM maven:3-jdk7 as JDK7
RUN echo $(java --version)
RUN echo $(mvn --version)
ADD . /src
WORKDIR /src
RUN mvn clean install


FROM maven:3-jdk8 as JDK8
RUN echo $(java --version)
RUN echo $(mvn --version)
ADD . /src
WORKDIR /src
RUN mvn clean install -Pit
