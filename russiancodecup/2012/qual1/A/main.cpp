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
#define filename ""

int v[12];

int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#endif
    while(true) {
        bool not0 = false;
        map<int,int> smap;
        for(int i = 0; i < 12; ++i) {
            cin >> v[i];
            not0 |= v[i];
            smap[v[i]]++;
        }
        if(!not0)
            break;
        int ret = 0;
        for(map<int,int>::iterator it = smap.begin(); it != smap.end(); ++it)
            ret += it->y / 4;
        cout << (ret == 3 ? "yes\n" : "no\n");
    }
    return 0;
}

