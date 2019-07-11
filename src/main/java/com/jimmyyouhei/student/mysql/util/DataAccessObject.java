package com.jimmyyouhei.student.mysql.util;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DataAccessObject <T> {

    T get(long id);

    List<T> getAll();
    void create(T dto);
    T update(T dto);
    void delete(T dto);

}
