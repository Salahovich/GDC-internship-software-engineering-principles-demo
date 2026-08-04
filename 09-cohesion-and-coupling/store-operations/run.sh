#!/usr/bin/env bash
# Compiles and runs CohesionAndCouplingDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/cohesionandcoupling/storeoperations/CohesionAndCouplingDemo.java
java -cp out cohesionandcoupling.storeoperations.CohesionAndCouplingDemo
