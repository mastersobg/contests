#!/usr/bin/python

from __future__ import print_function
import sys
sys.setrecursionlimit(100000)

def solve(n, k):
    if k == 0 or n == k: return 1
    return solve(n + 1, k) - solve(n, k - 1)


for n in range(1, 11):
	for k in range(1, n + 1):
		print(solve(n, k), end=" ")
	print('\n')


