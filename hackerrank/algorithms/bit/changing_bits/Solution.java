import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    class Number {

        byte []bits;
        int len;
        
        Number(byte []ret, int n) {
            bits = ret;
            len = n;
        }
        void setBit(int idx, byte value) {
            bits[idx] = value;
        }

        byte []add(Number n) {
            byte []ret = new byte[max(n.len, this.len) + 1];
            byte carry = 0;
            for (int i = 0; i < min(n.len, this.len); ++i) {
                carry += bits[i] + n.bits[i];
                ret[i] = (byte) (carry % 2);
                carry /= 2;
            }
            for (int i = min(n.len, this.len); i < max(n.len, this.len); ++i) {
                carry += (i < n.len ? n.bits[i] : this.bits[i]);
                ret[i] %= (byte) (carry % 2);
                carry /= 2;
            }
            int retLen = max(n.len, this.len);
            if (carry != 0) {
                ret[max(n.len, this.len)] = carry;
                ++retLen;
            }
            return ret;
        }
    }

	void solve() throws IOException {
        int n = ni();
        int q = ni();
        Number a = new Number(read(n), n);
        Number b = new Number(read(n), n);
        for (int it = 0; it < q; ++it) {
            String s = ns();
            if (s.equals("set_a")) {
                int idx = ni();
                int bit = ni();
                a.setBit(idx, (byte) bit);
            } else if (s.equals("set_b")) {
                int idx = ni();
                int bit = ni();
                b.setBit(idx, (byte) bit);            
            } else {
                int idx = ni();
                out.print(a.add(b)[idx]);
            }
        }
	}

    byte []read(int n) throws IOException {
        byte []ret = new byte[n];
        String s = ns();
        for (int i = 0; i < s.length(); ++i)
            ret[i] = (byte) (s.charAt(i) - '0');
        return ret;
    }

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}

	long nl() throws IOException {
		return Long.valueOf(ns());
	}

	double nd() throws IOException {
		return Double.valueOf(ns());
	}

	public static void main(String[] args) throws IOException {
		new Solution().run();
	}
}
