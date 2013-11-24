#!/bin/bash

file=Solution.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx64m -ea ${name} LOCAL_DEBUG -verbose:gc -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
