package ohm.softa.a04;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T item);

	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list

	SimpleList<T> filter(SimpleFilter<T> filter);
     */

	/**
	 * @param clazz Class instance to solve the instantiation problem
	 */

	default void addDefault(Class<T> clazz) {
		try{
			this.add(clazz.newInstance());
		}catch(InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}


	default SimpleList<T> filter(SimpleFilter<T> filter){
		SimpleList<T> result = new SimpleListImpl<T>();
		for(T o : this){
			if(filter.include(o)){
				result.add(o);
			}
		}
		return result;
	}


	default <R> SimpleList<R> map(Function<T,R> transform){
		SimpleList<R> transformedList;
		try{
			transformedList = (SimpleList<R>) this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			transformedList = new SimpleListImpl<>();
		}
		for(T t : this){
			transformedList.add(transform.apply(t));
		}
		return transformedList;
	}


}
