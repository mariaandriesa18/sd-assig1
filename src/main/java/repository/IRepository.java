package repository;

import java.util.List;

public interface IRepository<T> {

    public void insertEntry(T obj);
    T findByInt(Integer criterion);
    T findByString(String criterion);
    public void updateEntry(T obj);
    public void deleteEntry(T obj);

    public List<T> queryForAll();
   // public List<T> queryForAllWith(String criterion);


}
