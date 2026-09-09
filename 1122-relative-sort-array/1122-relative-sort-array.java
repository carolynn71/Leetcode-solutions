class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        // Frequency of every value in arr1
        int[] count = new int[1001];

        for (int num : arr1) {
            count[num]++;
        }

        int[] answer = new int[arr1.length];
        int index = 0;

        // 1. Put elements according to arr2 order
        for (int num : arr2) {

            while (count[num] > 0) {
                answer[index++] = num;
                count[num]--;
            }
        }

        // 2. Put remaining elements in ascending order
        for (int num = 0; num <= 1000; num++) {

            while (count[num] > 0) {
                answer[index++] = num;
                count[num]--;
            }
        }

        return answer;
    }
}