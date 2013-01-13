#if 1
#include <numeric>
#include <iostream>
#include <vector>
#include <sstream>
#include <string>
#include <set>
#include <map>
#include <queue>
#include <stack>
#include <ctime>
#include <cassert>
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <bitset>
using namespace std;

typedef long long LL;
typedef long double LD;
typedef pair<int, int> pii;
typedef vector<int> veci;
typedef vector<veci> graph;

const int inf = 1000 * 1000 * 1000;
const LL inf64 = inf * LL(inf);
const LD eps = 1e-9;
const LD pi = acos(-1.0);

#define pb push_back
#define mp make_pair
#define X first
#define Y second
#define dbg(x) {cerr << #x << " = " << x << endl; }
#define dbgv(x) { cerr << #x << " = {"; for(int _i = 0; _i < (x).size(); ++_i) { if(_i) cerr << ", "; cerr << (x)[_i]; } cerr << "}" << endl; }
#define iss istringstream
#define oss ostringstream
#define PROBLEM "exam"

LL xabs(LL a)
{
	return a < 0? -a: a;
}
LL solve2(const vector<int> &a)
{
	int n = a.size();
	LL h = -a[n - 1], l = -a[0];
	for (int i = 0; i + 1 < n; ++ i)
		if (a[i + 1] > a[i])
			h += a[i + 1] - a[i];
		else
			l += a[i] - a[i + 1];
	h = max(h, 0LL);
	l = max(l, 0LL);
	LL res = (h + l + 1) / 2;

	return res;
}
LL solve3(vector<int> a)
{
	LL best = inf64;
	for(LL p = -100; p <= a[0] + 1000; ++p)
	{
		LL oldp =p;
		// dbg(p);

		//	LL p = a[0]; 
		LL q = a[0] - p;
		LL res = 0;
		if(q < 0)
			res += -q;
		if(p < 0)
			res += -p;
		for(int i = 1; i < a.size(); ++i)
		{
			LL cur = a[i] - q;
			LL pp = min(cur, p);
			p = pp;
			q = a[i] - p;
			if(p < 0)
				res += -p;
			if(q < 0)
				res += -q;
		}
		best = min(best, res);
		p = oldp;
	}
	return best;
}
void solve()
{
	int n;
	cin >> n;
	vector<int> a(n);
	for (int i = 0; i < n; ++ i)
		cin >> a[i];

	LL res = min(solve3(a), solve2(a));
	cout << res << endl;
}

int main()
{
	//freopen("input.txt", "r", stdin); 
	//freopen("output.txt", "w", stdout);
	freopen(PROBLEM ".in", "r", stdin); freopen(PROBLEM ".out", "w", stdout);
	int t;
	cin >> t;
	for (int i = 0; i < t; ++ i)
		solve();

	return 0;
}

/*	TEST ZONE BEGIN
	TEST ZONE END */

#endif
