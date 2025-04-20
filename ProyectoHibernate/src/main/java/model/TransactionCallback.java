package model;

import org.hibernate.Session;

public interface TransactionCallback<T> {
    T execute(Session session) throws Exception;
}
