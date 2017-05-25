package com.naveen.build_it_bigger.backend;

import com.example.Joke;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {
    private Joke tellJoke;

    public MyBean() {
        tellJoke = new Joke();
    }

    public String getData() {
        return tellJoke.getJoke();
    }

}