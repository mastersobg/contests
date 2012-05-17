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
#define dbg( x ) { cerr << #x << "=" << x << endl; }
#define dbgv( v ) { cerr << #v << "={";for( int I=0;I<(int)(v).size();++I)cerr << " " << (v)[I];cerr<<" }\n"; }
#define dbgm( v, n ) { cerr << #v << "={";for( int I=0;I<n;++I)cerr << " " << (v)[I];cerr<<" }\n"; }

const int INF = 1 << 29;
const int MAX_N = 10;
const int MASK= 1 << MAX_N;

map<string,int> n2i;
map<int,string> i2n;

int get_idx(string &name) {
    if(n2i.count(name) == 0) {
        int size = n2i.size();
        n2i[name] = size;
        i2n[size] = name;
    }
    return n2i[name];
}

string get_name(char &idx) {
    return i2n[idx];
}

int n, m;
set<char> g[MAX_N];
char cmove[MASK][MASK];
int dp[MASK];
int cprev[MASK];

int rec(int mask) {
    int &ret = dp[mask];
    if(ret != -1)
        return ret;
    if(mask != 0) {
        ret = INF;
        for(int m = mask; m; m = (m - 1) & mask) {
            if(cmove[mask][m]) {
                int next_mask = mask ^ m;
                int f = rec(next_mask) + 1;
                if(f < ret) {
                    ret = f;
                    cprev[mask] = next_mask;
                }
            }
        }
    }
    else {
        ret = 0;   
    }
    return ret;
}
void bits(int t[MAX_N], int &size, int &mask) {
    size = 0;
    for(int i = 0; i < n; ++i)
        if(!(mask & (1 << i)))
            t[size++] = i;
}

char was[MAX_N];
char ri[MAX_N];
char mt[MAX_N];

int dfs(int v) {
    if(was[v])
        return 0;
    was[v] = 1;
    for(set<char> :: iterator it = g[v].begin(); it != g[v].end(); ++it) {
        int u = *it;
        if(ri[u] && mt[u] == -1) {
            mt[u] = v;
            return 1;
        }
    }
    for(set<char> :: iterator it = g[v].begin(); it != g[v].end(); ++it) {
        int u = *it;
        if(ri[u] && dfs(mt[u])) {
            mt[u] = v;
            return 1;
        }
    }
    return 0;
}
char can(int t[], int size, int mask, vector<pair<char,char> > &pairs) {
    memset(mt, -1, sizeof mt);
    memset(ri,  0, sizeof ri);
    int bits = 0;
    for(int i = 0; i < n; ++i)
        if(mask & (1<<i)) {
            ri[i] = 1;
            ++bits;
        }
    for(int i = 0; i < size; ++i)
        if(ri[t[i]])
            return 0;
    int ret = 0;
    for(int i = 0; i < size; ++i) {
        memset(was, 0, sizeof was);
        ret += dfs(t[i]);
    }
    if(ret != bits)
        return 0;
    pairs.reserve(bits);
    for(int i = 0; i < n; ++i)
        if(mt[i] != -1)
            pairs.pb(mp(mt[i], i));
    return 1;
}
int main() {
#ifndef ONLINE_JUDGE
    stdin = freopen("input.txt", "r", stdin);
#endif
    cin >> n >> m;
    for(int i = 0; i < m; ++i) {
        string s1, s2;
        cin >> s1 >> s2;
        int idx1 = get_idx(s1), idx2 = get_idx(s2);
        g[idx1].insert(idx2);
        g[idx2].insert(idx1);
    }
    int t[MAX_N], size;
    string sroot;
    cin >> sroot;
    int root = get_idx(sroot);
    
    int mask = ((1 << n) - 1) ^ (1 << root);
    for(int i = 0; i < MASK; ++i) {
        bits(t, size, i);
        for(int j = 0; j < MASK; ++j) {
            vector<pair<char,char> > vec;
            cmove[i][j] = can(t, size, j, vec);
        }
    }
    memset(dp, -1, sizeof dp);
    cout << rec(mask) << endl;
    while(mask) {
        int m = cprev[mask] ^ mask;
        vector<pair<char, char> > vec;
        bits(t, size, mask);
        vector<pair<char,char> > v;
        can(t, size, m, v);
        cout << v.size() << endl;
        for(int i = 0; i < (int)v.size(); ++i)
            cout << get_name(v[i].x) << " " << get_name(v[i].y) << endl;
        mask = cprev[mask];
    }
    return 0;
}

