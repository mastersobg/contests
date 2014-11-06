public class Solution {
    public String addBinary(String A, String B) {
        int maxLength = Math.max(A.length(), B.length());
        byte []a = getBinary(A, maxLength);
        byte []b = getBinary(B, maxLength);
        byte []ret = new byte[maxLength + 1];
        byte carry = 0;
        for (int i = 0; i < maxLength; ++i) {
            byte value = (byte) (a[i] + b[i] + carry);
            ret[i] = (byte) (value % 2);
            carry = (byte) (value >> 1);
        }
        if (carry > 0)
            ret[maxLength] = carry;
        StringBuilder sb = new StringBuilder();
        int i = maxLength + 1;
        while (i > 0 && ret[--i] == 0) ;
        if (i < 0)
            return "0";
        while (i >= 0)
            sb.append(ret[i--]);
        return sb.toString();
    }
    
    private byte[] getBinary(String s, int maxLength) {
        byte[] ret = new byte[maxLength];
        for (int i = s.length() - 1, j = 0; i >= 0; --i, ++j) {
            ret[j] = (byte) (s.charAt(i) - '0');
        }
        return ret;
    }
}
