package CS480_Java.pattern_recognition.step6_github_4;

public class ObjectRecognition {

    // Constants for the area and perimeter calculation
    private static final double PI = Math.PI;
    // private int[][] labeledImage;

    // public ObjectRecognition(int[][] labeledImage1) {
    //     labeledImage = labeledImage1;
    // }

    public static double computeRoundness(int[][] labeledImage, int label) {
        int area = 0;
        int perimeter = 0;

        for (int i = 0; i < labeledImage.length; i++) {
            for (int j = 0; j < labeledImage[0].length; j++) {
                if (labeledImage[i][j] == label) {
                    area++;
                    // Check if the pixel is on the border of the object for perimeter
                    if (isBorderPixel(labeledImage, i, j, label)) {
                        perimeter++;
                    }
                }
            }
        }

        // Compute roundness ratio R
        double R = (4 * PI * area) / (Math.pow(perimeter, 2));
        return R;
    }

    private static boolean isBorderPixel(int[][] labeledImage, int i, int j, int label) {
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0) continue; // Skip the pixel itself
                int ni = i + di, nj = j + dj;
                if (ni >= 0 && ni < labeledImage.length && nj >= 0 && nj < labeledImage[0].length) {
                    if (labeledImage[ni][nj] != label) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static String classifyObject(double R) {
        if (R >= 0.85) {
            return "circle";
        } else if (R >= 0.75) {
            return "square";
        } else {
            return "triangle";
        }
    }

    // public static void main(String[] args) {
    //     int[][] labeledImage = {
    //         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    //         {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
    //         // ... the rest of the labeled image
    //     };

    //     // Assuming there are only two objects and labels used are 1 and 2
    //     double R1 = computeRoundness(labeledImage, 1);
    //     double R2 = computeRoundness(labeledImage, 2);

    //     System.out.println("Object 1 is " + classifyObject(R1));
    //     System.out.println("Object 2 is " + classifyObject(R2));
    // }

}
