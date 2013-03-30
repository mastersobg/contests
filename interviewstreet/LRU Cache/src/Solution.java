import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	private static class ListItem implements Comparable<ListItem> {
		String key;
		String value;
		ListItem previous;
		ListItem next;

		public ListItem(String key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(ListItem o) {
			return key.compareTo(o.key);
		}
	}

	private static class List {
		ListItem first;
		ListItem last;
		int size;

		ListItem removeLast() {
			if (last == null) {
				throw new NoSuchElementException();
			}
			ListItem result = last;
			ListItem prev = last.previous;
			last = prev;
			if (prev == null) {
				first = null;
			} else {
				prev.next = null;
			}
			result.previous = null;
			--size;
			return result;
		}

		void remove(ListItem item) {
			if (item == null) {
				throw new NoSuchElementException();
			}
			ListItem prev = item.previous;
			ListItem next = item.next;
			if (prev == null) {
				first = next;
			} else {
				prev.next = next;
				item.previous = null;
			}
			if (next == null) {
				last = prev;
			} else {
				next.previous = prev;
				item.next = null;
			}
			--size;
		}

		void addFirst(ListItem item) {
			if (first == null) {
				item.previous = item.next = null;
				first = last = item;
			} else {
				item.previous = null;
				item.next = first;
				first.previous = item;
				first = item;
			}
			++size;
		}

		public int getSize() {
			return size;
		}
	}

	private static class LRUCache {

		private static final String NOT_FOUND = "NULL";

		List list = new List();
		Map<String, ListItem> map = new HashMap<String, ListItem>();
		int maxSize;

		public void bound(int size) {
			this.maxSize = size;
			ensureSize();
		}

		public void set(String key, String value) {
			if (map.containsKey(key)) {
				ListItem item = map.get(key);
				item.value = value;
				list.remove(item);
				list.addFirst(item);
			} else {
				ListItem item = new ListItem(key, value);
				map.put(key, item);
				list.addFirst(item);
			}
			ensureSize();
		}

		public String get(String key) {
			return get(key, true);
		}

		public String peek(String key) {
			return get(key, false);
		}

		public void dump(PrintWriter out) {
			ListItem[] items = new ListItem[list.getSize()];
			int index = 0;
			for (ListItem item = list.first; item != null; item = item.next) {
				items[index++] = item;
			}
			Arrays.sort(items);
			for (ListItem item : items) {
				out.print(item.key);
				out.print(" ");
				out.println(item.value);
			}
		}

		private String get(String key, boolean recordAccess) {
			if (map.containsKey(key)) {
				ListItem item = map.get(key);
				if (recordAccess) {
					list.remove(item);
					list.addFirst(item);
				}
				return item.value;
			} else {
				return NOT_FOUND;
			}
		}

		private void ensureSize() {
			while (list.getSize() > maxSize) {
				String key = list.removeLast().key;
				map.remove(key);
			}
		}
	}

	void solve() throws IOException {
		int ops = ni();
		LRUCache cache = new LRUCache();
		for (int i = 0; i < ops; ++i) {
			String command = ns();
			if ("BOUND".equals(command)) {
				cache.bound(ni());
			} else if ("SET".equals(command)) {
				cache.set(ns(), ns());
			} else if ("GET".equals(command)) {
				out.println(cache.get(ns()));
			} else if ("PEEK".equals(command)) {
				out.println(cache.peek(ns()));
			} else if ("DUMP".equals(command)) {
				cache.dump(out);
			}
		}
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
