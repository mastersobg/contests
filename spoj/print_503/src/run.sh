#!/bin/bash

file=Main.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx1500m -ea ${name} LOCAL_DEBUG < input.txt
