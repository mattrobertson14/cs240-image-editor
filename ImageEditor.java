import java.util.Scanner;
import java.io.*;

public class ImageEditor {
  ImageEditor(){

  }

  public static void main(String[] args){
    try {
      if (args.length < 2){
        System.out.println("TOO FEW ARGUMENTS:\nPlease enter an input file AND an output file");
        System.exit(0);
      }
      File input = new File(args[0]);
      File output = new File(args[1]);
      Scanner sc = new Scanner(input);
      PrintWriter pw = new PrintWriter(output);
      String next;
      next = sc.next();
      if (!next.contains("P3")){
        throw new Exception("File not formatted correctly");
      }
      int count = 0;
      int width = 0;
      int height = 0;
      int maxVal = 0;
      while (sc.hasNext() && count < 3){
        next = sc.next();
        if (next.contains("#")){
          sc.nextLine();
          continue;
        }
        if (count == 0) width = Integer.parseInt(next);
        if (count == 1) height = Integer.parseInt(next);
        if (count == 2) maxVal = Integer.parseInt(next);
        count++;
      }
      Image img = new Image(width, height, maxVal);
      img.makePixels(sc);

      if (args.length < 3){
        System.out.println("TOO FEW ARGUMENTS:\nPlease enter a transformation type (grayscale, invert, emboss, or motionblur)");
        System.exit(0);
      }

      switch(args[2]){
        case "grayscale" :
          img.grayscale();
          break;
        case "invert" :
          img.invert();
          break;
        case "emboss" :
          img.emboss();
          break;
        case "motionblur" :
          if (args.length < 4){
            System.out.println("TOO FEW ARGUMENTS:\nPlease enter a number for MotionBlur to work");
            System.exit(0);
          }
          int x = Integer.parseInt(args[3]);
          img.motionblur(x);
          break;
        default :
          break;
      }

      pw.write(img.toString());

      sc.close();
      pw.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
