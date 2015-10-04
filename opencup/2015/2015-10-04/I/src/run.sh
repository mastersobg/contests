#!/bin/bash

file=Solution.java
name=${file%.*}

javac -target 1.7 -source 1.7 ${file} && echo "compiled" && java -Xmx64m -ea ${name} LOCAL_DEBUG
