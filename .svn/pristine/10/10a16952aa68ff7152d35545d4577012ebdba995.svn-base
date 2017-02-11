package objects.controller.models;

import dal.DalServices;
import dao.interfaces.DemandeDao;
import dao.interfaces.DossierDao;
import dao.interfaces.MobiliteDao;
import dao.interfaces.PartenaireDao;
import dao.interfaces.PaysDao;
import dao.interfaces.UtilisateurDao;
import enumere.Etat;
import objects.controller.interfaces.DemandeUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.bizz.MobiliteBizz;
import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.PaysDto;
import objects.interfaces.dto.UtilisateurDto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class DemandeUcControllerImpl implements DemandeUcController {

  private DalServices dal;
  private DemandeDao demandeDao;
  private PartenaireDao partenaireDao;
  private UtilisateurDao utilisateurDao;
  private MobiliteDao mobiliteDao;
  private BizzFactory factory;
  private DossierDao dossierDao;
  private PaysDao paysDao;
  private static final Logger LOG = LogManager.getLogger(DemandeUcController.class);

  /**
   * @param dalServices.
   * @param demandeDao.
   * @param partenaireDao.
   */
  public DemandeUcControllerImpl(BizzFactory factory, DalServices dalServices,
      DemandeDao demandeDao, PartenaireDao partenaireDao, UtilisateurDao utilisateurDao,
      MobiliteDao mobiliteDao, DossierDao dossierDao, PaysDao paysDao) {
    this.dal = dalServices;
    this.demandeDao = demandeDao;
    this.partenaireDao = partenaireDao;
    this.utilisateurDao = utilisateurDao;
    this.mobiliteDao = mobiliteDao;
    this.factory = factory;
    this.dossierDao = dossierDao;
    this.paysDao = paysDao;
  }

  @Override
  public List<DemandeDto> listerDemandes() {
    try {
      dal.start();
      List<DemandeDto> results = demandeDao.selectAll();
      for (int i = 0; i < results.size(); i++) {
        MobiliteDto mobilite = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
        mobilite.setChoix(results.get(i).getId_demande());
        PartenaireDto partenaire = partenaireDao.selectById(results.get(i).getPartenaire());
        results.get(i).setPartenaire(partenaire);
        UtilisateurDto user = utilisateurDao.select(results.get(i).getEtudiant());
        results.get(i).setEtudiant(user);

      }
      Iterator<DemandeDto> iterator = results.iterator();
      while (iterator.hasNext()) {
        DemandeDto demande = iterator.next();
        MobiliteDto mobilite = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
        mobilite.setChoix(demande.getId_demande());
        if (mobiliteDao.selectByChoix(mobilite) != null) {
          iterator.remove();
        }
      }
      dal.close();
      return results;
    } catch (InternalError exec) {
      dal.close();
      LOG.error(exec.getMessage());
      throw exec;
    }
  }

  public boolean confirmerDemande(DemandeDto demande, MobiliteDto mobilite) {
    MobiliteBizz mobizz = (MobiliteBizz) mobilite;
    try {
      dal.start();
      demande = demandeDao.selectById(demande);
      PartenaireDto partenaire = partenaireDao.selectById(demande.getPartenaire());
      PaysDto pays = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
      pays.setCodeIso(partenaire.getAdresse().getPays());
      partenaire.setPays(paysDao.selectById(pays));
      mobizz.setPartenaire(partenaire);
      mobizz.setChoix(demande.getId_demande());
      mobizz.setEtudiant(demande.getEtudiant());
      mobizz.setEtat(Etat.EN_PREPARATION);
      mobizz.setPeriode(demande.getPeriode());
      mobizz.setTypeDemande(demande.getType());

      if (mobizz.readyInsert() && mobizz.checkDate() && mobizz.checkParam()
          && mobilite.getDateDebut().getYear() >= demande.getAnneeAcademique()
          && mobilite.getDateFin().getYear() >= demande.getAnneeAcademique()) {
        mobiliteDao.insert(mobilite);
        mobilite = mobiliteDao.selectByChoix(mobilite);
        DossierDto dossier = (DossierDto) factory.getDto(DossierDto.class.getSimpleName());
        dossier.setMobilite(mobilite);
        dossierDao.insert(dossier);
        dal.commit();
        dal.close();
        return true;
      } else {
        return false;
      }
    } catch (InternalError e) {
      dal.rollBack();
      dal.close();
      LOG.error(e.getMessage());
      throw new InternalError();
    }
  }

  @Override
  public DemandeDto donnerDemandesAvecId(DemandeDto demandeParam) {
    DemandeDto demande = demandeDao.selectById(demandeParam);
    return demande;
  }

  @Override
  public boolean supprimerDemande(DemandeDto demandeParam) {
    try {
      dal.start();
      DemandeDto demande = demandeDao.selectById(demandeParam);
      if (demande == null) {
        return false;
      }
      demandeDao.delete(demandeParam);
      dal.commit();
      dal.close();
      return true;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dal.rollBack();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dal.rollBack();
    }
    dal.close();
    return false;

  }

  @Override
  public boolean encoderDemande(DemandeDto demande) {
    try {
      dal.start();
      demandeDao.insert(demande);
      dal.commit();
      dal.close();
      return true;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dal.rollBack();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dal.rollBack();
    }
    dal.close();
    return false;
  }

  @Override
  public List<DemandeDto> listerDemandesParUser(String pseudo) {
    try {
      List<DemandeDto> results = demandeDao.selectByUser(pseudo);
      for (int i = 0; i < results.size(); i++) {
        PartenaireDto partenaire = partenaireDao.selectById(results.get(i).getPartenaire());
        results.get(i).setPartenaire(partenaire);
      }
      return results;
    } catch (InternalError exec) {
      LOG.error(exec.getLocalizedMessage());
      throw exec;
    }
  }

  @Override
  public List<DemandeDto> listerDemandesParUserFull(UtilisateurDto user) {
    try {
      List<DemandeDto> results = demandeDao.selectAllByUser(user);
      if (results != null) {
        for (DemandeDto demande : results) {
          demande.setPartenaire(partenaireDao.selectById(demande.getPartenaire()));
        }
      }
      return results;
    } catch (InternalError exe) {
      LOG.error(exe.getLocalizedMessage());
      throw new InternalError();
    }
  }

}
