import java.util.Scanner;
import java.io.File;

public class ImageEditor {
  ImageEditor(){

  }

  public static void main(String[] args){
    try {
      File file = new File(args[0]);
      Scanner sc = new Scanner(file);
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
      img.toString();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
