package util;

import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.PaysDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Util {

  /**
   * 
   * @param obj peut importe on le check.
   *
   */

  static void checkObject(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException("L'objet ne peut pas être null");
    }
  }

  /**
   * 
   * @param str chaine de caractere a verifier.
   */

  static void checkString(String str) {
    checkObject(str);
    if (str.matches("\\s*")) {
      throw new IllegalArgumentException("La chaîne ne peut pas être vide");
    }
  }

  /**
   * 
   * @param str chaine de caractere a verifier.
   */

  static void checkNumerique(String str) {
    checkString(str);
    try {
      Long.parseLong(str);
    } catch (NumberFormatException exc) {
      throw new IllegalArgumentException("La chaîne doit être un nombre valide");
    }
  }

  /**
   * 
   * @param nombre nbr a verifier.
   */

  static void checkPositive(double nombre) {
    if (nombre <= 0) {
      throw new IllegalArgumentException("La valeur ne peut pas être négative ou nulle");

    }
  }

  static void checkPositiveOu0(double nombre) {
    if (nombre < 0) {
      throw new IllegalArgumentException("La valeur ne peut pas être négative");

    }
  }

  static void checkPlusGrand(double nombre, int max) {
    checkPositive(nombre);
    checkPositive(max);

    if (nombre < max)
      throw new IllegalArgumentException("Le nombre est trop petit");
  }

  static void checkStringSize(String str, int size) {
    checkString(str);
    checkPositive(size);

    if (str.length() > size)
      throw new IllegalArgumentException("La chaine de cara" + "ctere est plus longue qu'attendu");
  }

  static boolean checkAdresse(AdresseDto adresse) {
    try {
      checkStringSize(adresse.getBoite(), 5);
      checkStringSize(adresse.getCp(), 20);
      checkStringSize(adresse.getRue(), 100);
      checkStringSize(adresse.getRegion(), 25);
      checkStringSize(adresse.getVille(), 50);
      checkPositive(adresse.getNumero());
      checkStringSize(adresse.getPays(), 2);
      checkPositive(adresse.getVersion());
    } catch (IllegalArgumentException excep) {
      return false;
    }
    return true;
  }

  static boolean checkPays(PaysDto pays) {
    try {
      checkStringSize(pays.getCodeIso(), 2);
      checkStringSize(pays.getNom(), 80);
      checkPositive(pays.getVersion());
      return true;
    } catch (IllegalArgumentException exec) {
      return false;
    }
  }

  static boolean checkEmail(String email) {
    int length = email.length();
    boolean rep = false;
    if (length < 1000000000) {
      Pattern p = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
      Matcher m = p.matcher(email);
      rep = m.matches();
    }
    return rep;
  }

  static boolean checkWeb(String siteWeb) {
    int length = siteWeb.length();
    boolean rep = false;
    if (length < 1000000000) {
      Pattern p =
          Pattern
              .compile("^(https?\\:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$");
      Matcher m = p.matcher(siteWeb);
      rep = m.matches();
    }
    return rep;
  }

  static boolean checkTel(String telephonne) {
    int length = telephonne.length();
    boolean rep = false;
    if (length < 1000000000) {
      Pattern p = Pattern.compile("^0[1-9][0-9]{7,8}$");
      Matcher m = p.matcher(telephonne);
      rep = m.matches();
    }
    return rep;
  }
}
