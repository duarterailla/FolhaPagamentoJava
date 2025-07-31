package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Responsável por fornecer os componentes necessários para estabelecer conexões com o
// banco de dados, garantindo que todos os parâmetros sejam configurados adequadamente.

public class ConnectionFactory {
    private static String url = "jdbc:postgresql://localhost:5432/POOProjetoFinal";
    private static String username = "postgres";
    private static String password = "root";


    //Implementa blocos try-catch para detectar e tratar possíveis falhas,
    // como: Conexão mal-sucedida.

    //Argumentos incorretos na configuração.

    public static Connection getConnection() {
        System.out.println("Fazendo a conexão....");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Conexão não efetuada!");
            e.printStackTrace();
        }
        return connection;
    }

}