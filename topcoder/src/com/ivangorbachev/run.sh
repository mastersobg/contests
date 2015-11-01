#!/bin/bash

file=$1
name=${file%.*}

javac ${file} && java -client -Xmx64m -ea ${name}
