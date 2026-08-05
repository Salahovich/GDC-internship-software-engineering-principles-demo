#!/usr/bin/env bash
# Compiles and runs LspDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/solidlsp/discountpolicies/before/*.java src/solidlsp/discountpolicies/example/*.java src/solidlsp/discountpolicies/exercise/*.java
java -cp out solidlsp.discountpolicies.example.LspDemo
