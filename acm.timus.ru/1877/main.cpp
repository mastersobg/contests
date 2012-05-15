#include <iostream>
#include <algorithm>

using namespace std;

int main(){
    int f, s;
    cin >> f >> s;
    for(int i = 0; i < 10000; ++i) {
        if(i == f){
            cout << "yes\n";
            return 0;
        }
        swap(f, s);
    }
    cout << "no\n";
    return 0;
}
