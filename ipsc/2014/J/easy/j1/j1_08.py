#!/usr/bin/python

from __future__ import print_function
import sys
sys.setrecursionlimit(100000)

def solve(n, k):
    res = 1
    for i in range(n):     res = res *  (i + 1)
    for i in range(k):     res = res // (i + 1)
    for i in range(n - k): res = res // (i + 1)
    return res


for n in range(1, 11):
	for k in range(1, n + 1):
		print(solve(n, k), end=" ")
	print('\n')



