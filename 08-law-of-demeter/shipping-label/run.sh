#!/usr/bin/env bash
# Compiles and runs LawOfDemeterDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/lawofdemeter/shippinglabel/LawOfDemeterDemo.java
java -cp out lawofdemeter.shippinglabel.LawOfDemeterDemo
