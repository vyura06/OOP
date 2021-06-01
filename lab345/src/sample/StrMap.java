package sample;

import javafx.util.StringConverter;

import java.util.List;
import java.util.Objects;

class StrMap<T> extends StringConverter<T> {
    private final List<T> list;
    private final String[] names;

    public StrMap(List<T> list, String... names) {
        this.list = Objects.requireNonNull(list);
        this.names = names;
    }

    public String toString(T t) {
        int i = list.indexOf(t);
        return i < 0 ? null : names[i];
    }

    public T fromString(String s) {
        for (int i = 0; i < names.length; i++)
            if (names[i].equals(s))
                return list.get(i);
        return null;
    }
}
