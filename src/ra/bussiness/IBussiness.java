package ra.bussiness;

import java.util.List;

public interface IBussiness<T, K> {
    //T: entity - K: primary key
    List<T> findAll();

    boolean create(T t);

    boolean update(T t);

    boolean delete(K k);

    T findById(K k);
}
