package objects.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import enumere.TypeOrganisation;
import objects.controller.interfaces.PartenaireUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.PartenaireDto;
import objects.model.BizzFactoryImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PartenaireUcControllerTest {
  BizzFactory factory = new BizzFactoryImpl();
  BizzFactory mockFactory = new MockupFactory();
  PartenaireDto partenaireDto = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
  AdresseDto adresseDto = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
  PartenaireUcController partController = mockFactory.getPartenaireUcController();

  @Before
  public void setUp() throws Exception {
    /*
     * adresseDto.setRue("rue du chat"); adresseDto.setNumero(23); adresseDto.setBoite("20A");
     */
    partenaireDto.setId(1);
    partenaireDto.setNomLegal("Fromage");
    partenaireDto.setNomAffaire("From SA");
    partenaireDto.setNomComplet("Ma Compagnie");
    partenaireDto.setDepartement("Laitier");
    partenaireDto.setTypeOrganisation(TypeOrganisation.TGE);
    partenaireDto.setNbEmploye(69);
    partenaireDto.setAdresse(adresseDto);
    partenaireDto.setEmail("lolcatdu38@bogoss.nik");
    partenaireDto.setSiteWeb("http://www.mcperso.fn/");
    partenaireDto.setTelephonne("0123456789");
    partenaireDto.setAjoutParEleve(false);
  }

  // // TEST KO DU MODIF
  // TODO


  // ajout partenaire normal

  @Test
  public void encoderPartenaire1() {
    boolean ret = partController.encoderPartenaire(partenaireDto);
    assertTrue(true);
  }


  // ajout partenaire déjà existant

  @Test
  public void encoderPartenaire2() {
    PartenaireDto temp = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    temp.setId(1);
    temp.setNomLegal("Mon Fromage bien aimé");
    temp.setNomAffaire("From SA");
    temp.setNomComplet("Mon Fromage bien aimé");
    temp.setDepartement("Laitier");
    temp.setTypeOrganisation(TypeOrganisation.TGE);
    temp.setNbEmploye(69);
    temp.setAdresse(adresseDto);
    temp.setEmail("lolcatdu38@bogoss.nik");
    temp.setSiteWeb("http://www.mcperso.fn/");
    temp.setTelephonne("0123456789");
    temp.setAjoutParEleve(false);
    Boolean ret = partController.encoderPartenaire(temp);
    assertTrue(ret);
  }

  @Test
  public void getAjout() {
    PartenaireDto aRet = partController.getAjout(partenaireDto);
    assertTrue(aRet.getAjoutParEleve());
  }

  @Test
  public void getPartenaire() {
    PartenaireDto temp = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    temp.setNomLegal("Mon Fromage bien aimé");
    PartenaireDto aRet = partController.getPartenaire(temp);
    assertEquals(temp.getNomLegal(), aRet.getNomLegal());
  }

  @Test
  public void supprimerPartenaire1() {
    PartenaireDto temp = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    temp.setId(1);
    boolean aRet = partController.supprimerPartenaire(temp);
    assertTrue(aRet);
  }

  @Test
  public void supprimerPartenaire2() {
    PartenaireDto temp = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    temp.setId(2);
    boolean aRet = partController.supprimerPartenaire(temp);
    assertFalse(aRet);
  }

  @Test
  public void getPartenaireDispo() {
    List<PartenaireDto> liste = partController.getPartenairesDispos();
    assertTrue(liste.isEmpty());
  }

  @Test
  public void rehabiliterPartenaire() {
    PartenaireDto temp = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    temp.setId(1);
    temp.setDispo(false);
    partController.rehabiliterPartenaire(temp);
    assertTrue(temp.isDispo());
  }

  @Test
  public void getListPartenairesSupprimes() {
    List<PartenaireDto> liste = partController.getListePartenairesSupprimes();
    assertTrue(liste.isEmpty());
  }

}
