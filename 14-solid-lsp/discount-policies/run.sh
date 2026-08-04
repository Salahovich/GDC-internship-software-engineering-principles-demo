#!/usr/bin/env bash
# Compiles and runs LspDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/solidlsp/discountpolicies/LspDemo.java
java -cp out solidlsp.discountpolicies.LspDemo
