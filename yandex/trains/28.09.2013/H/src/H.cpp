#include <iostream>
#include <cstdio>
#include <cassert>
#include <algorithm>
#include <cmath>
#include <ctime>
#include <stdint.h>

#include <vector>
#include <map>
#include <set>
#include <queue>
#include <cstring>
#include <bitset>
#include <deque>
#include <list>
#include <stack>

using namespace std;

typedef long long ll;

#define mp make_pair
#define pb push_back
#define all(v) (v).begin(), (v).end()
#define forn(i, n) for(int i = 0; i < int(n); ++i)
#define foreach(it, v) for(__typeof((v).begin()) it = (v).begin(); it != (v).end(); ++it)
#define sz(v) int((v).size())

typedef pair < int, int > pair_t;

int bit_count(int mask) {
  int res = 0;
  while(mask != 0) {
    res += (mask & 1);
    mask >>= 1;
  }
  return res;
}

bool check_bit(int mask, int bit) {
  return ((mask & (1 << bit)) != 0);
}

int get_coolness(int n, int mask) {
  int result = n;
  for(int i = 0; i < n; i++) {
    for(int j = i + 1; j < n; j++) {
      if(check_bit(mask, i) && check_bit(mask, j)) {
        result = min(result, j - i);
      }
    }
  }
  return result;
}

double start;

void rec(int n, int m, int id, const vector < pair_t > & types,
         const vector < vector < pair_t > > & masks,
         vector < vector < int > > already, int result,
         int & best_result, vector < vector < int > > & best_result_already) {
  double gone = double((clock() - start)) / CLOCKS_PER_SEC;

  if(gone > 0.8)
    return;

  if(id == types.size()) {
    if(result > best_result) {
      best_result = result;
      best_result_already = already;
    }
  } else {
    bool found = false;
    pair_t p = types[id];
    if(p.first <= n) {
      foreach(it, masks[p.first]) {
        pair_t pair = *it;
        int mask = pair.second, add = pair.first;
        bool can = true;
        for(int i = 0; i < n && can; i++) {
          if(check_bit(mask, i) && already[i].size() == m) {
            can = false;
          }
        }
        if(can) {
          found = true;
          vector < vector < int > > new_already(already);
          for(int i = 0; i < n; i++) {
            if(check_bit(mask, i)) {
              new_already[i].pb(p.second);
            }
          }
          rec(n, m, id + 1, types, masks, new_already, result + add, best_result, best_result_already);
        }
      }
    }
    if(!found) {
      vector < vector < int > > new_already(already);
      for(int i = 0; i < p.first; i++) {
        int min_index_size = 0;
        for(int j = 0; j < n; j++) {
          if(new_already[j].size() < new_already[min_index_size].size()) {
            min_index_size = j;
          }
        }
        new_already[min_index_size].pb(p.second);
      }
      rec(n, m, id + 1, types, masks, new_already, result, best_result, best_result_already);
    }
  }
}

int main() {
  freopen("seating.in", "r", stdin);
  freopen("seating.out", "w", stdout);

  start = clock();

  int k, m, n;
  scanf("%d%d%d", &k, &m, &n);
  vector < pair_t > types(k);
  for(int i = 0; i < k; i++) {
    pair_t pair;
    scanf("%d", &pair.first);
    pair.second = i;
    types.pb(pair);
  }
  sort(types.begin(), types.end());
  vector < vector < pair_t > > masks(n + 1);
  for(int mask = 0; mask < (1 << n); mask++) {
    int bits = bit_count(mask);
    if(bits > 1) {
      masks[bit_count(mask)].pb(pair_t(get_coolness(n, mask), mask));
    }
  }
  for(int i = 2; i <= n; i++) {
    sort(masks[i].rbegin(), masks[i].rend());
  }
  vector < vector < int > > already(n), best_result_already(n);
  int best_result = -1;

  rec(n, m, 0, types, masks, already, 0, best_result, best_result_already);

  printf("%d\n", best_result);

  for(int i = 0; i < n; i++) {
    for(int j = 0; j < m; j++) {
      if(j >= best_result_already[i].size()) {
        printf("0 ");
      } else {
        printf("%d ", best_result_already[i][j] + 1);
      }
    }
    printf("\n");
  }



  return 0;
}
