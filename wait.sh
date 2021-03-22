#! /bin/bash

echo "I will wait for 30 sec"
sleep 20

java -Djava.security.egd=file:/dev/./urandom -jar /bank.jar