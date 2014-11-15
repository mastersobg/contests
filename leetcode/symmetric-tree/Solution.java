/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        List<Integer> a = new ArrayList<Integer>(), b = new ArrayList<Integer> ();
        preOrder(root, a, true);
        preOrder(root, b, false);
        return a.equals(b);
    }
    
    void preOrder(TreeNode root, List<Integer> values, boolean order) {
        TreeNode left = order ? root.left : root.right;
        TreeNode right = order ? root.right : root.left;
        if (left != null) 
            preOrder(left, values, order);
        values.add(root.val);
        if (right != null)
            preOrder(right, values, order);
        values.add(-1);
    }
}