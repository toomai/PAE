package objects.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import enumere.TypeDemande;
import enumere.TypeMobilite;
import objects.controller.interfaces.DemandeUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.PaysDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.BizzFactoryImpl;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class DemandeUcControllerTest {
  BizzFactory factory = new BizzFactoryImpl();
  BizzFactory mockFactory = new MockupFactory();
  PartenaireDto partenaireDto = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
  AdresseDto adresseDto = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
  PaysDto paysDto = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
  MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
  UtilisateurDto utilisateurDto = (UtilisateurDto) factory.getDto(UtilisateurDto.class
      .getSimpleName());
  DemandeDto demandeDto = (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());
  DemandeUcController demandeController = mockFactory.getDemandeUcController();

  @Before
  public void setUp() {
    adresseDto.setPays("Belgique");
    demandeDto.setIdDemande(1);
    demandeDto.setAnneeAcademique(2017);
    demandeDto.setPeriode("Q2");
    demandeDto.setTypeDemande(TypeDemande.SMS);
    mobiliteDto.setChoix(1);
    mobiliteDto.setDateDebut(LocalDate.of(2017, 02, 27));
    mobiliteDto.setDateFin(LocalDate.of(2017, 06, 27));
    utilisateurDto.setPseudo("lol");
    demandeDto.setEtudiant(utilisateurDto);
    partenaireDto.setNomComplet("Fromage");
    partenaireDto.setId(1);
    partenaireDto.setAdresse(adresseDto);

    demandeDto.setPartenaire(partenaireDto);

  }

  // test supression demande existante
  @Test
  public void supprimerDemande1() {
    boolean ret = demandeController.supprimerDemande(demandeDto);
    assertTrue(ret);
  }

  // test supression demande inexistante
  @Test
  public void supprimerDemande2() {
    demandeDto.setIdDemande(0);
    boolean ret = demandeController.supprimerDemande(demandeDto);
    assertFalse(ret);
  }

  // test lister demandes
  @Test
  public void listerDemandes1() {
    List<DemandeDto> ret = demandeController.listerDemandes();
    assertNotNull(ret);
  }

  // test affichage d'une demande existante.
  @Test
  public void donnerDemandesAvecId1() {
    DemandeDto ret = demandeController.donnerDemandesAvecId(demandeDto);
    assertNotNull(ret);
  }

  // test affichage d'une demande inexistante.
  @Test
  public void donnerDemandesAvecId2() {
    demandeDto.setIdDemande(2);
    DemandeDto ret = demandeController.donnerDemandesAvecId(demandeDto);
    assertNull(ret);
  }

  // test liste demandes utilisateur existant.
  @Test
  public void listerDemandesParUser1() {
    List<DemandeDto> ret = demandeController.listerDemandesParUser("lol");
    assertNotNull(ret);
  }

  // test liste demandes utilisateur inexistant.
  @Test(expected = NullPointerException.class)
  public void listerDemandesParUser2() {
    List<DemandeDto> ret = demandeController.listerDemandesParUser("loli");
  }

  // test lister demandes par user full existant.
  @Test
  public void listerDemandesParUserFull1() {
    List<DemandeDto> ret = demandeController.listerDemandesParUserFull(utilisateurDto);
    assertNotNull(ret);
  }

  // test lister demandes par user full existant.
  @Test
  public void listerDemandesParUserFull2() {
    utilisateurDto.setPseudo("Lama");
    List<DemandeDto> ret = demandeController.listerDemandesParUserFull(utilisateurDto);
    assertNull(ret);
  }

  // Confirmer demande existante

  @Test
  public void confirmerDemande1() {
    paysDto.setNom("Belgique");
    paysDto.setCodeIso("BE");
    paysDto.setTypeMobilite(TypeMobilite.ERABEL);
    boolean ret = demandeController.confirmerDemande(demandeDto, mobiliteDto);
    assertTrue(ret);
  }

  // Confirmer demande inexistante -> lance NullPointerException

  @Test(expected = NullPointerException.class)
  public void confirmerDemande2() {
    demandeDto.setIdDemande(2);
    boolean ret = demandeController.confirmerDemande(demandeDto, mobiliteDto);
  }

  @Test(expected = NullPointerException.class)
  public void confirmerDemande3() {
    demandeDto.setIdDemande(2);
    boolean ret = demandeController.confirmerDemande(demandeDto, mobiliteDto);
  }

  // Test encoder demande
  @Test
  public void encoderDemande1() {
    boolean ret = demandeController.encoderDemande(demandeDto);
    assertTrue(ret);
  }

}
