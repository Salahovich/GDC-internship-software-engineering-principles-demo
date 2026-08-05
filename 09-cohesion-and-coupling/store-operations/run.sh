#!/usr/bin/env bash
# Compiles and runs CohesionAndCouplingDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/cohesionandcoupling/storeoperations/example/*.java src/cohesionandcoupling/storeoperations/exercise/*.java
java -cp out cohesionandcoupling.storeoperations.example.CohesionAndCouplingDemo
