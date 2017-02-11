package objects.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import objects.controller.interfaces.DossierUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.PaysDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.BizzFactoryImpl;

import org.junit.Test;

import java.util.List;

public class DossierUcControllerTest {
  BizzFactory factory = new BizzFactoryImpl();
  BizzFactory mockFactory = new MockupFactory();
  PartenaireDto partenaireDto = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
  AdresseDto adresseDto = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
  PaysDto paysDto = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
  MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
  UtilisateurDto utilisateurDto = (UtilisateurDto) factory.getDto(UtilisateurDto.class
      .getSimpleName());
  DossierDto dossierDto = (DossierDto) factory.getDto(DossierDto.class.getSimpleName());
  DossierUcController dossierController = mockFactory.getDossierUcController();

  // test dossierEtudiant pour étudiant existant
  @Test
  public void getDossiersParEtudiant1() {
    utilisateurDto.setPseudo("Lama");
    List<DossierDto> ret = dossierController.getDossiersParEtudiant(utilisateurDto);
    assertNotNull(ret);
  }

  // test dossierEtudiant pour étudiant inexistant
  @Test
  public void getDossiersParEtudiant2() {
    utilisateurDto.setPseudo("Lamax");
    List<DossierDto> ret = dossierController.getDossiersParEtudiant(utilisateurDto);
    assertTrue(ret.isEmpty());
  }

  // Test selectAll
  @Test
  public void selectAll1() {
    List<DossierDto> ret = dossierController.selectAll();
    assertNotNull(ret);
  }

  // Test getDossier existant
  @Test
  public void getDossier1() {
    mobiliteDto.setId(1);
    dossierDto.setMobilite(mobiliteDto);
    DossierDto ret = dossierController.getDossier(dossierDto);
    assertNotNull(ret);
  }

  // Test getDossier inexistant
  @Test
  public void getDossier2() {
    mobiliteDto.setId(2);
    dossierDto.setMobilite(mobiliteDto);
    DossierDto ret = dossierController.getDossier(dossierDto);
    assertNull(ret);
  }

  // Test getDossierparMobi sur mobilite existante
  @Test
  public void getDossierParMobi() {
    mobiliteDto.setId(1);
    DossierDto ret = dossierController.getDossierParMobi(mobiliteDto);
    assertNotNull(ret);
  }

  // Test getDossierparMobi sur mobilite inexistante
  @Test
  public void getDossierParMob2() {
    mobiliteDto.setId(2);
    DossierDto ret = dossierController.getDossierParMobi(mobiliteDto);
    assertNull(ret);
  }

  @Test
  public void documentsAuDepartCompletes() {

    mobiliteDto.setId(2);
    DossierDto ret = dossierController.getDossierParMobi(mobiliteDto);
    assertNull(ret);

  }

  @Test
  public void documentsAuRetourCompletes() {

  }

  @Test
  public void donneesEncodeesDansLogiciels() {

  }

  // TODO documentsAuDepartCompletes
  // TODO documentsAuRetourCompletes
  // TODO donneesEncodeesDansLogiciels

}
