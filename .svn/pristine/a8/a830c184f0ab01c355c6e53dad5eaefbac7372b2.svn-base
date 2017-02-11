package objects.controller.models;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dal.DalServices;
import dao.interfaces.MobiliteDao;
import dao.interfaces.PartenaireDao;
import dao.interfaces.PaysDao;
import dao.interfaces.UtilisateurDao;
import objects.controller.interfaces.RechercheUcController;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.PaysDto;
import objects.interfaces.dto.UtilisateurDto;

public class RechercheUcControllerImpl implements RechercheUcController {

  private DalServices dalServices;
  private PartenaireDao partenaireDao;
  private UtilisateurDao utilisateurDao;
  private MobiliteDao mobiliteDao;
  private PaysDao paysDao;
  static final Logger LOG = LogManager.getLogger(RechercheUcController.class);

  /**
   * @param dalServices.
   * @param partenaireDao.
   * @param utilisateurDao.
   * @param mobiliteDao.
   */
  public RechercheUcControllerImpl(DalServices dalServices, PartenaireDao partenaireDao,
      UtilisateurDao utilisateurDao, MobiliteDao mobiliteDao, PaysDao paysDao) {
    this.dalServices = dalServices;
    this.partenaireDao = partenaireDao;
    this.utilisateurDao = utilisateurDao;
    this.mobiliteDao = mobiliteDao;
    this.paysDao = paysDao;
  }

  @Override
  public List<MobiliteDto> effectuerRechercheMobilite() {
    List<MobiliteDto> resultats = mobiliteDao.selectAll();
    for (int i = 0; i < resultats.size(); i++) {
      resultats.get(i).setPartenaire(partenaireDao.selectById(resultats.get(i).getPartenaire()));
      resultats.get(i).setEtudiant(utilisateurDao.select(resultats.get(i).getEtudiant()));
    }
    return resultats;

  }

  @Override
  public PartenaireDto effectuerRecherchePartenaire(PartenaireDto recherche) {
    return partenaireDao.selectByName(recherche);
  }

  @Override
  public List<PartenaireDto> getAllPartenairesDispo() {
    List<PartenaireDto> resultats = partenaireDao.selectPartenairesDispos();
    return resultats;
  }

  @Override
  public List<PartenaireDto> getAllPartenaire() {
    try {
      return partenaireDao.selectAll();
    } catch (InternalError exec) {
      LOG.error(exec.getMessage());
      throw new InternalError();
    }

  }

  @Override
  public List<UtilisateurDto> effectuerRechercheUtilisateurs() {
    List<UtilisateurDto> resultats = utilisateurDao.selectEtudiants();
    return resultats;
  }

  @Override
  public UtilisateurDto selectUserByNames(String nom, String prenom) {
    return utilisateurDao.selectByName(nom, prenom);
  }



  @Override
  public MobiliteDto selectMobByUserAndPart(String user, PartenaireDto partenaire) {
    int idPartenaire = partenaireDao.selectByName(partenaire).getId();
    return mobiliteDao.selectByUserAndPartner(user, idPartenaire);
  }

  @Override
  public List<PaysDto> getPays() {
    return paysDao.selectAll();
  }

  @Override
  public MobiliteDto selectMobByUserAndPart(String user, String string) {
    // TODO Auto-generated method stub
    return null;
  }
}
