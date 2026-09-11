class Solution {

    public String fractionAddition(String expression) {

        int numerator = 0;
        int denominator = 1;

        int i = 0;

        while (i < expression.length()) {

            int sign = 1;

            if (expression.charAt(i) == '+') {
                i++;
            } else if (expression.charAt(i) == '-') {
                sign = -1;
                i++;
            }

            int num = 0;

            while (i < expression.length()
                    && Character.isDigit(expression.charAt(i))) {

                num = num * 10 + (expression.charAt(i) - '0');
                i++;
            }

            i++;

            int den = 0;

            while (i < expression.length()
                    && Character.isDigit(expression.charAt(i))) {

                den = den * 10 + (expression.charAt(i) - '0');
                i++;
            }

            num *= sign;

            numerator =
                    numerator * den + num * denominator;

            denominator *= den;

            int g = gcd(Math.abs(numerator), denominator);

            numerator /= g;
            denominator /= g;
        }

        return numerator + "/" + denominator;
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}