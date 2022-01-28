#!/bin/bash

#./gradlew bootRun

./gradlew build

cd build/libs

/usr/bin/java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8888 -jar music-pages-scraper-0.0.1-SNAPSHOT.jar
