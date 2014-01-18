#!/bin/bash

file=Solution.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx1064m -ea ${name} LOCAL_DEBUG
