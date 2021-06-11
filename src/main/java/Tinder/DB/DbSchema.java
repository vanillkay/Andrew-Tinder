package Tinder.DB;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

public class DbSchema {

    private final String conn;
    private final String user;
    private final String pass;

    public DbSchema(String conn, String user, String pass) {
        this.conn = conn;
        this.user = user;
        this.pass = pass;
    }

    public void migrate() {
        FluentConfiguration conf = new FluentConfiguration()
                .dataSource(conn, user, pass);
        new Flyway(conf).migrate();
    }

}

