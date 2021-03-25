#!/bin/bash

cd /var/www/music-scraper/MusicPagesScraper

git pull

./gradlew build

cd build/libs

su

systemctl restart music-scraper

exit
