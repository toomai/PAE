package objects.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import objects.controller.interfaces.RechercheUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.PaysDto;
import objects.interfaces.dto.RechercheDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.BizzFactoryImpl;

public class RechercheUcControllerTest {
  BizzFactory factory = new BizzFactoryImpl();
  BizzFactory mockFactory = new MockupFactory();
  PartenaireDto partenaireDto = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
  AdresseDto adresseDto = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
  PaysDto paysDto = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
  MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
  UtilisateurDto utilisateurDto =
      (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
  RechercheDto rechercheDto = (RechercheDto) factory.getDto(RechercheDto.class.getSimpleName());
  RechercheUcController rechercheController = mockFactory.getRechercheUcController();

  // test effectuerRechercheMobilite
  @Test
  public void effectuerRechercheMobilite1() {
    List<MobiliteDto> ret = rechercheController.effectuerRechercheMobilite();
    assertNotNull(ret);
  }

  /*
   * // test effectuer recherche partenaire existant
   * 
   * @Test public void effectuerRecherchePartenaire1() { PartenaireDto ret = rechercheController
   * .effectuerRecherchePartenaire("Mon Fromage bien aimé"); assertNotNull(ret); }
   * 
   * // test effectuer recherche partenaire inexistant
   * 
   * @Test public void effectuerRecherchePartenaire2() { PartenaireDto ret = rechercheController
   * .effectuerRecherchePartenaire("Mon Fromage bien"); assertNull(ret); }
   */
  // test getAllPartenaireDto
//  @Test
//  public void getAllPartenaireDispo1() {
//    List<PartenaireDto> ret = rechercheController.getAllPartenairesDispo();
//    assertNotNull(ret);
//  }

  // test getAllPartenaire
  @Test
  public void getAllPartenaire1() {
    List<PartenaireDto> ret = rechercheController.getAllPartenaire();
    assertNotNull(ret);
  }

  // test effectuer recherche utilisateur
  @Test
  public void effectuerRechercheUtilisateurs1() {
    List<UtilisateurDto> ret = rechercheController.effectuerRechercheUtilisateurs();
    assertNotNull(ret);
  }


  // test select user by names nom et prenom ok
  @Test
  public void selectUserByNames1() {
    UtilisateurDto ret = rechercheController.selectUserByNames("Kendrik", "Lamar");
    assertNotNull(ret);
  }


  // test select user by names nom et prenom ko
  @Test
  public void selectUserByNames2() {
    UtilisateurDto ret = rechercheController.selectUserByNames("Lamar", "Kendrik");
    assertNull(ret);
  }

  // test select user by names nom ok et prenom ko
  @Test
  public void selectUserByNames3() {
    UtilisateurDto ret = rechercheController.selectUserByNames("Kendrik", "Lama");
    assertNull(ret);
  }

  // test select user by names nom KO et prenom OK
  @Test
  public void selectUserByNames4() {
    UtilisateurDto ret = rechercheController.selectUserByNames("Kendric", "Lamar");
    assertNull(ret);
  }

  //// TEST KO
  // TODO
  /*
   * // test select mob by user and part OK
   * 
   * @Test public void selectMobByUserAndPart1() { MobiliteDto ret =
   * rechercheController.selectMobByUserAndPart("Lama", "Mon Fromage bien aimé");
   * assertNotNull(ret); }
   * 
   * // test select mob by user and part KO
   * 
   * @Test(expected = NullPointerException.class) public void selectMobByUserAndPart2() {
   * MobiliteDto ret = rechercheController.selectMobByUserAndPart("Lamar", "Mon Fromage bien");
   * 
   * }
   */

  // test select mob by user KO and part OK
  @Test
  public void selectMobByUserAndPart3() {
    MobiliteDto ret = rechercheController.selectMobByUserAndPart("Lamar", "Mon Fromage bien aimé");
    assertNull(ret);
  }
  // TEST KO
  // TODO
  /*
   * // test select mob by user OK and part KO
   * 
   * @Test(expected = NullPointerException.class) public void selectMobByUserAndPart4() {
   * MobiliteDto ret = rechercheController.selectMobByUserAndPart("Lama", "Mon Fromage bien"); }
   */

  // test getPays
  @Test
  public void getPays() {
    List<PaysDto> ret = rechercheController.getPays();
    assertNotNull(ret);
  }

}
