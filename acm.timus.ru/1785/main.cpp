#include <iostream>
#include <cstring>
#include <string>

using namespace std;

int count[9] = {0, 5, 10, 20, 50, 100, 250, 500, 1000};
string name[9] = {"few", "several", "pack", "lots", "horde", "throng", "swarm", "zounds", "legion"};

int main() {
    int n;
    cin >> n;
    int i;
    for(i = 0; i < 8; ++i)
        if(n >= count[i] && n < count[i+1])
            break;
    cout << name[i] << endl;
    return 0;
}
