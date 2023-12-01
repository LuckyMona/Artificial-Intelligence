package CS480_Java.pattern_recognition.step6_github_4;
class ProcessImage {
    private int[][] image;

    public ProcessImage(int[][] inputImage) {
        image = inputImage;
    }

    // step 1 gaussian filter
    public void gaussian_filter() {
        int rows = image.length;
        int cols = image[0].length;
        int[][] result = new int[rows][cols];

        double[][] kernel = {
            {1.0/16, 1.0/8, 1.0/16},
            {1.0/8, 1.0/4, 1.0/8},
            {1.0/16, 1.0/8, 1.0/16}
        };

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                double sum = 0.0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        sum += kernel[x + 1][y + 1] * image[i + x][j + y];
                    }
                }
                result[i][j] = (int) Math.round(sum);
            }
        }

        // Update the original image with the filtered values
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                image[i][j] = result[i][j];
            }
        }
    }


    // step 2 histogram
    public int[] histogram() {
        int[] hist = new int[256];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                hist[image[i][j]]++;
            }
        }
        return hist;
    }

    // step 3 threshold
    public int[][] threshold() {
        int[][] threshold = OtsuThresholdFilter.filter(image);
        return threshold;
    }

    // step 4 connected component
    public int[][] connected_component() {
        int[][] threshold = threshold();
        ConnectivityAnalysis ca = new ConnectivityAnalysis(threshold);
        int[][] labeledImage = ca.computeConnectivity(threshold);
        return labeledImage;
    }


    public void displayImage() {
        int rows = image.length;
        int cols = image[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// public class TestImage {
//     public static void main(String[] args) {
        
//         int[][] inputImage = {
//             {1, 3, 5, 7, 9, 3, 4, 4, 5, 6},
//             {1, 3, 35, 7, 9, 3, 4, 4, 5, 6},
//             {1, 3, 5, 7, 9, 3, 4, 4, 5, 6},
//             {1, 3, 5, 20, 25, 24, 33, 5, 6, 4},
//             {1, 3, 5, 22, 35, 24, 32, 5, 6, 4},
//             {1, 3, 5, 20, 28, 34, 23, 5, 6, 4},
//             {1, 3, 5, 21, 25, 27, 23, 5, 6, 4},
//             {1, 3, 27, 7, 9, 3, 4, 4, 5, 6},
//             {1, 3, 5, 7, 9, 3, 4, 4, 5, 6},
//             {1, 3, 5, 7, 9, 3, 4, 4, 25, 6}
//         };

//         Image image = new Image(inputImage);

//         System.out.println("Input image (with noise):");
//         image.displayImage();

//         image.gaussian_filter();

//         System.out.println("\nOutput image (cleaned image):");
//         image.displayImage();
//     }
// }
