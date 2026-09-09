class Solution {
    public String sortSentence(String s) {

        String[] words = s.split(" ");

        String[] answer = new String[words.length];

        for (String word : words) {

            // Last character is the position
            int position = word.charAt(word.length() - 1) - '1';

            // Remove the last digit
            String actualWord = word.substring(0, word.length() - 1);

            // Put it directly in its correct position
            answer[position] = actualWord;
        }

        return String.join(" ", answer);
    }
}