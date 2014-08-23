#!/usr/bin/python

from __future__ import print_function
map = {}

def solve(n, k): 
	if map.get((n,k)) is not None:
		return map.get((n,k))
	if k == 0 or n == k:
		return 1;
	map[(n,k)] = solve(n - 1, k) + solve(n - 1, k - 1)
	return map[(n,k)]


for n in range(1, 101):
	for k in range(1, n + 1):
		print(solve(n, k), end=" ")
	print('\n')





