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
const int MAX_N = 50000;
#define filename ""

int v[MAX_N];
int n;

struct st {
    int a, b, c;

    st() {
        a = b = c = -1;
    }

    st(int a, int b, int c) {
        this->a = a;
        this->b = b;
        this->c = c;
    }
    st(const st &s) {
        this->a = s.a;
        this->b = s.b;
        this->c = s.c;
    }
    void init(int a, int b, int c) {
        this->a = a;
        this->b = b;
        this->c = c;
    }
    void add(int v) {
        if(v == 1)
            ++c;
        else if(v == 2)
            ++b;
        else ++a;
    }
    int cmp(st s) {
        if(a != s.a)
            return a - s.a;
        if(b != s.b)
            return b - s.b;
        return c - s.c;
    }
};

st dp[MAX_N];
int next[MAX_N];

bool can(int pos, int cnt) {
    if(pos + cnt > n)
        return false;
    int p = pos;
    bool less = true, more = true;
    for(int i = 1; i < cnt; ++i) {
        int cur = v[pos+i];
        if(cur <= v[p]);
        else less = false;
        p = pos + i;
    }
    p = pos;
    for(int i = 1; i < cnt; ++i) {
        int cur = v[pos+i];
        if(cur >= v[p]);
        else more = false;
        p = pos + i;
    }
    return less || more;
}
st rec(int pos) {
    if(pos >= n)
        return st(0,0,0);
    st &ret = dp[pos];
    if(ret.a == -1) {
        ret.init(0,0,0);
        for(int i = 1; i <= 3; ++i) {
            if(can(pos, i)) {
                st a = st(rec(pos + i));
                a.add(i);
                if(ret.cmp(a) < 0) {
                    ret = a;
                    next[pos] = i;
                }
            }
        }
    }
    return ret;
}

int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#endif
    cin >> n;
    for(int i = 0; i < n; ++i)
        cin >> v[i];
    //dbg(can(0, 3));
    st ret = rec(0);
    //cout << ret.a << " " << ret.b << " " << ret.c << endl;
    cout << ret.a + ret.b + ret.c << endl;
    int p = 0;
    while(p < n) {
        cout << p + next[p] << " ";
        p = p + next[p]; 
    }
    return 0;
}

