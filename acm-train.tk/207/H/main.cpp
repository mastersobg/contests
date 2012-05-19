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
#define filename "sms"
const int MAX_N = 30;

int n, m;
int v[MAX_N];

int dp[MAX_N][10];
int prev[MAX_N][10];

int rec(int letter, int button) {
    if(letter >= n)
        return 0;
    if(button >= m)
        return INF;
    int &ret = dp[letter][button];
    if(ret != -1)
        return ret;
    int avail = n - letter;
    int sum = 0;
    ret = INF;
    for(int i = 1; i <= avail; ++i) {
        sum += v[letter + i - 1] * i;
        int a = rec(letter + i, button + 1) + sum;
        if(a < ret) {
            ret = a;
            prev[letter][button] = letter + i;
        }
    }
    return ret;
}
int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#else
    stdin =  freopen(filename ".in",  "r", stdin);
    stdout = freopen(filename ".out", "w", stdout);
#endif
    cin >> n >> m;
    for(int i = 0; i < n; ++i)
        cin >> v[i];
    memset(dp, -1, sizeof dp);
    rec(0, 0);
    int l = 0, b = 0;
    while(l < n) {
        cout << prev[l][b] - l << " ";
        l = prev[l][b];
        b++;
    }
    while(b++ < m)
        cout << "0 ";
    cout << endl;
    return 0;
}

