import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    class Blog {
        String name;
        Set<String> friends = new TreeSet<String> ();
        Set<String> inFriends = new TreeSet<String> ();
        Set<String> both = new TreeSet<String> ();

        Blog(String name) {
            this.name = name;
        }

        public String toString() {
            return name + " " + friends.toString();
        }
    }

    void solve() throws IOException {
        int n = ni();
        Blog []blogs = new Blog[n];
        for (int i = 0; i < n; ++i) {
            blogs[i] = read();
        }
        Map<String, Blog> map = new HashMap<String, Blog> ();
        for (Blog b : blogs) {
            map.put(b.name, b);
        }
        for (Blog b : blogs) {
            for (String f : b.friends) {
                if (!map.containsKey(f)) continue;
                map.get(f).inFriends.add(b.name);
                if (map.get(f).friends.contains(b.name))
                    b.both.add(map.get(f).name);
            }
        }

        boolean first = true;
        for (Blog b : blogs) {
            if (!first) out.println();
//            Collections.sort(b.friends);
//            Collections.sort(b.inFriends);
//            Collections.sort(b.both);
            out.println(b.name);
            print(1, new ArrayList<String>(b.friends));
            print(2, new ArrayList<String>(b.inFriends));
            print(3, new ArrayList<String>(b.both));
            first = false;
        }
    }

    void print(int index, List<String> list) {
        out.print(index + ": ");
        for (int i = 0; i < list.size(); ++i) {
            if (i > 0) out.print(", ");
            out.print(list.get(i));
        }
        out.println();
    }

    Blog read() throws IOException {
        String name = ns();
        String text = "";
        String s;
        while (true) {
            s = in.readLine();
            text += s;
            if (s.indexOf("</blog>") >= 0) {
                break;
            }
        }
        Blog ret = new Blog(name);
        int start = 0;
        while (true){ 
            int p = text.indexOf("</friend>", start);
            if (p < 0) break;
            int p1 = text.indexOf("<friend>", start);
            String friend = text.substring(p1 + 8, p); 
            if (!name.equals(friend))
                ret.friends.add(friend);
            start = p + 1;
        }
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

    static boolean DEBUG = true;

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

