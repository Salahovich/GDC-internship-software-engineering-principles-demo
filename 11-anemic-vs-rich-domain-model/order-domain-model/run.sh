#!/usr/bin/env bash
# Compiles and runs AnemicVsRichDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/anemicvsrich/orderdomainmodel/example/*.java src/anemicvsrich/orderdomainmodel/exercise/*.java
java -cp out anemicvsrich.orderdomainmodel.example.AnemicVsRichDemo
