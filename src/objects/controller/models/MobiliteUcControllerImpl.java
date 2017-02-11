package objects.controller.models;

import dal.DalServices;
import dao.interfaces.MobiliteDao;
import dao.interfaces.PartenaireDao;
import dao.interfaces.UtilisateurDao;
import objects.controller.interfaces.DossierUcController;
import objects.controller.interfaces.MobiliteUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import util.Comparaison;

import java.sql.SQLException;
import java.util.List;

public class MobiliteUcControllerImpl implements MobiliteUcController {
  private DalServices dal;
  private MobiliteDao mobiliteDao;
  private PartenaireDao partenaireDao;
  private UtilisateurDao utilisateurDao;
  private DossierUcController dossierUc;
  private BizzFactory factory;


  /**
   * @param dal.
   * @param mobiliteDao.
   * @param partenaireDao.
   */
  public MobiliteUcControllerImpl(BizzFactory fatory, DalServices dal, MobiliteDao mobiliteDao,
      PartenaireDao partenaireDao, UtilisateurDao utilisateurDao, DossierUcController dossierUc) {
    this.dal = dal;
    this.mobiliteDao = mobiliteDao;
    this.partenaireDao = partenaireDao;
    this.utilisateurDao = utilisateurDao;
    this.dossierUc = dossierUc;
    this.factory = fatory;
  }

  @Override
  public List<MobiliteDto> getMobilites(UtilisateurDto utilisateur) {
    List<MobiliteDto> mobilites = mobiliteDao.selectParUtilisateur(utilisateur);
    if (mobilites != null) {
      for (MobiliteDto mobilite : mobilites) {
        mobilite.setPartenaire(partenaireDao.selectById(mobilite.getPartenaire()));
        DossierDto dossier = (DossierDto) factory.getDto(DossierDto.class.getSimpleName());
        dossier.setMobilite(mobilite);
        dossier = dossierUc.getDossier(dossier);
      }
    }
    return mobilites;
  }

  @Override
  public List<MobiliteDto> getAllMobilites() {
    return mobiliteDao.selectAll();
  }

  @Override
  public MobiliteDto getMobiliteById(MobiliteDto mobi) {
    return mobiliteDao.selectById(mobi);
  }

  @Override
  public void updateMobi(MobiliteDto mobi) throws SQLException {

    mobiliteDao.update(mobi);

  }

  @Override
  public MobiliteDto getMobilite(UtilisateurDto utilisateur) {
    // TODO Auto-generated method stub
    return mobiliteDao.selectByUser(utilisateur);
  }

  @Override
  public void changeEtatMobilite(MobiliteDto mobilite, String etat) {
    MobiliteDto temp = getMobiliteById(mobilite);
    mobilite.setEtat(Comparaison.compareEtat(etat));
    mobiliteDao.updateEtat(mobilite);
  }

  @Override
  public List<MobiliteDto> getAllMobilitesPaiementsDemandes() {
    List<MobiliteDto> liste = mobiliteDao.getAllMobilitesPaiementDemande();
    for (int i = 0; i < liste.size(); i++) {
      UtilisateurDto user = liste.get(i).getEtudiant();
      user = utilisateurDao.select(user);
      liste.get(i).setEtudiant(user);
      PartenaireDto partenaire = liste.get(i).getPartenaire();
      partenaire = partenaireDao.selectById(partenaire);
      liste.get(i).setPartenaire(partenaire);
    }
    return liste;
  }

  @Override
  public void updateAnnulation(MobiliteDto mobi) {
    mobiliteDao.updateAnnulation(mobi);

  }
}
