package dal;

import java.sql.PreparedStatement;

/*
 * Prepared statement, on stocke tous les preparedStatement *je pense * connection à la DB
 */
public interface DalAccessServices {
  PreparedStatement prepare(String sql);
}
