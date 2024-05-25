package com.company;
import java.util.Scanner;
import java.io.*;

public class Word {
    private final String WRONG = "\033[1;100m";
    private final String CLOSE = "\033[1;103m";
    private final String RIGHT = "\033[1;102m";
    private final String NORMAL = "\033[m";
    // is 5 letters and ENGLISH
    private String word;

    public Word()
    {
        int count = 0;
        try {
            Scanner tanner = new Scanner(new File("words.txt"));
            while(tanner.hasNextLine() && tanner.nextLine()!="")
                count++;
            int rng = (int)Math.random()*count+1;
            tanner = new Scanner(new File("words.txt"));
            for(int i =0; i < rng; i++)
                word = tanner.nextLine();
            tanner.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void guess(String str)
    {
        String[] output = new String[5];
        String tempWord = word;
        for(int i = 0; i < str.length();i++) {
            if (str.substring(i, i + 1).equals(tempWord.substring(i, i + 1))) {
                output[i] = RIGHT + str.substring(i, i + 1);
                tempWord = tempWord.substring(0, i) + " " + tempWord.substring(i + 1);
                str = str.substring(0, i) + " " + str.substring(i + 1);
            }

        }
        for(int i = 0; i < str.length();i++)
        {
            for(int j = 0; j < tempWord.length();j++)
            {
                if(str.charAt(i) == ' ')
                    ;
                else if (str.substring(i, i + 1).equals(tempWord.substring(j, j + 1)) )
                {
                    output[i] = CLOSE + str.substring(i, i + 1);
                    tempWord = tempWord.substring(0, j) + " " + tempWord.substring(j + 1);
                    str = str.substring(0, i) + " " + str.substring(i + 1);
                    j = str.length()+1;
                }
            }
            if(str.charAt(i) != ' ')
            {
                output[i] = WRONG + str.substring(i, i + 1);
            }
            System.out.print(output[i] + NORMAL);
        }
    }
    public void play()
    {
        String guess = "";
        int lives = 6;
        Scanner tanner = new Scanner(System.in);
        while(lives > 0 && !guess.equals(word))
        {
            guess = tanner.nextLine();
            guess(guess);
            System.out.println();
            lives--;
        }
        System.out.print(word);
    }
}
