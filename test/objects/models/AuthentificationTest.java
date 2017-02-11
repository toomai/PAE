/**
 * 
 */
package objects.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import objects.model.Authentification;

import org.junit.Before;
import org.junit.Test;

/**
 * @author loic
 *
 */
public class AuthentificationTest {

  String mdp = "123456";
  String mdpCrypte = "1000:323bc3fc67da98e1f6be7c9627b4b76a:c781f25b7e70f21c7f3d96fec2d10e"
      + "1fe1b88ca13912df74174aaa0aa0b3e485d4696d3eb7b46e360f0ba91176d30014c"
      + "6bc62c1af4783847e511643c6079496";

  @Before
  public void setUp() throws Exception {}


  @Test
  // test reussi
  public void testGenererMdp() throws Exception {
    assertEquals(166, Authentification.genererMdp(mdp).length(), 0);
  }


  @Test
  // test reussi
  public void testValiderMdp() throws Exception {
    assertTrue(Authentification.validerMdp(mdp, mdpCrypte));
  }

}
