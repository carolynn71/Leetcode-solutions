class Solution {

    static class Node {
        Node[] child = new Node[26];
        int index = -1;
    }

    public int[] stringIndices(
            String[] wordsContainer,
            String[] wordsQuery) {

        Node root = new Node();

        // Build Trie using reversed words
        for (int i = 0; i < wordsContainer.length; i++) {

            String word = wordsContainer[i];

            Node cur = root;

            // Root represents empty suffix.
            // Choose the best word for empty suffix.
            if (cur.index == -1 ||
                word.length() < wordsContainer[cur.index].length()) {
                cur.index = i;
            }

            for (int j = word.length() - 1; j >= 0; j--) {

                int c = word.charAt(j) - 'a';

                if (cur.child[c] == null) {
                    cur.child[c] = new Node();
                }

                cur = cur.child[c];

                // Best word for this suffix
                if (cur.index == -1 ||
                    word.length() <
                    wordsContainer[cur.index].length()) {

                    cur.index = i;
                }
            }
        }

        int[] answer = new int[wordsQuery.length];

        // Query the Trie using reversed query
        for (int i = 0; i < wordsQuery.length; i++) {

            String query = wordsQuery[i];

            Node cur = root;

            int best = root.index;

            for (int j = query.length() - 1; j >= 0; j--) {

                int c = query.charAt(j) - 'a';

                if (cur.child[c] == null) {
                    break;
                }

                cur = cur.child[c];

                best = cur.index;
            }

            answer[i] = best;
        }

        return answer;
    }
}