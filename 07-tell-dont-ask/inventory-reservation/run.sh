#!/usr/bin/env bash
# Compiles and runs TellDontAskDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/telldontask/inventoryreservation/example/*.java src/telldontask/inventoryreservation/exercise/*.java
java -cp out telldontask.inventoryreservation.example.TellDontAskDemo
