import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	String []days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

	int firstSeptember;
	int contest;
	int a, b;

	void solve() throws IOException {
		firstSeptember = getDayIdx(ns());
		contest = getContest(ns(), ni());
		dbg("con", contest);
		dbg("first", firstSeptember);
		a = ni();
		b = ni();
		int []days = new int[7];
 		for (int cnt = 1; cnt <= 7; ++cnt) {
			if (rec(cnt, 0, days)) {
				out.println(cnt);
				for (int i = 0; i < cnt; ++i) {
					out.println(this.days[days[i]]);
				}
				return ;
			}
		}
		out.println("Impossible");
	}

	boolean rec(int cnt, int idx, int []days) {
		if (cnt == idx) {
			int ret = count(days, cnt);
			return a <= ret && ret <= b;
		}

		int last = idx == 0 ? 0 : days[idx - 1] + 1;
		for (; last < 7; ++last) {
			days[idx] = last;
			if (rec(cnt, idx + 1, days)) {
				return true;
			}
		}
		return false;
	}

	boolean []arr = new boolean[7];

	int count(int []days, int cnt) {
		Arrays.fill(arr, false);
		for (int i = 0; i < cnt; ++i) {
			arr[days[i]] = true;
		}
		int currentDay = (firstSeptember + 1) % 7;
		int ret = 0;
		for (int i = 1; i < contest; ++i) {
			if (arr[currentDay]) {
				++ret;
			}
			currentDay++;
			if (currentDay == 7) {
				currentDay = 0;
			}
		}
		return ret;
	}

	int getDayIdx(String name) {
		for (int i = 0; i < days.length; ++i) {
			if (days[i].equals(name)) {
				return i;
			}
		}
		throw new RuntimeException();
	}

	int getContest(String month, int day) {
		month = month.toLowerCase();
		int []cnt = {30, 31, 30, 31};
		String []names = {"september", "october", "november", "december"};
		int ret = 0;
		int mIdx = 0;
		int dayN = 1;
		while (!(names[mIdx].equals(month) && day == dayN)) {
			++dayN;
			if (dayN > cnt[mIdx]) {
				dayN = 1;
				mIdx++;
			}
			++ret;
		}
		return ret;
	}

	public Solution() throws IOException {
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
		if (args.length > 0 && args[0].equals("LOCAL_DEBUG")) {
			DEBUG = true;
		}
		new Solution();
	}

	class Timer {

        long time;

        void start() {
            time = System.currentTimeMillis();
        }
        long time() {
            return System.currentTimeMillis() - time;
        }
        void print() {
            print("Time spent = ");
        } 

        void print(String message) {
            dbg(message, time());
        }

    }

    static boolean DEBUG = false;

    void dbg(Object ... objs) {
        if (!DEBUG) {
            return ;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[]) 
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }
}
