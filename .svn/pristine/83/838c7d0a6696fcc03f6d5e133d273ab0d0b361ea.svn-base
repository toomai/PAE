package dal;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DalServicesImpl implements DalServices, DalAccessServices {
  private BasicDataSource ds;
  // private Connection conn = null;
  private ThreadLocal<Connection> map;
  private Logger logger = LogManager.getLogger(DalServices.class);

  /**
   * constructeur.
   */

  public DalServicesImpl(BasicDataSource ds) {
    this.ds = ds;
    map = new ThreadLocal<Connection>();

  }

  /*
   * public DalServicesImpl(String url) { /* try { Class.forName("org.postgresql.Driver"); conn =
   * DriverManager.getConnection(url); } catch (ClassNotFoundException exc) { logger.fatal(
   * "Driver Postgres manquant"); } catch (SQLException excep) { // TODO Auto-generated catch
   * logger.fatal("Sqlexception" + excep.getSQLState()); }
   * 
   * }
   */

  /**
   * ouverture de l'app.
   */

  public void start() {
    try {
      getConnection().setAutoCommit(false);
    } catch (SQLException except) {
      logger.error("erreur start:" + except.getSQLState());
      throw new InternalError();
    }
  }

  /**
   * fermeture de l'app.
   */
  public void close() {
    try {
      getConnection().setAutoCommit(true);
      giveUpConnection();
    } catch (SQLException except) {
      logger.error("error clos: " + except.getSQLState());
      throw new InternalError();
    }
  }

  private void giveUpConnection() {
    Connection con = this.map.get();
    if (con != null) {
      try {
        con.close();
      } catch (SQLException excep) {
        logger.error(excep.getSQLState());
      }
      this.map.remove();
    }
  }



  /**
   * maj de l'app.
   */

  public void commit() {
    try {
      getConnection().commit();
    } catch (SQLException exc) {
      logger.error("erreur commit: " + exc.getSQLState());
      throw new InternalError();
    }
  }

  /**
   * annule la transaction.
   */

  public void rollBack() {
    try {
      getConnection().rollback();
    } catch (SQLException exc) {
      logger.error("erreur rollback: " + exc.getSQLState());
    }
  }

  /**
   * ferme la connexion.
   */

  private Connection getConnection() {
    Connection con = map.get();
    if (con == null) {
      try {
        con = this.ds.getConnection();
        map.set(con);
      } catch (SQLException except) {
        except.printStackTrace();
        logger.error("erreur getConn " + except.getSQLState());
      }
    }
    return con;
  }

  /**
   * prepare un statement pour la db.
   */

  public PreparedStatement prepare(String sql) {
    try {
      return getConnection().prepareStatement(sql);
    } catch (SQLException exc) {
      logger.error("erreur prepare :" + exc.getSQLState());
    }
    return null;
  }

}
