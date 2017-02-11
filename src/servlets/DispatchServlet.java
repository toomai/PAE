package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.owlike.genson.Genson;

import enumere.Etat;
import enumere.Status;
import objects.controller.interfaces.DemandeUcController;
import objects.controller.interfaces.DossierUcController;
import objects.controller.interfaces.MobiliteUcController;
import objects.controller.interfaces.PartenaireUcController;
import objects.controller.interfaces.PaysUcController;
import objects.controller.interfaces.RaisonAnnulationUcController;
import objects.controller.interfaces.RechercheUcController;
import objects.controller.interfaces.UtilisateurUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.PaysDto;
import objects.interfaces.dto.RaisonAnnulationDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.DemandeImpl;
import objects.model.PartenaireImpl;
import objects.model.UtilisateurImpl;
import util.Util;

/*
 * Switch a compléter => UC
 */

public class DispatchServlet extends HttpServlet {
  static final Logger LOG = LogManager.getLogger(DispatchServlet.class);
  private Genson genson;
  private UtilisateurUcController control;
  private BizzFactory factory;
  private RechercheUcController recherche;
  private MobiliteUcController mobilite;
  private DemandeUcController demande;
  private PartenaireUcController part;
  private RaisonAnnulationUcController annulation;
  private DossierUcController dossier;
  private PaysUcController pays;
  private static final String JWTSECRET = "A1Rsdf qsdfzerr45 qdsfd";
  JWTSigner signer = new JWTSigner(JWTSECRET);

  /**
   * construceur.
   * 
   * @param control uccControleur.
   * @param factory notre factory business.
   */

