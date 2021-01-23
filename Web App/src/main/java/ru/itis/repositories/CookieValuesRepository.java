package ru.itis.repositories;

import ru.itis.models.CookieValue;

public interface CookieValuesRepository extends CrudRepository<CookieValue, Long> {

    boolean cookieExist(String cookie);

}
