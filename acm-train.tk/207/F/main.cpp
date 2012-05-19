#include<iostream>
#include<cstdio>
#include<set>
#include<map>
#include<vector>
#include<queue>
#include<algorithm>
#include<string>
#include<cstring>
#include<memory.h>
#include<cmath>
#include<cassert>
#include<sstream>
#include<cstdlib>

using namespace std;

typedef long long ll;
typedef pair<int,int> pii;
typedef vector<int> vi;
typedef vector<vi> vii;
typedef vector<pii> vpii;

#define x first
#define y second
#define mp make_pair
#define pb push_back
#define dbg(x) { cerr << #x << "=" << x << endl; }
#define dbgv(v) { cerr << #v << "={";for( int I=0;I<(int)(v).size();++I)cerr << " " << (v)[I];cerr<<" }\n"; }
#define dbgm(v, n) { cerr << #v << "={";for( int I=0;I<n;++I)cerr << " " << (v)[I];cerr<<" }\n"; }

const int INF = 1 << 29;
#define filename "monsters"
const int MAX_K = 5;

int n;
int v[MAX_K];

int can(int level) {
    int ret = 0;
    bool wasNot0 = false;
    for(int i = 0; i < n; ++i) {
        if(v[i] > 0)
            wasNot0 = true;
        if(v[i] >= level) {
            v[i] -= level;
            ret |= can(level << 1);
            v[i] += level;
        }
    }
    if(!wasNot0)
        return 1;
    return ret;
}
int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#else
    stdin =  freopen(filename ".in",  "r", stdin);
    stdout = freopen(filename ".out", "w", stdout);
#endif
    cin >> n;
    for(int i = 0; i < n; ++i)
        cin >> v[i];
    cout << (can(1) ? "Yes\n" : "No\n");
    return 0;
}

