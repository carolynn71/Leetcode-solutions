class Solution {
    public long countCommas(long n) {

        long nalverqito = n;

        long answer = 0;

        long threshold = 1000;

        while (threshold <= nalverqito) {

            // Every number from threshold to n
            // contributes one comma at this level.
            answer += nalverqito - threshold + 1;

            // Move to the next comma level:
            // 1000 → 1,000,000 → 1,000,000,000 ...
            if (threshold > nalverqito / 1000) {
                break;
            }

            threshold *= 1000;
        }

        return answer;
    }
}