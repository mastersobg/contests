from decimal import *
import math

def get(s, k):
  idx = 0
  ret = ""
  for c in s:
    ret += c
    idx += 1
    if idx == k:
      return ret

def pow(a, p, mod):
  ret = 1
  while p > 0:
    if (p & 1) == 1:
      ret = (ret * a) % mod
    a = (a * a) % mod
    p >>= 1
  return ret

t = int(raw_input())
for test in range(t):
  s = raw_input().split(' ')
  n = Decimal(int(s[0]) - 1)
  k = Decimal(int(s[1]))
  a = Decimal(2.0).log10() * n
  a = a - Decimal(int(a))
  a = Decimal(10.0) ** a
  first = getcontext().to_sci_string(a).replace('.', '')
  f = get(first, int(k))
  s = pow(2, int(n), int(math.pow(10, int(k))))
  print long(f) + long(s)

