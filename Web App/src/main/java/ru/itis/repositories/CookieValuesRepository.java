package ru.itis.repositories;

import ru.itis.models.CookieValue;

public interface CookieValuesRepository extends CrudRepository<CookieValue, Long> {
    CookieValue findCookieByIdAndName(Long id, String name);
    boolean cookieExist(String cookie);
}
