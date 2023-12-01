package CS480_Java.pattern_recognition.step6_github_4;

// import CS480_Java.pattern_recognition.step6_github_4.RecogImage;

public class TestImage {
    public static void main(String[] args) {
        String filepath = "./CS480_Java/pattern_recognition/step6_github_4/a2.bmp";
        RecogImage reco =  new RecogImage(filepath);
        
        int[][] pixels =  reco.readBmp();
        
        ProcessImage process = new ProcessImage(pixels);
        int[][] labeledImage = process.connected_component();

        
        for (int i = 0; i < labeledImage.length; i++) {
            for (int j = 0; j < labeledImage[i].length; j++) {
                System.out.print(String.format("%d ", labeledImage[i][j]));
            }
            System.out.println();
        }

        double R1 = ObjectRecognition.computeRoundness(labeledImage, 1);
        double R2 = ObjectRecognition.computeRoundness(labeledImage, 2);

        System.out.println("Object 1 is " + ObjectRecognition.classifyObject(R1));
        System.out.println("Object 2 is " + ObjectRecognition.classifyObject(R2));
    }
}
