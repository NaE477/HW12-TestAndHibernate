package bank;

import java.util.List;

public interface ThingCRUD<O> {

    Integer insert(O o);
    O read(Integer targetId);
    List<O> readAll();
    Integer update(O obj);
    void delete(O obj);
}
