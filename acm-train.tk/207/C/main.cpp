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
#define filename "fibstring"

bool check(string &s, int start, int end) {
    for(; start < end; ++start, --end)
        if(s[start] != s[end])
            return false;
    return true;
}
int get_pal(string &s) {
    int len = (int)s.size();
    int ret = 0;
    for(int i = 0; i < len; ++i)
        for(int j = i; j < len; ++j)
            if(check(s, i, j))
                ret = max(ret, j - i + 1);
    return ret;
}
int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#else
    stdin =  freopen(filename ".in",  "r", stdin);
    stdout = freopen(filename ".out", "w", stdout);
#endif
    //string v[20];
    //v[0] = "a";
    //v[1] = "b";
    //int n = 12;
    //for(int i = 2; i < n; ++i) {
    //    v[i] = v[i-2] + v[i-1];
    //}
    //for(int i = 0; i < n; ++i) {
    //    cerr << i << " " << get_pal(v[i]) << endl;
    //}
    int n;
    cin >> n;
    if(n < 3)
        cout << 1;
    else if(n < 5)
        cout << n;
    else {
        ll ret = 6;
        ll a = 5;
        ll b = 3;
        for(int i = 5; i < n; ++i) {
            ret += a;
            ll next = a + b;
            b = a;
            a = next;
        }
        cout << ret;
    }
    cout << endl;
    return 0;
}

