import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void quickSort(int[] ar) {
		qs(ar, 0, ar.length - 1);
	}

	void qs(int[] ar, int l, int r) {
		if (r - l <= 0) {
			return;
		}
		int pos = partition(ar, l, r);
		qs(ar, l, pos - 1);
		qs(ar, pos + 1, r);
		printArray(ar, l, r);

	}

	int partition(int[] ar, int l, int r) {
		int n = r - l + 1;
		int el = ar[l];
		int[] ret = new int[n];
		int idx = 0;
		for (int i = l + 1; i <= r; ++i) {
			if (ar[i] < el)
				ret[idx++] = ar[i];
		}
		int pos = l + idx;
		ret[idx++] = el;
		for (int i = l + 1; i <= r; ++i) {
			if (ar[i] >= el)
				ret[idx++] = ar[i];
		}
		for (int i = 0; i < idx; ++i)
			ar[l++] = ret[i];
		return pos;
	}

	/* Tail starts here */

	static void printArray(int[] ar, int l, int r) {
		for (int i = l; i <= r; ++i) {
			int n = ar[i];
			System.out.print(n + " ");
		}
		System.out.println("");
	}

	void solve() throws IOException {
		int n = ni();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i)
			arr[i] = ni();
		quickSort(arr);
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
