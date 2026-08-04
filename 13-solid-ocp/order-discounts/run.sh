#!/usr/bin/env bash
# Compiles and runs OcpDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/solidocp/orderdiscounts/OcpDemo.java
java -cp out solidocp.orderdiscounts.OcpDemo
