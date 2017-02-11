package objects.controller.models;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dal.DalServices;
import dao.interfaces.DossierDao;
import dao.interfaces.MobiliteDao;
import dao.interfaces.PartenaireDao;
import dao.interfaces.UtilisateurDao;
import objects.controller.interfaces.DossierUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;

public class DossierUcControllerImpl implements DossierUcController {
  private BizzFactory factory;
  private UtilisateurDao utilisateurDao;
  private DalServices dalServices;
  private DossierDao dossierDao;
  private MobiliteDao mobiliteDao;
  private PartenaireDao partenaireDao;
  static final Logger LOG = LogManager.getLogger(DossierUcController.class);

  /**
   * @param utilisateurDao.
   * @param demandeDao.
   * @param dalServices.
   * @param partenaireDao.
   */
  public DossierUcControllerImpl() {
    factory = BizzFactory.INSTANCE;
    for (Field fil : this.getClass().getDeclaredFields()) {
      if (!fil.getType().getSimpleName().contains("Factory")) {
        System.out.println("C'est vide");
      }
    }
  }

  /**
   * @param factory.
   * @param utilisateurDao.
   * @param demandeDao.
   * @param dalServices.
   * @param dossierDao.
   * @param mobiliteDao.
   */
  public DossierUcControllerImpl(BizzFactory factory, UtilisateurDao utilisateurDao,
      PartenaireDao partenaireDao, DalServices dalServices, DossierDao dossierDao,
      MobiliteDao mobiliteDao) {
    this.factory = factory;
    this.utilisateurDao = utilisateurDao;
    this.partenaireDao = partenaireDao;
    this.dalServices = dalServices;
    this.dossierDao = dossierDao;
    this.mobiliteDao = mobiliteDao;

  }

  @Override
  public List<DossierDto> getDossiersParEtudiant(UtilisateurDto utilisateur) {
    try {
      dalServices.start();
      List<MobiliteDto> mobilites = mobiliteDao.selectParUtilisateur(utilisateur);
      List<DossierDto> dossiers = new ArrayList<>();
      if (mobilites != null) {
        for (MobiliteDto mobi : mobilites) {
          DossierDto dossier = (DossierDto) factory.getDto(DossierDto.class.getSimpleName());
          mobi.setPartenaire(partenaireDao.selectById(mobi.getPartenaire()));
          dossier.setMobilite(mobi);
          dossier = dossierDao.select(dossier);
          dossiers.add(dossier);
        }
      }
      dalServices.commit();
      dalServices.close();
      return dossiers;
    } catch (InternalError excep) {
      dalServices.close();
      excep.printStackTrace();
      LOG.error(excep.getMessage());
      throw new InternalError();
    }
  }

  @Override
  public List<DossierDto> selectAll() {
    try {
      List<DossierDto> liste = dossierDao.selectAll();
      if (liste != null) {
        for (int i = 0; i < liste.size(); i++) {
          MobiliteDto mobilite = liste.get(i).getMobilite();
          mobilite = mobiliteDao.selectById(mobilite);
          UtilisateurDto user = utilisateurDao.select(mobilite.getEtudiant());
          mobilite.setEtudiant(user);
          PartenaireDto partenaire = partenaireDao.selectById(mobilite.getPartenaire());
          mobilite.setPartenaire(partenaire);
          liste.get(i).setMobilite(mobilite);
        }
      }
      return liste;
    } catch (InternalError excep) {
      LOG.error(excep.getLocalizedMessage());
      throw new InternalError();
    }
  }

  @Override
  public DossierDto getDossier(DossierDto dossier) {
    try {
      dalServices.start();
      dossier = dossierDao.select(dossier);
      dalServices.commit();
      dalServices.close();
      return dossier;
    } catch (InternalError excep) {
      LOG.error(excep.getMessage());
      throw new InternalError();
    }
  }

  @Override
  public DossierDto getDossierParMobi(MobiliteDto mobilite) {
    try {
      dalServices.start();
      DossierDto dossiers = dossierDao.selectByMobi(mobilite);
      dalServices.commit();
      dalServices.close();
      return dossiers;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
      dalServices.close();
      throw new InternalError();
    }
  }

  @Override
  public void documentsAuDepartCompletes(MobiliteDto mobilite) {
    DossierDto dossier = getDossierParMobi(mobilite);
    dossier.setConvention(true);
    dossier.setCharteEtudiant(true);
    dossier.setDocumentEngagement(true);
    dossier.setContratBourse(true);
    dossier.setPreuvePassageLinguistiqueAvant(true);
    dossier.setAttestationSejour(false);
    dossier.setPreuvePassageLinguistiqueApres(false);
    dossier.setRapportFinal(false);
    dossier.setReleveNote(false);
    dossier.setCertificatStage(false);
    dossierDao.update(dossier);
  }

  @Override
  public void documentsAuRetourCompletes(MobiliteDto mobilite) {
    DossierDto dossier = getDossierParMobi(mobilite);
    dossier.setPreuvePassageLinguistiqueApres(true);
    dossier.setRapportFinal(true);
    dossier.setAttestationSejour(true);
    dossier.setCertificatStage(true);
    dossier.setReleveNote(true);
    dossier.setConvention(true);
    dossierDao.update(dossier);
  }

  @Override
  public void donneesEncodeesDansLogiciels(MobiliteDto mobilite) {
    DossierDto dossier = getDossierParMobi(mobilite);
    dossier.setEncodageMobi(true);
    dossier.setEncodageMobilityTool(true);
    dossier.setEncodageProEco(true);
    dossierDao.update(dossier);
  }
}
