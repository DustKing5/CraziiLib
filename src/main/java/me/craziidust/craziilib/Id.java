package me.craziidust.craziilib;

import java.util.Random;

public class Id {

    private static final Random RANDOM = new Random();
    private final String id;

    public Id(String id) {
        this.id = id;
    }

    public Id() {
        id = RANDOM.ints(97,123)
                .limit(5)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append
                ).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Id id1 = (Id) o;

        return id.equals(id1.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
