#!/bin/bash

file=Solution.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx256m -ea ${name} LOCAL_DEBUG
