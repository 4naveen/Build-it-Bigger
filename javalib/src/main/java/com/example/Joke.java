package com.example;

import java.util.Random;

public class Joke {

    String[] jokes = new String[5];
    private Random random = new Random();

    public String getJoke(){

        jokes[0] = "Hide and seek champion ..."+"\n;"+"\n since 1958";
        jokes[1] = "Why do Java developers wear glasses? Because they can't C#(see sharp)";
        jokes[2] = "Programmer?"+"\nA person who fixed problem that you don't know you have,in a way you don't understand";
        jokes[3]="Real programmer count from 0";
        jokes[4]="why did the programmer quit his jobs? Because he didn't get arrays( a raise)";

        return jokes[random.nextInt(jokes.length)];
    }
}
