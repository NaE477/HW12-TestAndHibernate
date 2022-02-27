package onlineShop;

import lombok.Getter;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

@Getter
public abstract class Repository<T> implements BaseRepository<T> {
    private final SessionFactory sessionFactory;

    public Repository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

}
