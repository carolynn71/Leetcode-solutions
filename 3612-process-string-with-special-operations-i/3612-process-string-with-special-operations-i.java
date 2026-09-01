class Solution {
    public String processStr(String s) {

        StringBuilder result = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch >= 'a' && ch <= 'z') {
                // Add character
                result.append(ch);
            }

            else if (ch == '*') {
                // Remove last character
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            }

            else if (ch == '#') {
                // Duplicate the current string
                result.append(result);
            }

            else if (ch == '%') {
                // Reverse the current string
                result.reverse();
            }
        }

        return result.toString();
    }
}