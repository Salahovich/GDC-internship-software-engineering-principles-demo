#!/usr/bin/env bash
# Compiles and runs SeparationOfConcernsDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/separationofconcerns/orderprocessing/SeparationOfConcernsDemo.java
java -cp out separationofconcerns.orderprocessing.SeparationOfConcernsDemo
