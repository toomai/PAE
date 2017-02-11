package objects.controller.models;

import dal.DalServices;
import dao.interfaces.DemandeDao;
import dao.interfaces.PartenaireDao;
import dao.interfaces.UtilisateurDao;
import enumere.Status;
import objects.controller.interfaces.UtilisateurUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.Authentification;
import util.Comparaison;

import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class UtilisateurUcControllerImpl implements UtilisateurUcController {
  private BizzFactory factory;
  private UtilisateurDao utilisateurDao;
  private DalServices dalServices;
  private DemandeDao demandeDao;
  private PartenaireDao partenaireDao;

  /**
   * @param utilisateurDao.
   * @param demandeDao.
   * @param dalServices.
   * @param partenaireDao.
   */
  public UtilisateurUcControllerImpl() {
    factory = BizzFactory.INSTANCE;
    for (Field fil : this.getClass().getDeclaredFields()) {
      if (!fil.getType().getSimpleName().contains("Factory")) {
        System.out.println("C'est vide.");
      }
    }
  }

  /**
   * @param factory Recois la factory.
   * @param utilisateurDao Recois un utilisateurDao.
   * @param demandeDao Recois une demandeDao.
   * @param dalServices Recois une dalServices.
   * @param partenaireDao Recois un partenaireDao.
   */
  public UtilisateurUcControllerImpl(BizzFactory factory, UtilisateurDao utilisateurDao,
      DemandeDao demandeDao, DalServices dalServices, PartenaireDao partenaireDao) {
    /*
     * Field[] fi = this.getClass().getDeclaredFields(); for (Field fil : fi) { if
     * (fil.getType().getSimpleName().contains("Dao")) {
     * 
     * } }
     */
    this.factory = factory;
    this.utilisateurDao = utilisateurDao;
    this.demandeDao = demandeDao;
    this.dalServices = dalServices;
    this.partenaireDao = partenaireDao;

  }

  /*
   * static { try { Constructor c1 =
   * Class.forName("dal.UtilisateurDAOImpl").getDeclaredConstructor(); c1.setAccessible(true);
   * utilisateurDao = (UtilisateurDAO) c1.newInstance(); Constructor c2 =
   * Class.forName("dal.DALServicesImpl").getDeclaredConstructor(); c2.setAccessible(true);
   * dalServices = (DALServices) c2.newInstance(); } catch (Throwable e) { throw new
   * RuntimeException(); } }
   */

  /**
   * authentifie un UtilisateurDto deja present dans la DB.
   */
  public UtilisateurDto authentifie(UtilisateurDto utilFrontEnd) {
    UtilisateurDto ret = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    ret.setPseudo(utilFrontEnd.getPseudo());
    try {
      dalServices.start();
      ret = utilisateurDao.select(ret);
      dalServices.close();
      if (ret == null) {
        return null;
      }
      if (Authentification.validerMdp(utilFrontEnd.getMdp(), ret.getMdp())) {
        ret.setMdp(null);
        return ret;
      }
      return null;
    } catch (InvalidKeySpecException exc) {
      exc.printStackTrace();
    } catch (NoSuchAlgorithmException exc) {
      exc.printStackTrace();
    } catch (InternalError exc) {
      exc.printStackTrace();
      dalServices.rollBack();
    }
    dalServices.close();
    return null;
  }

  /**
   * identifie un Utilisateur dans la DB. =insérer l'utilsateur ds la Db
   */

  public UtilisateurDto identifie(UtilisateurDto utilisateur) {
    try {
      dalServices.start();
      UtilisateurDto utilARet = null;
      if ((utilARet = utilisateurDao.select(utilisateur)) != null) {
        return null;
      }
      if (utilisateur.getPseudo() == null) {
        return null;
      }
      String mdp = Authentification.genererMdp(utilisateur.getMdp());
      utilisateur.setMdp(mdp);
      if (utilisateurDao.insert(utilisateur)) {
        utilisateur.setNumVersion(0);
      }
      dalServices.commit();
      dalServices.close();
      utilisateur.setMdp(null);
      return utilisateur;
    } catch (InternalError exc) {
      System.out.println(exc.getMessage());
      dalServices.rollBack();
      dalServices.close();
      throw new InternalError();
    } catch (NoSuchAlgorithmException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    } catch (InvalidKeySpecException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    }
    dalServices.close();
    return null;
  }

  @Override
  public boolean encoderEtudiant(UtilisateurDto demandeur, UtilisateurDto utilisateur) {
    try {
      dalServices.start();
      boolean ret = utilisateurDao.update(demandeur, utilisateur);
      dalServices.commit();
      dalServices.close();
      return ret;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
    }
    dalServices.close();
    return false;
  }


  @Override
  public boolean encoderDemande(DemandeDto demande, PartenaireDto partenaire) {
    PartenaireDto partenaires = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    partenaires = partenaireDao.selectByName(partenaire);
    demande.setPartenaire(partenaire);
    try {
      dalServices.start();
      this.demandeDao.insert(demande);
      dalServices.commit();
      dalServices.close();
      return true;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
      dalServices.close();
      return false;
    }
  }

  @Override
  public List<UtilisateurDto> getEtudiants() {
    List<UtilisateurDto> utils = new ArrayList<UtilisateurDto>();
    try {
      dalServices.start();
      utils = utilisateurDao.selectEtudiants();
      dalServices.commit();
      dalServices.close();
      return utils;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
      dalServices.close();
      throw new InternalError();
    }
  }

  @Override
  public boolean changerTypeCompte(UtilisateurDto utilisateur, String status) {
    UtilisateurDto user = utilisateurDao.select(utilisateur);
    user.setStatus(Comparaison.compareStatus(status));
    return utilisateurDao.updateStatus(utilisateur);
  }

  @Override
  public List<UtilisateurDto> getEtudiantsPseudo() {
    List<UtilisateurDto> pseudos = new ArrayList<UtilisateurDto>();
    try {
      dalServices.start();
      pseudos = utilisateurDao.selectEtudiants();
      dalServices.commit();
      dalServices.close();
      return pseudos;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
    }
    dalServices.close();
    return null;
  }

  @Override
  public UtilisateurDto getEtudiant(UtilisateurDto etudiant) {
    try {
      dalServices.start();
      etudiant = utilisateurDao.select(etudiant);
      dalServices.commit();
      dalServices.close();
      return etudiant;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
    }
    dalServices.close();
    return null;
  }

  public boolean emptyOrNot() {
    try {
      boolean vide = false;
      dalServices.start();
      vide = utilisateurDao.emptyOrNot();
      dalServices.commit();
      dalServices.close();
      return vide;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
    }
    return false;
  }
}
