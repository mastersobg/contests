import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	PrintWriter out;
    Reader console;
    Reader file;

    static class Node {
        Node []next = new Node[2];
        int cnt;
    }

	void solve() throws IOException {  
        Node root = buildTree();
        int t = console.ni();
        for(int it = 0; it < t; ++it) {
            int []arr = read(console);
            int ret = process(root, arr);
            out.println(ret);
        }
	}

    int process(Node root, int []arr) {
        Node ROOT = root;
        if (arr.length == 1) {
            out.println("Fuck");
            return new Random().nextBoolean() ? 1 : 0;
        }
        int ret = 0;
        for (int i = 1; i < arr.length; ++i) {
            int a = arr[i] >= arr[i - 1] ? 1 : 0;
            if (root.next[a] != null) {
                root = root.next[a];
            } else {
                return process(ROOT, trim(arr));
            }
        }
        int l = root.next[0] != null ? root.next[0].cnt : 0;
        int r = root.next[1] != null ? root.next[1].cnt : 0;
        return r >= l ? 1 : 0;
    }

    int [] trim(int []a) {
        int []b = new int[a.length - 1];
        for(int i = 1; i < a.length; ++i) 
            b[i - 1] = a[i];
        return b;
    }

    int []read(Reader r) throws IOException {
        int n = r.ni();
        int []arr = new int[n];
        for(int i = 0; i < n; ++i) {
            arr[i] = r.ni();
        }
        return arr;
    }
    Node buildTree() throws IOException {
        Node root = new Node();
        int t = file.ni();
        for(int i = 0; i < t; ++i) {
            int []arr = read(file);
            build(root, arr);
        }
        return root;
    }

    void build(Node root, int []arr) {
        root.cnt++;
        for(int i = 1; i < arr.length; ++i) {
            int a = arr[i] >= arr[i - 1] ? 1 : 0;
            if (root.next[a] == null) {
                Node next = new Node();
                root.next[a] = next;
            }
            root = root.next[a];
            root.cnt++;
        }
    }

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
        console = new Reader(new InputStreamReader(System.in));
        file = new Reader(new FileReader("topcoderdata.txt"));
		out = new PrintWriter(System.out);
		solve();
		file.close();
        console.close();
		out.close();
	}  

    class Reader {
        BufferedReader in;
        StringTokenizer st;

        Reader(java.io.Reader reader) {
            this.in = new BufferedReader(reader);
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
        void close() throws IOException {
            in.close();
        }
    }

	public static void main(String[] args) throws IOException {
		new Solution().run();
	}
}
