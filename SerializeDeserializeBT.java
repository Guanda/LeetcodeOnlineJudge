import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 Serialization is the process of converting a data structure or object into a sequence of bits
 so that it can be stored in a file or memory buffer, or transmitted across a network connection
 link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how
 your serialization/deserialization algorithm should work. You just need to ensure that a binary
 tree can be serialized to a string and this string can be deserialized to the original tree
 structure.

 For example, you may serialize the following tree

     1
    / \
   2   3
  / \
 4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not
 necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 Analysis:
     The idea is simple: print the tree in pre-order traversal and use "X" to denote null node and split node with ",".
     We can use a StringBuilder for building the string on the fly. For deserializing, we use a Queue to store the pre-order
     traversal and since we have "X" as null node, we know exactly how to where to end building subtrees.
 */

public class SerializeDeserializeBT {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append(NN).append(spliter);
        }
        else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<String>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.remove();
        if(val.equals(NN))
            return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}

// Treat SerializeDeserializeBT as Codec
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));