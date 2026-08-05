#!/usr/bin/env bash
# Compiles and runs OcpDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/solidocp/orderdiscounts/example/*.java src/solidocp/orderdiscounts/exercise/*.java
java -cp out solidocp.orderdiscounts.example.OcpDemo
