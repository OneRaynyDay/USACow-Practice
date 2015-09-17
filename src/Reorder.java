import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Reorder {
   public static PrintWriter write;
   public static Scanner scan;
   public static int[] A, B;
   public static int size;
   public static void main(String[] args){
      loadFile();
      int index = 0;
      int count = 0;
      int longestShift = 0;
      while(check() != -1){
         int shift = 0;//output
         int startIndex = check();//starting point
         int initIndex = startIndex;
         int val = A[startIndex];
         do{
            index = findNewIndex(val);
            if(index != startIndex)//because do while
               shift++;
            int temp = A[index];
            A[index] = val;
            startIndex = index;
            val = temp;
         }while(initIndex != startIndex);
         
         if(shift > longestShift)
            longestShift = shift;
         count++;//output
      }
      try {
         write = new PrintWriter("reorder.out");
         if(longestShift == 0)
            longestShift = -1;
         write.println(count + " " + longestShift);
         write.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }
   public static int findNewIndex(int startVal){
      for(int i = 0; i < size; i++){
         if(B[i] == startVal){
            return i;
         }
      }
      return -1;//flag for not correct
   }
   public static void loadFile(){
      try {
         scan = new Scanner(new File("reorder.in"));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      size = scan.nextInt();
      A = new int[size];
      B = new int[size];
      for(int i = 0; i < size; i++)
         A[i] = scan.nextInt();
      for(int i = 0; i < size; i++)
         B[i] = scan.nextInt();
   }
   public static int check(){
      for(int i = 0; i < size; i++){
         if(A[i] != B[i])
            return i;
      }
      return -1;
   }
}
