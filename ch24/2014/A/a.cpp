#if 1
#include <cstdlib>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <functional>
#include <cstring>
#include <string>
#include <vector>
#include <list>
#include <set>
#include <map>
#include <stack>
#include <queue>
#include <ctime>
#include <cassert>
#include <sstream>
#include <iostream>
#include <bitset>

using namespace std;

typedef long long LL;
typedef long double LD;
typedef pair<int , int> pii;
typedef vector <int> veci;
typedef vector <veci> graph;
const LD eps = 1e-9;
const LD pi = acos(-1.0);
const int inf = 1000 * 1000 * 1000;
const LL inf64 = LL(inf) * inf;

#define mp make_pair
#define pb push_back
#define X first
#define Y second
#define iss istringstream
#define oss ostringstream
#define dbg(x) {cerr << #x << " = " << x << endl;}
#define dbgv(x) {cerr << #x << " ={"; for (int _i = 0; _i < x.size(); _i++) {if (_i) cerr << ", "; cerr << x[_i];} cerr << "}" << endl;}
#define NAME "problem"
vector<string> a;

int n;
vector<int> mlt;
int st[60];
void decode(int state)
{
    for (int i = 0; i < n; ++i)
    {
        st[i] = state % a[i].size();
        state /= a[i].size();
    }
}

void press(int j)
{
    st[j]++;
    if (st[j] == a[j].size())
        st[j] = 0;
}

void unpress(int j)
{
    st[j]--;
    if (st[j] == -1)
        st[j] = a[j].size() - 1;
}

int code()
{
    int state = 0;
    for (int j = n - 1; j >= 0; --j)
    {
        //if (j + 1 < n)
            state *= a[j].size();
        state += st[j];
    }
    return state;
}

typedef int itype;
itype *row1, *row2;
int all;

void upd(itype *row, int state, int val)
{
    if (row[state] == -1 || row[state] > val)
        row[state] = val;
}

int get_code(int state, int p)
{
    //assert(state >= 0 && state < all && "in");
    int prev = st[p];
    int next = prev + 1;
    if (next == a[p].size()) next = 0;
    if (next == -1) next = a[p].size() - 1;

    state += (next - prev) * mlt[p];
    //if (state < 0)
    //{
    //    cerr << "asdf" << endl;
    //}
    //assert(state >= 0 && state < all && "out");
    return state;
}
int solve(const string &s)
{
    for (int i = 0; i < all; ++i)
    {
        decode(i);

        int sum = 0;
        for (int j = 0; j < n; ++j)
            sum += st[j];
        row1[i] = sum;
    }


    for (int i = 0; i < s.size(); ++i)
    {
        memset(row2, -1, sizeof(row2[0]) * all);
        char ch = s[i];
        bool no = true;
        for (int state = 0; state < all; ++state) if (row1[state] >= 0)
        {
            decode(state);
            //int fstate = code();
            //if (state != fstate)
            //{
            //    cerr << "asdf" << endl;
            //    assert(false);
            //}
            for (int j = 0; j < n; ++j)
            {
                if (a[j][st[j]] == ch)
                {
                    no = false;
                    int nstate = get_code(state, j);
                    upd(row2, nstate, row1[state] + 1);
                }
            }

        }
        
        swap(row1, row2);        
        if (no) break;
    }
    int res = inf;
    for (int i = 0; i < all; ++i)
        if (row1[i] != -1)
            res = min(res, row1[i]);
    if (res == inf)
        res = -1;
    return res;
}

int main(int argc, char * argv[])
{
    
    //freopen(NAME ".in","r",stdin);freopen(NAME ".out","w",stdout);
    if (argc > 1)
    {
        string pref = argv[1];
        freopen((pref + ".in").c_str(), "r", stdin);
        freopen(("A" + pref + ".out").c_str(), "w", stdout);
    } else
    {
        freopen("input.txt", "r", stdin); //freopen("output.txt", "w", stdout);
    }
    
    cin >> n;    
    a.resize(n);
    for (int i = 0; i < n; ++i)
        cin >> a[i];
    mlt.resize(n + 1);
    mlt[0] = 1;
    for (int i = 0; i < n; ++i)
        mlt[i + 1] = mlt[i] * a[i].size();
    all = mlt.back();
    row1 = new itype[all];
    row2 = new itype[all];

    int m;
    cin >> m;
    clock_t tstart = clock();
    for (int i = 0; i < m; ++i)
    {
        dbg(i);
        string s;
        cin >> s;
        int ret = solve(s);
        cout << ret << endl;
        dbg(ret);
        dbg((clock() - tstart) / LD(CLOCKS_PER_SEC));
    }

    delete []row1;
    delete []row2;
    return 0;
}
/*************************
*************************/
#endif
