#!/usr/bin/env bash
# Compiles and runs IspDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/solidisp/orderprocessorinterfaces/IspDemo.java
java -cp out solidisp.orderprocessorinterfaces.IspDemo
