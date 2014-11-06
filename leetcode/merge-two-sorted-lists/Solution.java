/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode ret = null, ans = null;
        while (l1 != null && l2 != null) {
            ListNode cur;
            if (l1.val < l2.val) {
                cur = l1;
                l1 = l1.next;
            } else {
                cur = l2;
                l2 = l2.next;
            }
            if (ret == null) {
                ret = ans = cur;
            }
            ret.next = cur;
            ret = cur;
        }
        if (l1 != null) {
            ret.next = l1;
        }
        if (l2 != null) {
            ret.next = l2;
        }
        return ans;
    }
}