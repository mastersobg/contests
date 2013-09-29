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

const int max_s = 1000000 + 1;

int times[max_s]; 

typedef pair < int, int > pair_t;
typedef map < int, vector < int > > cache_t;
typedef set < pair_t > was_t;

cache_t caches[3][2];
was_t was[3];

typedef long long ll;

int get_minus(int t, const vector < int > & v) {
  int result = 0;

  foreach(it, v) {
    int coord = *it;
    if(times[coord] != t) {
      ++result;
      times[coord] = t;
    }
  }

  return result;
}

int main() {
  freopen("cube.in", "r", stdin);
  freopen("cube.out", "w", stdout);

  int s, n;

  scanf("%d%d", &s, &n);
  ll remain = ll(s) * s * s;

  for(int i = 1; i <= n; i++) {
    int type;
    scanf("%d", &type);

    if(type == 2) {
      printf("%lld\n", remain);
    } else {
      int x, y, z, dx, dy, dz;
      scanf("%d%d%d%d%d%d", &x, &y, &z, &dx, &dy, &dz);
      ll minus = s;

      if(dx != 0) {
        if(was[0].find(pair_t(y, z)) != was[0].end()) {
          minus = 0;
        } else {
          minus -= get_minus(i, caches[1][1][z]);
          minus -= get_minus(i, caches[2][1][y]);
          caches[0][0][y].pb(z);
          caches[0][1][z].pb(y);
          was[0].insert(pair_t(y, z));
        }
      } else if(dy != 0) {
        if(was[1].find(pair_t(x, z)) != was[1].end()) {
          minus = 0;
        } else {
          minus -= get_minus(i, caches[0][1][z]);
          minus -= get_minus(i, caches[2][0][x]);
          caches[1][0][x].pb(z);
          caches[1][1][z].pb(x);
          was[1].insert(pair_t(x, z));
        }
      } else if(dz != 0) {
        if(was[2].find(pair_t(x, y)) != was[2].end()) {
          minus = 0;
        } else {
          minus -= get_minus(i, caches[0][0][y]);
          minus -= get_minus(i, caches[1][0][x]);
          caches[2][0][x].pb(y);
          caches[2][1][y].pb(x);
          was[2].insert(pair_t(x, y));
        }
      }

      remain -= minus;
    }
  }

  return 0;
}
