package me.craziidust.craziilib;

import java.util.Random;

public class Id {

    private static final Random RANDOM = new Random();

    private Id() {

    }

    public String get() {
        return RANDOM.ints(97,123)
                .limit(5)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append
                ).toString();
    }
}
