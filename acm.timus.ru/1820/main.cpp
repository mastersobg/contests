#include <iostream>
#include <cmath>

using namespace std;

int main() {
    int n, k;
    cin >> n >> k;
    int one_side = 0;
    int ret = 0;
    while(n > 0 || one_side > 0){
        int can = min(k, n);
        n -= can;
        int add = min(k - can, one_side);
        one_side -= add;
        one_side += can;
        ++ret;
    }
    cout << ret << endl;
    return 0;
}

