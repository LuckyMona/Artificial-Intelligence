package CS480_Java.pattern_recognition.step6_github_4;
import java.util.HashMap;
import java.util.Map;

public class OtsuThresholdFilter {

    private static double varianceBetween(int threshold, HashMap<Integer, Integer> counts) {
        int countLower = 0;
        int countHigher = 0;
        int sumLower = 0;
        int sumHigher = 0;
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            if (e.getKey() < threshold) {
                countLower += e.getValue();
                sumLower += e.getKey() * e.getValue();
            } else {
                countHigher += e.getValue();
                sumHigher += e.getKey() * e.getValue();
            }
        }

        if (countHigher == 0 || countLower == 0) {
            return 0.0;
        }

        double uDiff = sumLower * 1.0 / countLower - sumHigher * 1.0 / countHigher;
        return (countLower * countHigher * 1.0 / ((countLower + countHigher)^2) * 1.0) * (uDiff * uDiff) ;
    }

    public static int[][] filter(int[][] matrix) {
        int[][] result = matrix.clone();

        HashMap<Integer, Integer> counts = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                counts.put(matrix[i][j], counts.getOrDefault(matrix[i][j], 0) + 1);
            }
        }

        int threshold = -1;
        double max = -1.0;
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            double vb = varianceBetween(e.getKey(), counts);
            // System.out.println("Threshold: " + e.getKey());
            // System.out.println("VB: " + vb);
            if (vb > max || max == -1.0) {
                max = vb;
                threshold = e.getKey();
            }
        }
        // System.out.println("Target Threshold: " + threshold);
        // System.out.println("Max vb: " + max);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < threshold) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = 1;
                }
            }
        }

        return result;
    }

}