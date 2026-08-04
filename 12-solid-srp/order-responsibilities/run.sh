#!/usr/bin/env bash
# Compiles and runs SrpDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/solidsrp/orderresponsibilities/SrpDemo.java
java -cp out solidsrp.orderresponsibilities.SrpDemo
