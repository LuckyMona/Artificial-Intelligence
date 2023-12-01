// 这个文件读取bmp图片，然后转换为灰度图，然后输出灰度图的像素值

// remove this line if use javac in command line
package CS480_Java.pattern_recognition.step6_github_4;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//
public class RecogImage0 {
    public static void main(String args[]) throws IOException {
        BufferedImage img = null;
        File f = null;
        // read image
        try {
            f = new File("./CS480_Java/pattern_recognition/step6_github_4/a2.bmp");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        // get width and height
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] arr = new int[width][height]; // convert to red image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Here (x,y) denotes the coordinate of image 
                // for modifying the pixel value.
                int p = img.getRGB(x, y);
                // System.err.println("p: " + p);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                // calculate average
                int avg = (r + g + b) / 3;
                arr[x][y] = avg;
                // replace RGB value with avg
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                img.setRGB(x, y, p);
            }
        }
        System.out.println("height" + height);
        System.out.println("width" + width);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // System.out.print(String.format("%1$3s", arr[i][j]));
                System.out.print(String.format("%d ", arr[i][j]));
            }
            System.out.println();
        }
        // try {
        //     f = new File("./out3.bmp");
        //     ImageIO.write(img, "jpg", f);
        // } catch (IOException e) {
        //     System.out.println(e);
        // }
    }
}