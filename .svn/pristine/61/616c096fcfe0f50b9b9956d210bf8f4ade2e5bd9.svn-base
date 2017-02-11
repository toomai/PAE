package objects.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import enumere.Etat;
import enumere.TypeDemande;
import objects.controller.interfaces.MobiliteUcController;
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

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MobiliteUcControlleurTest {
  BizzFactory factory = new BizzFactoryImpl();
  BizzFactory mockFactory = new MockupFactory();
  PartenaireDto partenaireDto = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
  AdresseDto adresseDto = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
  PaysDto paysDto = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
  MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
  UtilisateurDto utilisateurDto = (UtilisateurDto) factory.getDto(UtilisateurDto.class
      .getSimpleName());
  DemandeDto demandeDto = (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());

  MobiliteUcController mobiliteController = mockFactory.getMobiliteUcController();


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


  @Test
  public void getMobilites() {
    UtilisateurDto utilisateur =
        (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    utilisateur.setPseudo("Lama");
    List<MobiliteDto> liste = mobiliteController.getMobilites(utilisateur);
    assertTrue(!liste.isEmpty());
  }

  @Test
  public void getAllMobilite() {
    List<MobiliteDto> temp = mobiliteController.getAllMobilites();
    assertTrue(temp.isEmpty());
  }

  @Test
  public void getMobiliteById1() {
    MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    mobiliteDto.setId(1);
    assertNotNull(mobiliteController.getMobiliteById(mobiliteDto));
  }

  @Test
  public void getMobiliteById2() {
    MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    mobiliteDto.setId(2);
    assertNull(mobiliteController.getMobiliteById(mobiliteDto));
  }

  @Test
  public void updateMobi() {
    MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    mobiliteDto.setId(1);
    try {
      mobiliteController.updateMobi(mobiliteDto);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertNotNull(mobiliteDto);
  }

  @Test
  public void getMobilite1() {
    UtilisateurDto user = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    user.setPseudo("Lama");
    MobiliteDto mobilite = mobiliteController.getMobilite(user);
    assertNotNull(mobilite);
  }

  @Test
  public void getMobilite2() {
    UtilisateurDto user = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    user.setPseudo("coco");
    MobiliteDto mobilite = mobiliteController.getMobilite(user);
    assertNull(mobilite);
  }

  @Test
  public void changerEtatMobilite() {
    MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    mobiliteDto.setId(1);
    mobiliteDto.setEtat(Etat.EN_PREPARATION);
    mobiliteController.changeEtatMobilite(mobiliteDto, "A_PAYER");
    assertTrue(mobiliteDto.getEtat().equals(Etat.A_PAYER));
  }

  @Test
  public void getAllMobilitePaiementDemande() {
    List<MobiliteDto> liste = mobiliteController.getAllMobilitesPaiementsDemandes();
    assertTrue(liste.isEmpty());
  }

  @Test
  public void updateAnnulation() {
    MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    mobiliteDto.setId(1);
    mobiliteController.updateAnnulation(mobiliteDto);

    assertNotNull(mobiliteDto);
  }
}
