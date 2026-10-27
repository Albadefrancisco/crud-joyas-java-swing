package bd;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBase {


    private  HikariDataSource dataSource;

    private static DataBase instance;

    private DataBase()
    {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/programacion");
        config.setUsername("programacion");
        config.setPassword("programacion");
        config.setMaximumPoolSize(10); // Máximo de conexiones en el pool
        config.setMinimumIdle(2); // Mínimo de conexiones inactivas

        config.setIdleTimeout(30000); // Tiempo de espera antes de liberar conexiones ms

        config.setConnectionTimeout(30000); // Tiempo máximo de espera para obtener conexión 30 seg

        config.setMaxLifetime(1800000); // Tiempo máximo de vida de una conexión 30 minutos


        dataSource = new HikariDataSource(config);
    }

    public static DataBase getInstance()
    {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;

    }

    public HikariDataSource getDataSource()
    {
        return dataSource;
    }


}