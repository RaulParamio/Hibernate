package repository;

import java.util.List;

public interface IRepositorio<T, ID> {

	long count();

	void deleteById(ID id);

	void deleteAll();

	boolean existsById(ID id);

	T getById(ID id);

	List<T> findAll();

	<S extends T> S save(S entity);

}
