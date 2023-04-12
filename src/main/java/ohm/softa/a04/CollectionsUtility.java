package ohm.softa.a04;

import java.util.Comparator;

public abstract class CollectionsUtility<T> {

    abstract SimpleList<T> sort(SimpleList<T> list, Comparator<T> cmp);
}
