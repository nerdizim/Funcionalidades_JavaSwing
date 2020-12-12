package br.com.projeto.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dindo
 */
public class ConnectionFactory {

    public Connection getConnection() {

      
        try {

            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/guardsites", "root", "");

        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }

    }

}
