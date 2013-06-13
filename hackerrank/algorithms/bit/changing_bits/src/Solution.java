import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	long[] a, b, ret;

    final static int SIZE = 64;
    final static long ONES;

    static {
        long ret = 1;
        for (int i = 1; i < SIZE; ++i) {
            ret <<= 1L;
            ret += 1L;
        }
        ONES = ret;
    }

	void solve() throws IOException {
		int n = ni();
		int q = ni();
		a = read(n);
		b = read(n);
		ret = add(a, b, n);
		for (int it = 0; it < q; ++it) {
			String s = ns();
			if ("set_a".equals(s)) {
				int idx = ni();
				int bit = ni();
				set(a, b, idx, bit);
			}
			if ("set_b".equals(s)) {
				int idx = ni();
				int bit = ni();
				set(b, a, idx, bit);
			}
			if ("get_c".equals(s)) {
				int idx = ni();
				out.print(getBit(ret, idx / SIZE, idx % SIZE));
			}
/*            dbg("a=", Arrays.toString(a));
            dbg("b=", Arrays.toString(b));
            dbg("ret=", Arrays.toString(ret));
            dbg();
            */
		}
		out.println();
	}
	void set(long[] a, long[] b, int pos, int value) {
        int idx = pos / SIZE;
        int bit = pos % SIZE;
		if (getBit(a, idx, bit) == value) {
			return;
		}
		if (value == 0) {
			if (getBit(b, idx, bit) == 0) {
				if (getBit(ret, idx, bit) == 1) {
                    setBit(ret, idx, bit, 0);
				} else {
                    find(ret, pos, 1);
				}
			} else {
                find(ret, pos, 1);
			}
		} else {
			if (getBit(b, idx, bit) == 0) {
				if (getBit(ret, idx, bit) == 1) {
                    find(ret, pos, 0);
				} else {
                    setBit(ret, idx, bit, 1);
				}
			} else {
                find(ret, pos, 0);
			}
		}
        setBit(a, idx, bit, value);
	}

    void find(long []a, int pos, int value) {
        int idx = pos / SIZE;
        int bit = pos % SIZE;
        for (int i = bit; i < SIZE; ++i) {
            if (getBit(a, idx, i) == value) {
                setBit(a, idx, i, rev(value));
                return ;
            }
            setBit(a, idx, i, value);
        }
        
        for(int i = idx + 1;; ++i) {
            if (value == 0) {
                if (a[i] != ONES) {
                    idx = i;
                    break;
                }
                a[i] = 0;
            } else {
                if (a[i] != 0) {
                    idx = i;
                    break;
                }
                a[i] = ONES;
            }
        }

        for (int i = 0; ; ++i) {
            if (getBit(a, idx, i) == value) {
                setBit(a, idx, i, rev(value));
                return ;
            }
            setBit(a, idx, i, value);           
        }
    }
    int rev(int value) {
        return value == 0 ? 1 : 0;
    }

	long[] read(int n) throws IOException {
		long[] ret = new long[n / SIZE + (n % SIZE != 0 ? 1 : 0)];
		String s = ns();
		for (int i = 0; i < s.length(); ++i)
            setBit(ret, i, s.charAt(s.length() - i - 1) - '0');
		return ret;
	}

    int getBit(long []a, int idx, int bit) {
        return (a[idx] & (1l << (long) bit)) != 0 ? 1 : 0;
    }

    void setBit(long []a, int pos, int value) {
        setBit(a, pos / SIZE, pos % SIZE, value);
    }

    void setBit(long []a, int idx, int bit, int value) {
        if (value == 0) 
            a[idx] &= ~(1l << (long) bit);
        else
            a[idx] |= (1l << (long) bit);
    }

	long[] add(long[] a, long[] b, int n) {
		int mx = max(a.length, b.length);
		int mn = min(a.length, b.length);
		long[] ret = new long[mx + 1];
		int carry = 0;
		for (int i = 0; i < n; ++i) {
            int idx = i / SIZE;
            int bit = i % SIZE;
			carry += getBit(a, idx, bit) + getBit(b, idx, bit);
            setBit(ret, i, carry % 2);
			carry /= 2;
		}
		if (carry != 0)
            setBit(ret, n, carry);
		return ret;
	}

	boolean DEBUG = true;

	void dbg(Object... args) {
		if (!DEBUG)
			return;
		for (Object o : args)
			System.err.print(o + " ");
		System.err.println();
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		 in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

	Random rnd = new Random(1234560L);

	void generate() throws IOException {
		PrintWriter out = new PrintWriter("test.txt");
		int n = 29;
		int q = 1000;
		out.println(n + " " + q);
		for (int i = 0; i < n; ++i) {
			out.print(rnd.nextBoolean() ? 1 : 0);
		}
		out.println();
		for (int i = 0; i < n; ++i) {
			out.print(rnd.nextBoolean() ? 1 : 0);
		}
		out.println();
		for (int i = 0; i < q; ++i) {
			out.print(rnd.nextBoolean() ? "set_a " : "set_b ");
			out.print(rnd.nextInt(n) + " " + (rnd.nextBoolean() ? 1 : 0));
			out.println();
		}
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
