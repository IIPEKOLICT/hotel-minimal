#!/bin/bash

cd backend || exit

echo Build fatJAR file...

./gradlew build -x test

echo Move output backend file to build directory...

mv "build/libs/backend.jar" "../build/$1.jar"
