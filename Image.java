import java.io.*;
import java.util.Scanner;

public class Image {
  Image(int width, int height, int maxColorVal){
    Pixel[][] p = new Pixel[height][width];
    pixels = p;
    wd = width;
    ht = height;
    maxVal = maxColorVal;
  }

  public static void main(String[] args){
    Pixel pix = new Pixel(1,255,3,255);
    System.out.println(pix.getRed() + "," + pix.getGreen() + "," + pix.getBlue());
  }

  public void makePixels(Scanner scanner){
    String next;
    int count = 0;
    int r = 0;
    int g = 0;
    int b = 0;
    while (scanner.hasNext()){
      next = scanner.next();
      if (next.contains("#")){
        scanner.nextLine();
        continue;
      }
      if (count%3 == 0) r = Integer.parseInt(next);
      if (count%3 == 1) g = Integer.parseInt(next);
      if (count%3 == 2){
        b = Integer.parseInt(next);
        Pixel px = new Pixel(r,g,b,maxVal);
        pixels[iter[0]][iter[1]] = px;
        if (iter[1] == (wd-1)){
          iter[0]++;
          iter[1] = 0;
        } else {
          iter[1]++;
        }
      }
      count++;
    }
  }

  public String toString(){
    System.out.println("Width: " + pixels[0].length);
    System.out.println("Height: " + pixels.length);
    System.out.println("Max Color Value: " + maxVal);
    for (int i = 0; i < pixels.length; i++){
      for (int j = 0; j < pixels[i].length; j++){
        if (pixels[i][j] != null){
          pixels[i][j].toString();
        }
      }
      System.out.println();
    }
    return "";
  }

  private Pixel[][] pixels;
  private int maxVal;
  private int[] iter = {0,0};
  private int wd;
  private int ht;
}
