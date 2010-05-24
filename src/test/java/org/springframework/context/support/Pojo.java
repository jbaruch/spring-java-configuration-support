package org.springframework.context.support;

/**
 * Created by IntelliJ IDEA.
 *
 * @author JBaruch
 * @since 24-May-2010
 */
public class Pojo {

    private final String name;

    public Pojo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pojo pojo = (Pojo) o;

        return name.equals(pojo.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "name='" + name + '\'' +
                '}';
    }
}
