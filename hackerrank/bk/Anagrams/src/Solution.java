import java.io.*;
import java.util.*;

public class Solution {

    PrintWriter out;
    Reader in;

    void solve() throws IOException {
        String line;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        while ((line = in.nextLine()) != null) {
            String key = getKey(line);
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<String>();
                map.put(key, list);
            }
            list.add(line);
        }
        String []result = new String[map.size()];
        int idx = 0;
        for (List<String> list : map.values()) {
            Collections.sort(list);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); ++i) {
                if (i > 0)
                    sb.append(',');
                sb.append(list.get(i));
            }
            result[idx++] = sb.toString();
        }
        Arrays.sort(result);
        for (String s : result)
            out.println(s);
    }

    String getKey(String line) {
        int letters = 0;
        for (int i = 0; i < line.length(); ++i)
            if (Character.isLetter(line.charAt(i)))
                ++letters;
        char []arr = new char[letters];
        int idx = 0;
        for (int i = 0; i < line.length(); ++i)
            if (Character.isLetter(line.charAt(i)))
                arr[idx++] = line.charAt(i);
        Arrays.sort(arr);
        return new String(arr);
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new Reader();
        out = new PrintWriter(System.out);
        solve();
        in.close();
        out.close();
    }


    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

    private static class Reader {

        private BufferedReader in;
        private StringTokenizer st;

        private Reader() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextString() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(in.readLine());
            return st.nextToken();
        }

        String nextLine() throws IOException {
            return in.readLine();
        }

        int nextInt() throws IOException {
            return Integer.valueOf(nextString());
        }

        long nextLong() throws IOException {
            return Long.valueOf(nextString());
        }

        double nextDouble() throws IOException {
            return Double.valueOf(nextString());
        }

        void close() throws IOException {
            in.close();
        }

    }
}

