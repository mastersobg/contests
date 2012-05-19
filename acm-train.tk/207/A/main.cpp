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
#define filename "army3"
const int MAX_N = 100000;

int n, k;
vi v;

int cmp(const pii &p1, const pii &p2) {
    if(p1.y != p2.y)
        return p1.y < p2.y;
    return p1.x < p2.x;
}
void compress(vi &source) {
    vpii v(source.size());
    for(int i = 0; i < (int)source.size(); ++i)
        v[i] = mp(source[i], i);
    sort(v.begin(), v.end());
    vi t(source.size());
    t[0] = 1;
    for(int i = 1; i < (int)v.size(); ++i) {
        t[i] = t[i - 1] + (v[i].x != v[i-1].x); 
    }
    for(int i = 0; i < (int)v.size(); ++i)
        v[i].x = t[i];
    sort(v.begin(), v.end(), cmp);
    for(int i = 0; i < (int)v.size(); ++i)
        source[i] = v[i].x;
}

void add(int t[], int n, int idx, int value) {
    for(; idx < n; idx += idx & -idx) 
        t[idx] += value;
}
int get(int t[], int idx) {
    if(idx == 0)
        return 0;
    int ret = 0;
    for(; idx > 0; idx -= idx & -idx)
        ret += t[idx];
    return ret;
}

const int T_SIZE = MAX_N + 1;

int tree[T_SIZE];
int h[MAX_N];

int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#else
    stdin =  freopen(filename ".in",  "r", stdin);
    stdout = freopen(filename ".out", "w", stdout);
#endif
    cin >> n >> k;
    v.resize(n);
    for(int i= 0; i < n; ++i)
        assert(scanf("%d", &v[i]) != EOF);
    compress(v);
    for(int i = 0; i < n; ++i) {
        int height = get(tree, v[i] - 1);
        h[i] += height;
        add(tree, T_SIZE, v[i], 1);
    }
    memset(tree, 0, sizeof tree);
    for(int i = n - 1; i >= 0; --i) {
        int height = get(tree, v[i] - 1);
        h[i] += height;
        add(tree, T_SIZE, v[i], 1);
    }
    ll total = 0;
    for(int i = 0; i < k; ++i)
        total += h[i];
    ll max = total;
    int pos = 0;
    for(int i = k; i < n; ++i) {
        total -= h[i - k];
        total += h[i];
        if(total > max) {
            max = total;
            pos = i - k + 1;
        }
    }
    cout << pos + 1 << endl;
    return 0;
}   

