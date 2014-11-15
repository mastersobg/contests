public class Solution {
    public String countAndSay(int n) {
        String first = "1";
        int idx = 1;
        while (idx < n) {
            StringBuilder next = new StringBuilder();
            char prev = first.charAt(0);
            int cnt = 1;
            for (int i = 1; i < first.length(); ++i) {
                if (first.charAt(i) == prev) {
                    ++cnt;
                } else {
                    next.append(cnt).append(prev);
                    prev = first.charAt(i);
                    cnt = 1;
                }
            }
            next.append(cnt).append(prev);
            first = next.toString();
            ++idx;
        }
        return first;
    }
}