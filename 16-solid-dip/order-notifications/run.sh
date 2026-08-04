#!/usr/bin/env bash
# Compiles and runs DipDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/soliddip/ordernotifications/DipDemo.java
java -cp out soliddip.ordernotifications.DipDemo
