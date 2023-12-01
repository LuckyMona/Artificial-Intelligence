package CS480_Java.pattern_recognition.step6_github_4;

import java.util.HashMap;
import java.util.Map;

public class ConnectivityAnalysis {

    private int[][] labels;
    private int nextLabel = 1; // Start labeling from 1
    private Map<Integer, Integer> labelEquivalences = new HashMap<>();

    public ConnectivityAnalysis(int[][] binaryImage) {
        labels = new int[binaryImage.length][binaryImage[0].length];
    }

    public int[][] computeConnectivity(int[][] binaryImage) {
        // First pass
        for (int i = 0; i < binaryImage.length; i++) {
            for (int j = 0; j < binaryImage[i].length; j++) {
                if (binaryImage[i][j] == 1) {
                    // Find the minimum label from the neighbors
                    int minLabel = findMinLabel(i, j);
                    if (minLabel == Integer.MAX_VALUE) {
                        // Assign a new label
                        labels[i][j] = nextLabel;
                        labelEquivalences.put(nextLabel, nextLabel);
                        nextLabel++;
                    } else {
                        // Assign the minimum label found and update the equivalences
                        labels[i][j] = minLabel;
                        updateEquivalences(i, j, minLabel);
                    }
                }
            }
        }

        // Second pass
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                if (labels[i][j] > 0) {
                    labels[i][j] = labelEquivalences.get(labels[i][j]);
                }
            }
        }

        return labels;
    }

    private int findMinLabel(int i, int j) {
        int minLabel = Integer.MAX_VALUE;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0) continue; // Skip the pixel itself
                int ni = i + di, nj = j + dj;
                if (ni >= 0 && ni < labels.length && nj >= 0 && nj < labels[0].length) {
                    if (labels[ni][nj] > 0) {
                        minLabel = Math.min(minLabel, labels[ni][nj]);
                    }
                }
            }
        }
        return minLabel;
    }

    private void updateEquivalences(int i, int j, int newLabel) {
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0) continue; // Skip the pixel itself
                int ni = i + di, nj = j + dj;
                if (ni >= 0 && ni < labels.length && nj >= 0 && nj < labels[0].length) {
                    if (labels[ni][nj] > 0) {
                        int currentLabel = labels[ni][nj];
                        while (labelEquivalences.get(currentLabel) != currentLabel) {
                            currentLabel = labelEquivalences.get(currentLabel);
                        }
                        int minLabel = Math.min(currentLabel, newLabel);
                        labelEquivalences.put(currentLabel, minLabel);
                        labelEquivalences.put(newLabel, minLabel);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] binaryImage = {
            // Binary image matrix here
        };

        ConnectivityAnalysis ca = new ConnectivityAnalysis(binaryImage);
        int[][] labeledImage = ca.computeConnectivity(binaryImage);

        // Output the labeled image
        for (int i = 0; i < labeledImage.length; i++) {
            for (int j = 0; j < labeledImage[i].length; j++) {
                System.out.print(labeledImage[i][j] + " ");
            }
            System.out.println();
        }
    }
}
