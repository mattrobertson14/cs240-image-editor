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

  public Pixel[][] getPixels(){
    return pixels;
  }

  public void invert(){
    for (int i = 0; i < pixels.length; i++){
      for (int j = 0; j < pixels[i].length; j++){
        if (pixels[i][j] != null){
          Pixel px = pixels[i][j];
          int r = maxVal - px.getRed();
          px.setRed(r);
          int g = maxVal - px.getGreen();
          px.setGreen(g);
          int b = maxVal - px.getBlue();
          px.setBlue(b);
        }
      }
    }
  }

  public void grayscale(){
    for (int i = 0; i < pixels.length; i++){
      for (int j = 0; j < pixels[i].length; j++){
        if (pixels[i][j] != null){
          Pixel px = pixels[i][j];
          int avg = px.getRed() + px.getGreen() + px.getBlue();
          avg = avg/3;
          px.setRed(avg);
          px.setGreen(avg);
          px.setBlue(avg);
        }
      }
    }
  }

  public void emboss(){
    for (int i = pixels.length-1; i > -1; i--){
      for (int j = pixels[0].length-1; j > -1 ; j--){
        if (pixels[i][j] != null){
          Pixel px1 = pixels[i][j];
          if (i == 0 || j == 0){
            px1.setRed(128);
            px1.setGreen(128);
            px1.setBlue(128);
          } else {
            Pixel px2 = pixels[i-1][j-1];
            int redDiff = getRedDiff(px1,px2);
            int greenDiff = getGreenDiff(px1,px2);
            int blueDiff = getBlueDiff(px1,px2);
            int res = 128 + getMaxDiff(redDiff, greenDiff, blueDiff);
            res = (res < 0)? 0 : res;
            res = (res > 255)? 255 : res;
            px1.setRed(res);
            px1.setGreen(res);
            px1.setBlue(res);
          }
        }
      }
    }
  }

  public int getRedDiff(Pixel p1, Pixel p2){
    return p1.getRed() - p2.getRed();
  }

  public int getGreenDiff(Pixel p1, Pixel p2){
    return p1.getGreen() - p2.getGreen();
  }

  public int getBlueDiff(Pixel p1, Pixel p2){
    return p1.getBlue() - p2.getBlue();
  }

  public int getMaxDiff(int r, int g, int b){
    if (Math.abs(r) > Math.abs(g) && Math.abs(r) > Math.abs(b)){
      return r;
    } else if (Math.abs(g) > Math.abs(r) && Math.abs(g) > Math.abs(b)){
      return g;
    } else if (Math.abs(b) > Math.abs(r) && Math.abs(b) > Math.abs(g)){
      return b;
    } else if (Math.abs(r) == Math.abs(g) || Math.abs(r) == Math.abs(b)){
      return r;
    } else if (Math.abs(g) == Math.abs(b)){
      return g;
    }
    return 0;
  }

  public void motionblur(int num){
    for (int i = 0; i < pixels.length; i++){
      for (int j = 0; j < pixels[i].length; j++){
        if (pixels[i][j] != null){
          Pixel px = pixels[i][j];
          int r = px.getRed();
          int g = px.getGreen();
          int b = px.getBlue();
          int count = 1;
          while (count < num && count < pixels[i].length-j){
            r += pixels[i][j+count].getRed();
            g += pixels[i][j+count].getGreen();
            b += pixels[i][j+count].getBlue();
            count++;
          }
          px.setRed(r/count);
          px.setGreen(g/count);
          px.setBlue(b/count);
        }
      }
    }
  }

  public String toString(){
    StringBuilder sb = new StringBuilder("P3");
    sb.append(" " + pixels[0].length);
    sb.append(" " + pixels.length);
    sb.append(" " + maxVal);

    for (int i = 0; i < pixels.length; i++){
      for (int j = 0; j < pixels[i].length; j++){
        if (pixels[i][j] != null){
          sb.append(" " + pixels[i][j].getRed());
          sb.append(" " + pixels[i][j].getGreen());
          sb.append(" " + pixels[i][j].getBlue());
        }
      }
      sb.append("\n");
    }

    return sb.toString();
  }

  private Pixel[][] pixels;
  private int maxVal;
  private int[] iter = {0,0};
  private int wd;
  private int ht;
}
