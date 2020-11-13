package ru.itis.repositories;

import ru.itis.models.CookieValue;

public interface CookieValuesRepository extends CrudRepository<CookieValue, Long> {
    //CookieValue findCookieByStudentIdAndCookieName(Long id, String name);
    boolean cookieExist(String cookie);
}
