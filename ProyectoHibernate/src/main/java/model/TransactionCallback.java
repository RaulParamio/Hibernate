package model;

import org.hibernate.Session;

/** Esta interfaz permite pasar una función personalizada que realice operaciones en la base de datos,
 *  y devuelve un resultado de tipo T (generico).
 * 
 * Se utiliza normalmente junto con una clase como TransactionExecutor, que gestiona la apertura de sesión, 
 * la transacción y el manejo de errores, mientras tú defines qué se ejecuta.
 */
public interface TransactionCallback<T> {
    T execute(Session session) throws Exception;
}
