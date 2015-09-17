import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class solution {
    public static void main(String[] args) {
       Scanner keyboard = new Scanner(System.in);
       int start = keyboard.nextInt();
       String[] decentNums = new String[start];
       for(int i = 0; i < decentNums.length; i++){
          int numOfChars = keyboard.nextInt();
          String num = "";
          //starting condition
          for(int j = 0; j < numOfChars; j++){
             num += "5";
          }
          int numsize = num.length();
          while(isDecentNum(num) || numsize < 5){
             if(num.length() > 5)
                num = num.substring(0, num.length()-5) + "33333";
             numsize -= 5;
          }
          if(isDecentNum(num))
             System.out.println("true");
       }
    }
    
    public static boolean isDecentNum(String num){
       int five = 0, three = 0;
       for(int i = 0; i < num.length(); i++){
          if(num.charAt(i) == '5')
             five++;
          else
             three++;
       }
       return ((five%3 == 0) && (three%5 == 0));
    }
}