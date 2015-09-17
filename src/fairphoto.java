import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class fairphoto {
   static Scanner scan;
   static PrintWriter write;
   static int range;
   static int flag;
   static Cow[] cows;
   static int[] min;
   static boolean[] minChecked;
   static int[] max;
   public static void main(String[] args){
      loadFile();
      int current = 0;
      minChecked = new boolean[min.length];
      for(int i = 0; i < cows.length; i++){
         current += cows[i].breed;
         if(max[current+range] < cows[i].pos)
            max[current+range] = cows[i].pos;
         if(min[current+range] > cows[i].pos || !minChecked[current+range]){
            min[current+range] = cows[i].pos;
            minChecked[current+range] = true;
         }
      }
      int total = 0;
      int maxTotal = total;
      for(int i = 0; i < min.length; i++){
         
            total = max[i] - min[i];
            if(total > maxTotal){
               maxTotal = total;
            }
         
      }
      System.out.println(maxTotal);
   }
//get all the cows sorted and read into an array
   public static void loadFile(){
      try {
         scan = new Scanner(new File("fairphoto.in"));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      range = scan.nextInt();
      cows = new Cow[range];
      min = new int[range*2];
      max = new int[range*2];
      flag = range*2+1;
      for(int i = 0; i < range; i++){
         int pos = scan.nextInt();
         char breed = scan.next().charAt(0);
         int num;
         if(breed == 'H')
            num = -1;
         else
            num = 1;
         cows[i] = new Cow(pos, num);
      }
      Arrays.sort(cows);
   }
}
class Cow implements Comparable<Cow>{
   public int pos;
   public int breed;
   public Cow(int nPos, int nBrd){
      pos = nPos;
      breed = nBrd;
   }
   public boolean equals(Cow cow){
      return breed == cow.breed;
   }
   
   public int compareTo(Cow o) {
      // TODO Auto-generated method stub
      return pos - o.pos;
   }
}

class Indices{
   public int position;
   public int num;
   public Indices(int pos, int num){
      position = pos;
      this.num = num;
   }
}
