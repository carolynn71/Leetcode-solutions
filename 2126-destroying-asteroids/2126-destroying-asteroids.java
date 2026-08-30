import java.util.*;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {

        // Destroy smaller asteroids first
        Arrays.sort(asteroids);

        long currentMass = mass;

        for (int asteroid : asteroids) {

            if (currentMass < asteroid) {
                return false;
            }

            currentMass += asteroid;
        }

        return true;
    }
}