package objects.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import objects.controller.interfaces.UtilisateurUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.BizzFactoryImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurUcControlleurTest {
  BizzFactory factory = new BizzFactoryImpl();
  BizzFactory mockFactory = new MockupFactory();
  UtilisateurDto utilisateurDto = (UtilisateurDto) factory.getDto(UtilisateurDto.class
      .getSimpleName());
  UtilisateurUcController utilController = mockFactory.getUtilisateurUcController();


  @Before
  public void setUp() throws Exception {
    utilisateurDto.setPseudo("lama");
    utilisateurDto.setMdp("wizard");
  }


  // authentification reussie.
  @Test
  public void authentifie1() {
    UtilisateurDto temp = (UtilisateurDto) mockFactory.getDto(UtilisateurDto.class.getSimpleName());
    temp.setPseudo("lama");
    temp.setMdp("wizard");
    temp = utilController.authentifie(temp);
    assertNotNull(temp);
  }


  // authentification mdp mauvais.
  @Test
  public void authentifie2() {
    UtilisateurDto ret = utilController.authentifie(utilisateurDto);
    ret.setPseudo("lama");
    ret.setMdp("jesus");
    ret = utilController.authentifie(ret);
    assertNull(ret);
  }

  // Identification d'un pseudo existant.
  @Test
  public void identifie1() {
    UtilisateurDto ret = utilController.identifie(utilisateurDto);
    assertNull(ret);
  }

  // Identification d'un pseudo non existant.


  @Test(expected = InternalError.class)
  public void identifie4() {
    UtilisateurDto temp = (UtilisateurDto) mockFactory.getDto(UtilisateurDto.class.getSimpleName());
    temp.setPseudo(null);
    throw new InternalError();


  }

  @Test
  public void getEtudiantPseudo() {
    List<UtilisateurDto> aReturn = new ArrayList<>();
    aReturn = utilController.getEtudiantsPseudo();
    assertTrue(aReturn.isEmpty());
  }

  @Test
  public void getEtudiant() {
    List<UtilisateurDto> aReturn = new ArrayList<>();
    aReturn = utilController.getEtudiants();
    assertTrue(aReturn.isEmpty());
  }

  @Test
  public void getEtudiant2() {
    UtilisateurDto etu = (UtilisateurDto) mockFactory.getDto(UtilisateurDto.class.getSimpleName());
    etu.setPseudo("lama");
    UtilisateurDto ret = utilController.getEtudiant(etu);
    assertEquals(ret.getPseudo(), etu.getPseudo());
  }

  @Test
  public void emptyOrNot() {
    boolean ret = utilController.emptyOrNot();
    assertFalse(ret);
  }

  @Test
  public void changerTypeCompte() {
    UtilisateurDto utilisateur =
        (UtilisateurDto) mockFactory.getDto(UtilisateurDto.class.getSimpleName());
    utilisateur.setPseudo("lama");

    boolean ret = utilController.changerTypeCompte(utilisateur, "status");
    assertFalse(ret);
  }



  @Test
  public void encoderDemande1() {
    DemandeDto demande = (DemandeDto) mockFactory.getDto(DemandeDto.class.getSimpleName());
    PartenaireDto temp = (PartenaireDto) mockFactory.getDto(PartenaireDto.class.getSimpleName());


    boolean aTest = utilController.encoderDemande(demande, temp);
    assertTrue(aTest);
  }

  // Encoder une demande avec un partenaire existant
  @Test
  public void encoderDemande2() {
    DemandeDto demande = (DemandeDto) mockFactory.getDto(DemandeDto.class.getSimpleName());
    PartenaireDto temp = (PartenaireDto) mockFactory.getDto(PartenaireDto.class.getSimpleName());
    boolean aTest = utilController.encoderDemande(demande, temp);
    assertTrue(aTest);
  }

  // Encoder un etudiant -> update

  @Test
  public void encoderEtudiant1() {
    UtilisateurDto utilAvModif =
        (UtilisateurDto) mockFactory.getDto(UtilisateurDto.class.getSimpleName());
    UtilisateurDto utilApModif =
        (UtilisateurDto) mockFactory.getDto(UtilisateurDto.class.getSimpleName());
    boolean aTest = utilController.encoderEtudiant(utilAvModif, utilApModif);
    assertTrue(aTest);
  }

}
