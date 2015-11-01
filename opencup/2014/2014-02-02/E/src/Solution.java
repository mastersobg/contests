import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    static class Element {
        Element next;
        int value;
        int other = -1;
        Element(Element next, int value) {
            this.next = next;
            this.value = value;
        }
    }

    static class Node {
        Element head;
        Node []next = new Node[4];

        Node next(char c) {
            return next[INDEX[c]];
        }

        void setNext(char c, Node node) {
            next[INDEX[c]] = node;
        }

        void addElement(Element e) {
            if (head == null)
                head = e;
            else {
                e.next = head;
                head = e;
            }
        }
    }

    void init() {
        INDEX = new int[256];
        INDEX['A'] = 0;
        INDEX['C'] = 1;
        INDEX['G'] = 2;
        INDEX['T'] = 3;
    }

    void addWord(Node root, String s, Element idx) {
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (root.next(c) == null) {
                Node node = new Node();
                root.setNext(c, node);
            }
            root = root.next(c);
        }
        root.addElement(idx);
    }

    Element dfs(Node root, int depth) {
        for (Node next : root.next) {
            if (next != null) {
                Element e =dfs(next, depth + 1);
                if (e != null) {
                    root.addElement(e);
                }
            }
        }
        Element e1 = root.head;
        while (e1 != null) {
            Element e2 = e1.next;
            if (e2 == null) {
                break;
            }

            result += depth;
            e1.other = e2.value;
            e2.other = e1.value;

            e1 = e2.next;
        }

        return e1 == null ? null : e1;
    }

    static int []INDEX;
    int result = 0;

    void solve() throws IOException {
        init();
        int n = ni();
        Node root = new Node();
        Element []elements = new Element[n];
        for (int i = 0; i < n; ++i) {
            Element e = new Element(null, i);
            elements[i] = e;
            addWord(root, ns(), e);
        }
        dfs(root, 0);
        out.println(result);
        for (Element e : elements) {
            if (e.other != -1 && e.value < e.other) {
                out.println((e.value + 1) + " " + (e.other + 1));
            }
        }
    }

    void test() throws IOException {
        PrintWriter out = new PrintWriter("input.txt");
        Random rnd = new Random();
        out.println("100000");
        for (int i = 0; i < 100000; ++i) {
            out.println("ACGT".charAt(rnd.nextInt(4)));
        }
        out.close();
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        // test();
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

