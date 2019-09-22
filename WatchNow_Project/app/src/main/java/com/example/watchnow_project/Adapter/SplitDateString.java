package com.example.watchnow_project.Adapter;

public class SplitDateString {
   public static String getDate(String str){
       String[] temp = new String[2];
       temp = str.split("\\s",2);
       String[] temp2 = new String[3];
       temp2 = temp[0].split("\\-",3);

       return temp2[2]+"-"+temp2[1]+"-"+temp2[0];
   }
}
