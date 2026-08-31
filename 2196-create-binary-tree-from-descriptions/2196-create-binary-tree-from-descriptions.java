import java.util.*;

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {

        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {

            int parentVal = d[0];
            int childVal = d[1];
            int isLeft = d[2];

            // Create nodes if they don't already exist
            TreeNode parent =
                map.computeIfAbsent(parentVal, k -> new TreeNode(k));

            TreeNode child =
                map.computeIfAbsent(childVal, k -> new TreeNode(k));

            // Connect parent and child
            if (isLeft == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }

            // Child cannot be the root
            children.add(childVal);
        }

        // The root is the node that is never a child
        for (int value : map.keySet()) {

            if (!children.contains(value)) {
                return map.get(value);
            }
        }

        return null;
    }
}