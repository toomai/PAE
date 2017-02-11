package dal;

/*
 * start transaction commit transaction rollback transaction
 */
public interface DalServices {
  void start();

  void commit();

  void rollBack();

  void close();
}
