#!/bin/bash

file=Solution.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx5G -ea ${name} LOCAL_DEBUG
