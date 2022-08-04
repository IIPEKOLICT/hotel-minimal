#!/bin/bash

cd backend

echo Build fatJAR file...

./gradlew build

echo Move output backend file to build directory...

mv "build/libs/backend.jar" "../build/$1.jar"
