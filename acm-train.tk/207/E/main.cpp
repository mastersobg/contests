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
#define filename "graphgame"

int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#else
    stdin =  freopen(filename ".in",  "r", stdin);
    stdout = freopen(filename ".out", "w", stdout);
#endif
    int n, m;
    cin >> n >> m;
    //how many roads we can remove from graph to get a tree
    int can = m - n + 1;
    if(m == 0)
        cout << "Draw\n";
    else if(can & 1)
        cout << "Bob\n";
    else cout << "Alice\n";
    return 0;
}

