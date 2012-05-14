#include <iostream>
#include <cmath>
#include <vector>
#include <cstdio>

using namespace std;

int main() {
    long long a;
    vector<long long> v;
    while(cin >> a) {
        v.push_back(a);
    }
    for(int i = v.size() - 1; i >= 0l; --i) {
        double ret = sqrt(v[i] + 0.0);
        printf("%.4f\n", ret);
    }
    return 0;
}
