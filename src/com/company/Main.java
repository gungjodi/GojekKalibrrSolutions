package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        String fileName = "input.in";
        try (Scanner scanner = new Scanner(new File(fileName))){
            int cases = scanner.nextInt();
            int[] count = new int[cases];
            for(int i=1;i<=cases;i++)
            {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int k = scanner.nextInt();
                count[i-1]=0;
                for(int j=a;j<=b;j++)
                {
                    if(j%k==0)
                    {
                        count[i-1]+=1;
                    }
                }
            }
            for(int i=1;i<=cases;i++)
            {
                System.out.println("Case "+i+": "+count[i-1]);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
}
