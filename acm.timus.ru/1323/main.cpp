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
#define dbgv( v ) { cerr << #v << "={";for( int I=0;I<(int)(v).size();++I)cerr << " " << (v)[i];cerr<<" }\n"; }
#define dbgm( v, n ) { cerr << #v << "={";for( int I=0;I<n;++I)cerr << " " << (v)[i];cerr<<" }\n"; }

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

string get_name(int &idx) {
    return i2n[idx];
}

int n, m;
set<char> g[MAX_N];
char move[MASK][MASK];
vpii move_p[MASK][MASK];
int dp[MASK];
pair<vpii,int> prev[MASK];

int rec(int mask) {
    int &ret = dp[mask];
    if(ret != -1)
        return ret;
    if(mask != 0) {
        ret = INF;
        for(int m = mask; m; m = (m - 1) & mask) {
            if(move[mask][m]) {
                int next_mask = mask ^ m;
                int f = rec(next_mask) + 1;
                if(f < ret) {
                    ret = f;
                    prev[mask] = mp(move_p[mask][m], next_mask);
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
char can(int t[], int size, int mask, vpii &pairs) {
    for(int i = 0; i < n; ++i) 
        if(mask & (1<<i)) {
            int found = 0;
            for(int j = 0; j < size; ++j) {
                int v = t[j];
                if(g[v].find(i) != g[v].end()) {
                    pairs.pb(mp(v, i));
                    found = 1;
                    break;
                }
            }
            if(!found)
                return 0;
        }
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
    for(int i = 0; i < MASK; ++i) {
        bits(t, size, i);
        for(int j = 0; j < MASK; ++j) {
            vpii vec;
            //dbg(i);
            //dbg(j);
            move[i][j] = can(t, size, j, vec);
            move_p[i][j] = vec;
        }
    }
    memset(dp, -1, sizeof dp);
    cout << rec((1<<n) - 2) << endl;
    int mask = (1<<n) - 2;
    while(mask) {
        vpii v = prev[mask].x;
        cout << v.size() << endl;
        for(int i = 0; i < (int)v.size(); ++i)
            cout << get_name(v[i].x) << " " << get_name(v[i].y) << endl;
        mask = prev[mask].y;
    }
    return 0;
}