  public DispatchServlet(UtilisateurUcController control, RechercheUcController recherche,
      BizzFactory factory, MobiliteUcController mobilite, DemandeUcController demande,
      PartenaireUcController partenaire, DossierUcController dossier,
      RaisonAnnulationUcController annule, PaysUcController pays) {
    this.control = control;
    this.factory = factory;
    this.genson = new Genson();
    this.recherche = recherche;
    this.mobilite = mobilite;
    this.demande = demande;
    this.part = partenaire;
    this.dossier = dossier;
    this.annulation = annule;
    this.pays = pays;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.getOutputStream().write(Files.readAllBytes(Paths.get("webPrivate/header.html")));
    resp.getOutputStream().write(Files.readAllBytes(Paths.get("webPrivate/encoderEtudiant.html")));
    resp.getOutputStream()
        .write(Files.readAllBytes(Paths.get("webPrivate/encoderPartenaire.html")));
    resp.getOutputStream().write(Files.readAllBytes(Paths.get("webPrivate/listerDemandes.html")));
    resp.getOutputStream().write(Files.readAllBytes(Paths.get("webPrivate/rechercher.html")));
    resp.getOutputStream().write(Files.readAllBytes(Paths.get("webPrivate/soumettreDemande.html")));
    resp.getOutputStream().write(Files.readAllBytes(Paths.get("webPrivate/footer.html")));
    resp.getOutputStream().flush();
  }


  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String param = req.getParameter("requete");
    PrintWriter out = resp.getWriter();
    try {
      switch (param) {
        case "authentifie":
          authentifie(req, resp, out);
          break;
        case "inscription":
          inscription(req, resp, out);
          break;
        case "listerDemandes":
          listerDemandes(req, resp, out);
          break;
        case "testPartenaireEtudiant":
          testPartenaireEtudiant(req, resp, out);
          break;
        case "demandeMobi":
          insererDemande(req, resp, out);
          break;
        case "encodEtudiant":
          encodEtudiant(req, resp, out);
          break;
        case "encodPartenaire":
          encodPartenaire(req, resp, out);
          break;
        case "listerPartenaire":
          listerPartenaireParNom(req, resp, out);
          break;
        case "listerEtudiant":
          listerEtudiantParPseudo(req, resp, out);
          break;
        case "chargerEtudiant":
          chargerEtudiant(req, resp, out);
          break;
        case "testConnect":
          verificationConnect(req, resp, out);
          break;
        case "supprimerDemande":
          supprimerDemande(req, resp, out);
          break;
        case "rechercher":
          rechercher(req, resp, out);
          break;
        case "annulerMobilite":
          annulerMobilite(req, resp, out);
          break;
        case "getPartenairesDipo":
          getPartenaireDispo(req, resp, out);
          break;
        case "listerDemandeByEtudiant":
          getListeDemandeByEtudiant(req, resp, out);
          break;
        case "getDossiersEtudiant":
          getDossiersEtudiant(req, resp, out);
          break;
        case "listerPaiements":
          listerPaiements(req, resp, out);
          break;
        case "getSuivi":
          getAllMobilitesEtDossiers(req, resp, out);
          break;
        case "deconnection":
          deconnecter(req, resp, out);
          break;
        case "changerTypeCompte":
          changerTypeCompte(req, resp, out);
          break;
        case "changeEtatMobilite":
          changerEtatMobilite(req, resp, out);
          break;
        case "getAnnulation":
          getAnnulation(req, resp, out);
          break;
        case "encodDemande":
          encodDemande(req, resp, out);
          break;
        case "getPays":
          getPays(req, resp, out);
          break;
        case "confirmerDemande":
          confirmerDemande(req, resp, out);
          break;
        case "supprimerPartenaire":
          supprimerPartenaire(req, resp, out);
          break;
        case "testPartenaire":
          testPartenaire(req, resp, out);
          break;
        case "rehabiliterPartenaire":
          rehabiliterPartenaire(req, resp, out);
          break;
        case "autoCompletion":
          autoCompletion(req, resp, out);
          break;
        default:
          break;
      }
    } catch (InternalError exec) {
      LOG.error(exec.getMessage());
      resp.setStatus(500);
    }
  }

  private void autoCompletion(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    List<PartenaireDto> liste = part.getListePartenairesSupprimes();
    String results = "[";
    for (int i = 0; i < liste.size(); i++) {
      results += genson.serialize(liste.get(i));
      if (i == liste.size() - 1) {
        break;
      }
      results += ",";
    }
    results += "]";
    out.println(results);
    out.flush();
  }

  private void chargerEtudiant(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    UtilisateurDto user = (UtilisateurDto) req.getSession().getAttribute("user");
    user = control.getEtudiant(user);
    System.out.println(genson.serialize(user));
    out.print(genson.serialize(user));
    resp.setStatus(200);
  }

  private void rehabiliterPartenaire(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    int id = Integer.parseInt(req.getParameter("idPart"));
    PartenaireDto partenaire = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    partenaire.setId(id);
    part.rehabiliterPartenaire(partenaire);
    resp.setStatus(200);
  }

  private void testPartenaire(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    String json = req.getParameter("nomLegal");
    PartenaireDto partenaire = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    String nomLegal = genson.deserialize(json, String.class);
    partenaire.setNomLegal(nomLegal);

    if ((partenaire = part.getPartenaire(partenaire)) != null) {
      out.write(genson.serialize(partenaire));
      out.flush();
      resp.setStatus(200);
    } else {
      resp.setStatus(404);
    }
  }

  private void getPartenaireDispo(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    List<PartenaireDto> partenairesDispos = part.getPartenairesDispos();
    out.write(genson.serialize(partenairesDispos));
    out.flush();
    resp.setStatus(200);
  }

  private void supprimerPartenaire(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    int id = genson.deserialize(req.getParameter("idPart"), int.class);
    PartenaireDto partenaire = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    partenaire.setId(id);
    if (part.supprimerPartenaire(partenaire)) {
      resp.setStatus(200);
    } else {
      resp.setStatus(403);
      out.write("Encore lié à une mobilité");
      out.flush();
    }
  }

  private void listerEtudiantParPseudo(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    List<UtilisateurDto> liste = new ArrayList<UtilisateurDto>();
    liste = control.getEtudiants();

    if (!liste.isEmpty()) {
      out.print(genson.serialize(liste));
      out.flush();
      resp.setStatus(200);
    } else {
      resp.setStatus(400);
    }
  }

  private void testPartenaireEtudiant(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    PartenaireDto parte = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    String nom = req.getParameter("data");
    nom = nom.replace("\"", "");
    parte.setNomLegal(nom);
    PartenaireDto partenaire = part.getAjout(parte);
    boolean encodage = parte.getAjoutParEleve();

    if (encodage == true) {

      out.print(true);
      out.flush();
      resp.setStatus(200);

    } else {
      out.print(false);
    }

  }



  private void confirmerDemande(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    int idDemande = genson.deserialize(req.getParameter("idDemande"), int.class);
    LocalDate dateDep = LocalDate.parse(req.getParameter("dateDep"));
    LocalDate dateRet = LocalDate.parse(req.getParameter("dateRet"));

    MobiliteDto mobilite = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    DemandeDto demandedto = (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());
    demandedto.setIdDemande(idDemande);
    mobilite.setDateDebut(dateDep);
    mobilite.setDateFin(dateRet);

    if (demande.confirmerDemande(demandedto, mobilite)) {
      resp.setStatus(200);
    } else {
      resp.setStatus(500);
      out.write("Confirmation ratée, tous les champs sont ils corrects ?");
      out.flush();
    }
  }


  private void getListeDemandeByEtudiant(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {

    UtilisateurDto utilisateur = (UtilisateurDto) req.getSession().getAttribute("user");
    List<DemandeDto> listeDem = demande.listerDemandesParUserFull(utilisateur);

    String result = "[";
    for (int i = 0; i < listeDem.size(); i++) {
      result += genson.serialize(listeDem.get(i));
      if (i == listeDem.size() - 1) {
        break;
      }
      result += ",";
    }
    result += "]";
    resp.setStatus(200);
    out.println(result);
    out.flush();
  }


  private void encodDemande(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    HttpSession session = req.getSession();
    DemandeDto dem = genson.deserialize(req.getParameter("form"), DemandeImpl.class);
    PartenaireDto parte = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    String newPart = req.getParameter("newPart");
    parte.setNomLegal(req.getParameter("part"));
    parte = part.getPartenaire(parte);
    dem.setPartenaire(parte);
    UtilisateurDto user = (UtilisateurDto) session.getAttribute("user");
    if ((user.getStatus() + "").equals("ETUDIANT")) {
      dem.setEtudiant(user);
    } else {
      String pseudo = req.getParameter("etud");
      UtilisateurDto etudiant =
          (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
      etudiant.setPseudo(pseudo);
      etudiant = control.getEtudiant(etudiant);
      dem.setEtudiant(etudiant);
    }
    dem.setDateIntroduction(LocalDate.now());
    boolean rep = demande.encoderDemande(dem);
    if (rep) {
      resp.setStatus(200);
    } else {
      resp.setStatus(400);
    }
  }


  private void supprimerDemande(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    DemandeDto dem = (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());
    dem.setIdDemande(Integer.parseInt(req.getParameter("id")));
    boolean rep = demande.supprimerDemande(dem);
    if (rep) {
      resp.setStatus(200);
    } else {
      resp.setStatus(400);
    }
  }

  private void getPays(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    List<PaysDto> liste = recherche.getPays();
    String results = "[";
    for (int i = 0; i < liste.size(); i++) {
      results += genson.serialize(liste.get(i));
      if (i == liste.size() - 1) {
        break;
      }
      results += ",";
    }
    results += "]";
    out.println(results);
    out.flush();

  }

  private void getAnnulation(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    List<RaisonAnnulationDto> liste = annulation.selectAll();
    String results = "[";
    for (int i = 0; i < liste.size(); i++) {
      results += genson.serialize(liste.get(i));
      if (i == liste.size() - 1) {
        break;
      }
      results += ",";
    }
    results += "]";
    out.println(results);
    out.flush();

  }

  /*
   * private void listerPaiements(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
   * { List<DossierDto> liste = dossier.getAllPaiements(); String result = "["; for (int i = 0; i <
   * liste.size(); i++) { result += genson.serialize(liste.get(i)); if (i == liste.size() - 1) {
   * break; } result += ","; } result += "]"; try {out.println(result); out.flush(); } catch
   * (IOException e) { e.printStackTrace(); } }
   */
  private void changerEtatMobilite(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    String para = req.getParameter("id");
    int id = Integer.parseInt(para);
    String etats = req.getParameter("donnees");
    List<String> listeEtats = genson.deserialize(etats, List.class);
    MobiliteDto mobi = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    mobi.setId(id);
    mobi = mobilite.getMobiliteById(mobi);
    for (int i = 0; i < listeEtats.size(); i++) {
      switch (listeEtats.get(i)) {
        case "EN_PREPARATION":
          mobilite.changeEtatMobilite(mobi, "A_PAYER");
          dossier.documentsAuDepartCompletes(mobi);
          break;
        case "A_PAYER":
          if (mobi.getEtat() != Etat.EN_PREPARATION) {
            resp.setStatus(500);
            break;
          }
          mobilite.changeEtatMobilite(mobi, listeEtats.get(i));
          dossier.documentsAuDepartCompletes(mobi);
          break;
        case "documentsRetours":
          if (!dossier.getDossierParMobi(mobi).getConvention()) {
            resp.setStatus(500);
            break;
          }
          dossier.documentsAuRetourCompletes(mobi);
          break;
        case "SOLDE":
          if (mobi.getEtat() != Etat.A_PAYER) {
            resp.setStatus(500);
            break;
          }
          dossier.documentsAuRetourCompletes(mobi);
          mobilite.changeEtatMobilite(mobi, listeEtats.get(i));
          break;
        case "PAYEE":
          if (mobi.getEtat() != Etat.SOLDE) {
            resp.setStatus(500);
            break;
          }
          mobilite.changeEtatMobilite(mobi, listeEtats.get(i));
          break;
        case "donneesEncodees":
          dossier.donneesEncodeesDansLogiciels(mobi);
          break;
        default:
          break;
      }
    }
    if (resp.getStatus() != 500) {
      resp.setStatus(200);
      out.println("réussite");
      out.flush();
    }
  }

  /*
   * private void listerPaiements(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
   * { List<DossierDto> liste = dossier.getAllPaiements(); String result = "["; for (int i = 0; i <
   * liste.size(); i++) { result += genson.serialize(liste.get(i)); if (i == liste.size() - 1) {
   * break; } result += ","; } result += "]"; try {out.println(result); out.flush(); } catch
   * (IOException e) { e.printStackTrace(); } }
   */

  private void listerPaiements(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    List<MobiliteDto> liste = mobilite.getAllMobilitesPaiementsDemandes();
    String resultats = "[";
    for (int i = 0; i < liste.size(); i++) {
      resultats += genson.serialize(liste.get(i));
      if (i == liste.size() - 1) {
        break;
      }
      resultats += ",";
    }
    resultats += "]";
    out.println(resultats);
    out.flush();
  }

  private void changerTypeCompte(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    String pseudo = req.getParameter("pseudo");
    String typeCompte = req.getParameter("type");
    String status = "";
    switch (typeCompte) {
      case "Etudiant":
        status = "ETUDIANT";
        break;
      case "Professeur":
        status = "PROFESSEUR";
        break;
      default:
        System.out.println("erreur typeCompte");
        break;
    }
    UtilisateurDto utilisateur =
        (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    utilisateur.setPseudo(pseudo);
    control.changerTypeCompte(utilisateur, status);
    // out.flush(); out.println("reussite");
  }

  private void deconnecter(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    req.getSession().setAttribute("user", null);
    if (req.getCookies() != null) {
      for (int i = 0; i < req.getCookies().length; i++) {
        if (req.getCookies()[i].getName().equals("user")) {
          req.getCookies()[i].setMaxAge(0);;
          resp.setStatus(200);
          break;
        }
      }
    }
  }

  private void getAllMobilitesEtDossiers(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    List<DossierDto> liste = dossier.selectAll();
    String result = "[";
    if (liste != null) {
      for (int i = 0; i < liste.size(); i++) {
        result += genson.serialize(liste.get(i));
        if (i == liste.size() - 1) {
          break;
        }
        result += ",";
      }
    }
    result += "]";
    out.println(result);
    out.flush();

  }

  //
  // private void getSuivi(HttpServletRequest req, HttpServletResponse resp,
  // PrintWriter out) throws IOException {
  // List<UtilisateurDto> users = control.getEtudiants();
  // String resultats = "[";
  // for (int i = 0; i < users.size(); i++) {
  // if (users.size() - 1 == i)
  // resultats += genson.serialize(users.get(i));
  // else
  // resultats += genson.serialize(users.get(i)) + ", ";
  // List<MobiliteDto> mobilites = mobilite.getMobilites(users.get(i));
  // if (mobilites != null) {
  // if (mobilites.size() - 1 == i)
  // resultats += genson.serialize(mobilites);
  // else
  // resultats += genson.serialize(mobilites) + ", ";
  // for (int j = 0; j < mobilites.size(); j++) {
  // List<DossierDto> dossiers = dossier
  // .getDossierParMobi(mobilites.get(j));
  // if (dossiers != null) {
  // if (mobilites.size() - 1 == i)
  // resultats += genson.serialize(dossiers);
  // else
  // resultats += genson.serialize(dossiers) + ", ";
  // }
  // }
  // }
  // }
  // resultats += "]";
  // System.out.println(resultats);
  // out.print(resultats);
  // out.flush();
  // }

  private void getSuivi(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
      throws IOException {
    List<UtilisateurDto> users = control.getEtudiants();
    String resultats = "[";
    for (int i = 0; i < users.size(); i++) {
      if (users.size() - 1 == i) {
        resultats += genson.serialize(users.get(i));
      } else {
        resultats += genson.serialize(users.get(i)) + ", ";
      }
      List<MobiliteDto> mobilites = mobilite.getMobilites(users.get(i));
      if (mobilites != null) {
        if (mobilites.size() - 1 == i) {
          resultats += genson.serialize(mobilites);
        } else {
          resultats += genson.serialize(mobilites) + ", ";
        }
        for (int j = 0; j < mobilites.size(); j++) {
          DossierDto dossiers = dossier.getDossierParMobi(mobilites.get(j));
          if (dossiers != null) {
            if (mobilites.size() - 1 == i) {
              resultats += genson.serialize(dossiers);
            } else {
              resultats += genson.serialize(dossiers) + ", ";
            }
          }
        }
      }
    }
    resultats += "]";
    out.print(resultats);
    out.flush();
  }



  private void getDossiersEtudiant(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) {
    UtilisateurDto utilisateur = (UtilisateurDto) req.getSession().getAttribute("user");
    List<DossierDto> dossiers = dossier.getDossiersParEtudiant(utilisateur);
    for (DossierDto dossier : dossiers) {
      genson.serialize(dossier);
    }
    resp.setStatus(200);
    out.write(genson.serialize(dossiers));
    out.flush();
  }



  private void annulerMobilite(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {

    String idMobiliteJson = req.getParameter("mobilite");

    int id = genson.deserialize(idMobiliteJson, int.class);

    MobiliteDto mobi = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    mobi.setId(id);
    MobiliteDto mobiliteAAnnule = mobilite.getMobiliteById(mobi);

    mobiliteAAnnule.setEtat(Etat.ANNULE);


    RaisonAnnulationDto annulationMotif =
        (RaisonAnnulationDto) factory.getDto(RaisonAnnulationDto.class.getSimpleName());
    RaisonAnnulationDto annulation2 =
        (RaisonAnnulationDto) factory.getDto(RaisonAnnulationDto.class.getSimpleName());

    String oldRaison = req.getParameter("oldRaison");
    String newRaison = req.getParameter("newRaison");

    if (newRaison.equals(null)) {
      annulationMotif.setRaison(oldRaison);
      annulation2 = annulation.selectRaison(annulationMotif);
    } else {

      annulationMotif.setRaison(newRaison);
      annulation2 = annulation.selectRaison(annulationMotif);
      if (annulation2.equals(null)) {
        annulation.encoderAnnulation(annulationMotif);
        annulation2 = annulation.selectRaison(annulationMotif);
      }

    }
    mobiliteAAnnule.setAnnulationId(annulation2.getId());
    mobilite.updateAnnulation(mobiliteAAnnule);


    out.flush();
    resp.setStatus(200);

  }

  // ////////////////////////////////////////////////////////////////////////////////////
  // ////////////////////////////////////////////////////////////////////////////////////
  // /////////////////////////INSERTION//
  // DONNEES/////////////////////////////////////////
  // /////////////////////////////////////////////////////////////////////////////

  /*
   * @TODO: bon code de status
   */

  private void listerDemandes(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
      throws IOException {
    List<DemandeDto> liste = demande.listerDemandes();
    String results = "[";
    for (int i = 0; i < liste.size(); i++) {
      results += genson.serialize(liste.get(i));
      if (i == liste.size() - 1) {
        break;
      }
      results += ",";
    }
    results += "]";
    resp.setStatus(200);
    out.print(results);
    out.flush();
  }

  private void rechercher(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
      throws IOException {
    String typeRecherche = req.getParameter("typeRecherche");
    String resultats = "[";
    switch (typeRecherche) {
      case "Partenaires":
        List<PartenaireDto> liste = recherche.getAllPartenairesDispo();
        for (int i = 0; i < liste.size(); i++) {
          resultats += genson.serialize(liste.get(i));
          if (i == liste.size() - 1) {
            break;
          }
          resultats += ",";
        }
        resultats += "]";
        break;
      case "Étudiants":
        List<UtilisateurDto> listeusers = recherche.effectuerRechercheUtilisateurs();
        for (int i = 0; i < listeusers.size(); i++) {
          resultats += genson.serialize(listeusers.get(i));
          if (i == listeusers.size() - 1) {
            break;
          }
          resultats += ",";
        }
        resultats += "]";
        break;
      case "Mobilités":
        List<MobiliteDto> listerMob = recherche.effectuerRechercheMobilite();
        for (int i = 0; i < listerMob.size(); i++) {
          resultats += genson.serialize(listerMob.get(i));
          if (i == listerMob.size() - 1) {
            break;
          }
          resultats += ",";
        }
        resultats += "]";
        break;
      default:
        break;
    }
    if (!resultats.isEmpty()) {
      out.write(resultats);
      out.flush();
      resp.setStatus(200);
    } else {
      resp.setStatus(4000);
    }
  }

  private void listerPartenaireParNom(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) throws IOException {

    List<PartenaireDto> liste = recherche.getAllPartenaire();

    if (!liste.isEmpty()) {
      out.print(genson.serialize(liste));
      out.flush();
      resp.setStatus(200);
    } else {
      resp.setStatus(400);
    }
  }

  private void insererDemande(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    String json = req.getParameter("info");

    DemandeDto demande = (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());
    demande = genson.deserialize(json, DemandeImpl.class);
    UtilisateurDto user = (UtilisateurDto) req.getSession().getAttribute("user");

    HashMap<String, Object> mapInfo = (HashMap<String, Object>) genson.deserialize(json, Map.class);
    String nomPartenaire = (String) mapInfo.get("partenaire");

    PartenaireDto part = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    part.setNomLegal(nomPartenaire);
    recherche.effectuerRecherchePartenaire(part);

    demande.setEtudiant(user);

    // demande.setType_mobilite(typeMobilite);

    if (control.encoderDemande(demande, part)) {
      resp.setStatus(200);
    } else {
      resp.setStatus(400000);
    }
  }
  // TODO Auto-generated catch block

  private void encodEtudiant(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    String json = req.getParameter("form");
    UtilisateurDto utilisateur =
        (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    utilisateur = genson.deserialize(json, UtilisateurImpl.class);

    LocalDate ts = LocalDate.parse(req.getParameter("date"));

    utilisateur.setDateNaissance(ts);
    utilisateur.setNbrAnneeSuperieur(Integer.parseInt(req.getParameter("nbr")));
    AdresseDto adresse = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
    adresse.setRue(req.getParameter("rue"));
    if (!req.getParameter("num").equals("")) {
      adresse.setNumero(Integer.parseInt(req.getParameter("num")));
    }
    adresse.setCp(req.getParameter("codepostal"));
    adresse.setBoite(req.getParameter("boite"));
    adresse.setRegion(req.getParameter("region"));
    adresse.setVille(req.getParameter("ville"));
    utilisateur.setAdresse(adresse);

    UtilisateurDto user = (UtilisateurDto) req.getSession().getAttribute("user");
    if ((user.getStatus() + "").equals("ETUDIANT")) {
      utilisateur.setPseudo((String) req.getSession().getAttribute("pseudo"));
    } else {
      String pseudo = req.getParameter("etud");
      utilisateur.setPseudo(pseudo);
    }

    boolean con = control.encoderEtudiant((UtilisateurDto) req.getSession().getAttribute("user"),
        utilisateur);

    if (!con) {
      resp.setStatus(400, "Une erreur est survenue");
    } else {
      resp.setStatus(200);
    }
  }

  private void encodPartenaire(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    String json = req.getParameter("form");
    PartenaireDto partenaire = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    partenaire = genson.deserialize(json, PartenaireImpl.class);
    AdresseDto adresse = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
    adresse.setRue(req.getParameter("rue"));

    HttpSession session = req.getSession();
    UtilisateurDto user = (UtilisateurDto) session.getAttribute("user");
    if ((user.getStatus() + "").equals("ETUDIANT")) {
      partenaire.setAjoutParEleve(true);
    } else {
      partenaire.setAjoutParEleve(false);
    }
    if (!req.getParameter("num").equals("")) {
      adresse.setNumero(Integer.parseInt(req.getParameter("num")));
    }

    adresse.setCp(req.getParameter("codePostal"));
    adresse.setBoite(req.getParameter("boite"));
    adresse.setRegion(req.getParameter("region"));
    adresse.setVille(req.getParameter("ville"));
    adresse.setCp(req.getParameter("codepostal"));
    PaysDto paysDto = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
    paysDto.setNom(req.getParameter("pays"));
    paysDto = pays.getPays(paysDto);
    adresse.setPays(paysDto.getCodeIso());

    partenaire.setAdresse(adresse);

    boolean con = part.encoderPartenaire(partenaire);

    if (!con) {
      resp.setStatus(400, "Une erreur est survenue");
    } else {
      resp.setStatus(200);
    }
  }

  // //////////////////////////////////////////////////////////////////////////////
  // /////////////////////////////////////////////////////////////////////////////////
  // ///////////////////////////INSCRIPTION ET
  // CONNECTION////////////////////////////////////////////////
  // /////////////////////////////////////////////////////////////////////////////////

  private void verificationConnect(HttpServletRequest req, HttpServletResponse resp,
      PrintWriter out) throws IOException {
    HttpSession session = req.getSession();
    boolean found = false;
    UtilisateurDto user = (UtilisateurDto) session.getAttribute("user");
    if (user != null) {
      out.print(user.getStatus());
      resp.setStatus(200);
    } else {
      if (req.getCookies() != null) {

        for (int i = 0; i < req.getCookies().length; i++) {
          if (req.getCookies()[i].getName().equals("user")) {
            String jwt = req.getCookies()[i].getValue();
            Map<String, Object> decodedPayload;
            try {
              user = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
              decodedPayload = new JWTVerifier(JWTSECRET).verify(jwt);
              String pseudo = (String) decodedPayload.get("pseudo");
              user.setPseudo(pseudo);
              user = control.getEtudiant(user);
            } catch (Throwable exc) {
              exc.printStackTrace();
              req.getCookies()[i] = null;
              resp.setStatus(400);
            }
            out.print(genson.serialize(user.getStatus()));
            resp.setStatus(200);
            break;
          }
        }
        resp.setStatus(400);

      } else {
        resp.setStatus(400);

      }

    }
  }

  private void authentifie(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
      throws IOException {
    UtilisateurDto utilFrontEnd =
        (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    utilFrontEnd.setMdp(req.getParameter("mdp"));
    utilFrontEnd.setPseudo(req.getParameter("pseudo"));
    UtilisateurDto util = control.authentifie(utilFrontEnd);
    if (util == null) {
      out.print("Problème d'authentification");
      resp.setStatus(400);
    } else {
      out.print(genson.serialize(utilFrontEnd));
      resp.addCookie(createCookie(util, req, resp));
      resp.setStatus(200);
    }
  }

  private void inscription(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
      throws IOException {
    String json = req.getParameter("form");
    UtilisateurDto utilisateur =
        (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    utilisateur = genson.deserialize(json, UtilisateurImpl.class);
    if (!Util.checkEmail(utilisateur.getEmail())) {
      out.print(
          "Veuillez entrer une adresse mail sous le format suivant: adresse@domaine.com ou adresse@adresse.domaine.com");
      resp.setStatus(400);
    } else {
      if (control.emptyOrNot()) {
        utilisateur.setStatus(Status.PROFESSEUR);
      } else {
        utilisateur.setStatus(Status.ETUDIANT);
      }

      utilisateur = control.identifie(utilisateur);
      if (utilisateur == null) {
        out.println("L'utilisateur existe déjà");
        resp.setStatus(400);
      } else {
        resp.addCookie(createCookie(utilisateur, req, resp));
        resp.setStatus(200);
      }
    }
  }

  private Cookie createCookie(UtilisateurDto utilisateur, HttpServletRequest req,
      HttpServletResponse resp) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pseudo", utilisateur.getPseudo());
    map.put("user", utilisateur);
    String jwt = signer.sign(map);
    Cookie cookie = new Cookie("user", jwt);
    cookie.setComment("user-jwt");
    cookie.setMaxAge(60 * 24);
    HttpSession session = req.getSession();
    session.setMaxInactiveInterval(3600);
    session.setAttribute("pseudo", utilisateur.getPseudo());
    session.setAttribute("user", utilisateur);
    return cookie;
  }
}
