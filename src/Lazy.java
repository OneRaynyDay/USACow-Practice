import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Lazy {
   public static PrintWriter write;
   public static Scanner scan;
   public static int N, K;
   public static Patch[] patch;
   public static void main(String[] args){
      loadFile();
      
      Arrays.sort(patch);
      int sum = testForMax(patch[0].coord + K, 0, 0);
      int maxSum = sum;
      //test between ranges - you don't want the start to be anywhere less than start + 3 or more than end - 3
      for(int i = 1; i < N; i++){
         sum = testForMax(patch[i].coord + K, i, sum);
         if(sum > maxSum)
            maxSum = sum;
      }
      System.out.println(maxSum);
      try {
         write = new PrintWriter("lazy.out");
         write.println(maxSum);
         write.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }
   public static int testForMax(int coord, int n, int sum){
      
      int start = 0;
      for(int i = n; i >= 0; i--){
         if(Math.abs(coord - patch[i].coord) <= K){
            start = i;
            break;
         }
      }
      for(int i = start-1; i >= 0; i--){
         if(start-1 >= 0){
            if(patch[i].checked){
               break;
            }
            //already has sum
            sum -= patch[i].grass;
            patch[i].checked = false;
         }
      }
      
      for(int i = n; i < N; i++){
         if(patch[i].coord - coord > K)
            break;
         else if(!patch[i].checked){
            sum += patch[i].grass;
            patch[i].checked = true;
         }
      }
      return sum;
   }
   public static void loadFile(){
      try {
         scan = new Scanner(new File("lazy.in"));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      N = scan.nextInt();
      K = scan.nextInt();
      patch = new Patch[N];
      for(int i = 0; i < N; i++){
         int grass = scan.nextInt();
         int coord = scan.nextInt();
         patch[i] = new Patch(grass, coord);
      }
   }
}
class Patch implements Comparable{
   public int coord;
   public int grass;
   public boolean checked;
   public Patch(int grass, int coord){
      this.coord = coord;
      this.grass = grass;
      checked = false;
   }
   public String toString(){
      return coord + " " + grass;
   }
   public int compareTo(Object arg0) {
      return coord - ((Patch)arg0).coord;
   }
}