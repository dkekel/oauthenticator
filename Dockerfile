FROM gradle:jdk11

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
CMD ["gradle", "bootRun"]