#!/usr/bin/env bash
# Compiles and runs AdapterDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/adapter/paymentadapter/example/*.java src/designpatterns/adapter/paymentadapter/exercise/*.java
java -cp out designpatterns.adapter.paymentadapter.example.AdapterDemo
