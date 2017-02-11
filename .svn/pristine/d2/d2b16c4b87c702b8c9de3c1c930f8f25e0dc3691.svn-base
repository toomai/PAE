package projet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
  static Properties prop;
  static final String PROP_URL = "prod.properties";

  static {
    try {
      prop = new Properties();
      FileInputStream input = new FileInputStream(PROP_URL);
      try {
        prop.load(input);
      } finally {
        input.close();
      }
    } catch (IOException excep) {
      excep.printStackTrace();
      System.exit(2);
    }
  }

  public static String getImpl(String inter) {
    return prop.getProperty(inter);
  }

  public static String getDbUrl() {
    return prop.getProperty("dbUrl");
  }

  public static String getUserDb() {
    return prop.getProperty("dbUserName");
  }

  public static String getPasswordDb() {
    return prop.getProperty("dbPassword");
  }

  public static String getDbDriver() {
    return prop.getProperty("dbDriver");
  }

  public static String getFactory() {
    return prop.getProperty("BizzFactory");
  }
}
