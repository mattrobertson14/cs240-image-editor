public class Pixel {
  Pixel(int r, int g, int b, int max){
    red = r;
    green = g;
    blue = b;
    maxVal = max;
  }

  public int getRed(){
    return red;
  }

  public int getGreen(){
    return green;
  }

  public int getBlue(){
    return blue;
  }

  public String toString(){
    System.out.println("("+red+","+green+","+blue+")");
    return "";
  }

  private int red;
  private int green;
  private int blue;
  private int maxVal;
}
