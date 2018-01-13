import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.Desktop;


public class ImageEditor {
  ImageEditor(){

  }

  public static void main(String[] args){
    try {

      JFrame frame1 = new JFrame("Transformation Selection");
      frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      String inputFile = (String)JOptionPane.showInputDialog(
        frame1,
        "Enter Input File Path:",
        "Customized Dialog",
        JOptionPane.PLAIN_MESSAGE,
        null,
        null,
        null
      );

      if (inputFile == null || !(inputFile.length() > 0)){
        System.exit(0);
      }

      frame1.dispose();

      JFrame frame2 = new JFrame("Transformation Selection");
      frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      String outputFile = (String)JOptionPane.showInputDialog(
        frame2,
        "Enter Output File Path:",
        "Customized Dialog",
        JOptionPane.PLAIN_MESSAGE,
        null,
        null,
        null
      );

      if (outputFile == null || !(outputFile.length() > 0)){
        System.exit(0);
      }

      frame2.dispose();

      Object[] possibilities = {
        "Grayscale",
        "Invert",
        "Emboss",
        "Motionblur 10"
      };

      JFrame frame3 = new JFrame("Transformation Selection");
      frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      String s = (String)JOptionPane.showInputDialog(
        frame3,
        "Choose Transformation:",
        "Customized Dialog",
        JOptionPane.PLAIN_MESSAGE,
        null,
        possibilities,
        "Grayscale"
      );

      frame3.dispose();

      if (s == null || !(s.length() > 0)){
        System.exit(0);
      }

      /*if (args.length < 2){
        System.out.println("TOO FEW ARGUMENTS:\nPlease enter an input file AND an output file");
        System.exit(0);
      }*/
      File input = new File(inputFile);
      File output = new File(outputFile);
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

      //if (args.length < 3){
      //  System.out.println("TOO FEW ARGUMENTS:\nPlease enter a transformation type (grayscale, invert, emboss, or motionblur)");
      //  System.exit(0);
      //}

      switch(s){
        case "Grayscale" :
          img.grayscale();
          break;
        case "Invert" :
          img.invert();
          break;
        case "Emboss" :
          img.emboss();
          break;
        case "Motionblur 10" :
          /*if (args.length < 4){
            System.out.println("TOO FEW ARGUMENTS:\nPlease enter a number for MotionBlur to work");
            System.exit(0);
          }*/
          //int x = Integer.parseInt(args[3]);
          img.motionblur(10);
          break;
        default :
          break;
      }

      pw.write(img.toString());

      sc.close();
      pw.close();

      if(!Desktop.isDesktopSupported()){
          System.out.println("Desktop is not supported");
          return;
      }

      Desktop desktop = Desktop.getDesktop();
      if(output.exists()) desktop.open(output);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
