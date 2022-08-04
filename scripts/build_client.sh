#!/bin/bash

cd client

echo Test client code...

./gradlew test

echo Build APK and AAB files...

./gradlew assembleRelease
./gradlew bundleRelease

echo Move output client files to build directory...

mv "app/build/outputs/apk/release/app-release-unsigned.apk" "../build/$1.apk"
mv "app/build/outputs/bundle/release/app-release.aab" "../build/$1.aab"
