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
#include <iterator>
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
#define PROBLEM "rag"


struct vec_t
{
	int x, y;
	vec_t() {}
	vec_t(int x, int y) : x(x), y(y) {}
	friend ostream& operator << (ostream& o, const vec_t &a)
	{
		return o << "(" << a.x << ", " << a.y << ")";
	}
};

struct segment_t
{
	vec_t p, q;
	LL a, b, c;
	segment_t() {}
	segment_t(vec_t _a, vec_t _b)
	{
		if(_a.y > _b.y)
			swap(_a, _b);
		p = _a;
		q = _b;
		a = q.y - p.y;
		b = p.x - q.x;
		c = - p.x * a - p.y * b;
	}

	LD get_by_y(int y) const
	{
		return LD(- b * y - c) / LD(a);
	}
	friend ostream& operator << (ostream& o, const segment_t &a)
	{
		return o << a.p << "-" << a.q;
		   
	}
};

vector<int> y;
int get_y(int x)
{
	return lower_bound(y.begin(), y.end(), x) - y.begin();
}

int cmp_y2;

struct compare_segments
{
	bool operator()(const segment_t &a, const segment_t &b)
	{
		// x1 = (-a.b * cmp_y2 - 2*a.c) / (2*a.a)
		// x2 = (-b.b * cmp_y2 - 2*b.c) / (2*b.a)
		return (-a.b * cmp_y2 - 2*a.c) / LD(2*a.a)  < (-b.b * cmp_y2 - 2*b.c) / LD(2*b.a);
	}
};

struct segments_set_t
{
	set<segment_t, compare_segments> s;

	void add_segment(const segment_t &seg)
	{
		s.insert(seg);
	}

	void change_mid(int y1, int y2)
	{
		cmp_y2 = y1 + y2;
	}

	void del_segment(const segment_t &seg)
	{
		set<segment_t, compare_segments> :: iterator it = s.find(seg);
		assert(it != s.end());
		s.erase(it);
	}

	segment_t get_max() const
	{
		assert(s.size() > 0);
		segment_t seg = *(--s.end());
		return seg;
	}
	void output()
	{
		copy(s.begin(), s.end(), ostream_iterator<segment_t>(cerr, ", "));
		cerr << endl;
	}
};

LD get_square(int y1, int y2, const segment_t &seg)
{
	LD x1 = seg.get_by_y(y1);
	LD x2 = seg.get_by_y(y2);
	return fabs(x2 + x1) * fabs(y2 - y1 + 0.0) / 2.0;
}

int main()
{
	//freopen("input.txt", "r", stdin); 
	//freopen("output.txt", "w", stdout);
	freopen(PROBLEM ".in", "r", stdin); freopen(PROBLEM ".out", "w", stdout);

	int w, h;
	scanf("%d%d", &w, &h);
	int n;
	scanf("%d", &n);
	vector<vec_t> a(n);
	for(int i = 0; i < n; ++i)
		scanf("%d%d", &a[i].x, &a[i].y);

	a.pb(vec_t(w, 0));
	reverse(a.begin(), a.end());
	n = a.size();

	for(int i = 0; i < n; ++i)
		y.pb(a[i].y);

	sort(y.begin(), y.end());
	y.erase(unique(y.begin(), y.end()), y.end());

	vector< segment_t > seg;
	for(int i = 0; i + 1 < n; ++i)
		if(a[i].y != a[i + 1].y)
			seg.pb(segment_t(a[i], a[i + 1]));
	vector< vector<int> > open(y.size()), close(y.size());
	for(int i = 0; i < seg.size(); ++i)
	{
		int y1 = get_y(seg[i].p.y);
		int y2 = get_y(seg[i].q.y);
		open[y1].pb(i);
		close[y2].pb(i);
	}

	segments_set_t s;
	LD area = 0;
	for(int i = 0; i + 1 < y.size(); ++i)
	{
		// delete
		for(int j = 0; j < close[i].size(); ++j)
			s.del_segment(seg[close[i][j]]);

		s.change_mid(y[i], y[i + 1]);

		for(int j = 0; j < open[i].size(); ++j)
			s.add_segment(seg[open[i][j]]);

		// s.output();
		
		segment_t seg = s.get_max();
		LD cur_area = get_square(y[i], y[i + 1], seg);
		// cerr << y[i] << " .. " << y[i + 1] << " x " << seg.p << " " << seg.q << endl;
		area += cur_area;
		
	}
	area = LD(w) * LD(h) - area;
	cout.precision(10);
	cout.setf(ios::fixed);
	cout << area << endl;
	

	return 0;
}

/*	TEST ZONE BEGIN
	TEST ZONE END */

#endif
