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
#define PROBLEM "billboard"




int main()
{
	//freopen("input.txt", "r", stdin); 
	//freopen("output.txt", "w", stdout);
	freopen(PROBLEM ".in", "r", stdin); freopen(PROBLEM ".out", "w", stdout);
	int k, n, m;
//	set<string> s;
	cin >> k >> n >> m;
	string t;
	vector<vector<int> > tt(n * m);
	for (int z = 0; z < k; ++ z)
		for (int i = 0; i < n; ++ i)
		{
			cin >> t;
			for (int j = 0; j < m; ++ j)
				tt[i * m + j].pb(t[j] == '*');
		}
	sort(tt.begin(), tt.end());
	tt.erase(unique(tt.begin(), tt.end()), tt.end());
	cout << tt.size() << endl;
	return 0;
}

/*	TEST ZONE BEGIN
	TEST ZONE END */

#endif
