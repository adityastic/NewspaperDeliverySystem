#!/bin/sh

git config --global user.name "Aditya Gupta"
git config --global user.email "adityaofficialgupta@gmail.com"

git clone --quiet --branch=jar https://adityastic:$GITHUB_TOKEN@github.com/adityastic/NewspaperDeliverySystem jar > /dev/null
cd jar

rm -rf $BRANCH_NAME*

find ../target -type f -name '*.jar' -exec cp -v {} . \;

for file in Newspaper*; do
    mv $file $BRANCH_NAME-$file
done

git checkout --orphan temporary

git add --all .
git commit -am "[Auto] Update JAR ($(date +%Y-%m-%d.%H:%M:%S))"

git branch -D jar
git branch -m jar

git push origin jar --force --quiet > /dev/null