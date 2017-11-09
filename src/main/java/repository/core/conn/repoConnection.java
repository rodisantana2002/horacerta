/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core.conn;

/**
 *
 * @author Rodolfo
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class repoConnection {

    private static repoConnection me;
    private Configuration cfg;
    private SessionFactory sessionFactory;

    private repoConnection() throws HibernateException {
        // build the config
        cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static synchronized repoConnection getInstance() throws HibernateException {
        if (me == null) {
            me = new repoConnection();             
        }
        return me ;
    }

    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }
    
    private void reconnect() throws HibernateException {
        this.sessionFactory = cfg.buildSessionFactory();
    }
}
