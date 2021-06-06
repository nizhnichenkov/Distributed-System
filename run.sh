#!/bin/bash
echo "*************************************"
echo "RUNNING THIS MAGNIFICENT PIECE OF ART"
echo "*************************************"
echo "DON'T FORGET TO TURN THE DOCKER ON AND WEAR A MASK"
mvn clean package
docker-compose build 
docker-compose up 
