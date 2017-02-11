package dao.model;

import dal.DalAccessServices;
import dao.interfaces.RechercheDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RechercheDaoImpl implements RechercheDao {
  DalAccessServices access;
  BizzFactory factory;
  static final String SELECT_DONNEES_PARTENAIRES = "SELECT nom_legal from mobipl.partenaires";
  static final String SELECT_DONNEES_UTILISATEURS = "SELECT nom, prenom from mobipl.utilisateurs";
  static final String RECHERCHE_UTILISATEURS_NOM = "SELECT * from mobipl.utilisateurs where nom=?";
  static final String RECHERCHE_PARTENAIRE_NOM_LEGAL =
      "SELECT * from mobipl.partenaires where nom_legal=?";

  PreparedStatement ps;

  public RechercheDaoImpl(DalAccessServices access) {
    this.access = access;
  }

  @Override
  public List<String> nomPartenaire() throws SQLException {

    List<String> liste = new ArrayList<String>();
    ps = this.access.prepare(SELECT_DONNEES_PARTENAIRES);
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        liste.add(rs.getString(1));
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
    return liste;
  }

  @Override
  public List<String> donneesTable() throws SQLException {
    List<String> liste = new ArrayList<String>();
    ps = this.access.prepare(SELECT_DONNEES_PARTENAIRES);
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        liste.add(rs.getString(1));
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
    ps = this.access.prepare(SELECT_DONNEES_UTILISATEURS);
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        liste.add(rs.getString(1));
        liste.add(rs.getString(2));
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
    return liste;
  }

  @Override
  public List<MobiliteDto> effectuerRechercheMobilite(String recherche) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<PartenaireDto> effectuerRecherchePartenaire(String recherche) throws SQLException {
    List<PartenaireDto> resultats = new ArrayList<PartenaireDto>();
    ps = this.access.prepare(RECHERCHE_PARTENAIRE_NOM_LEGAL);
    ps.setString(1, recherche);
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        PartenaireDto partenaire =
            (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
        partenaire.setNomLegal(rs.getString(1));
        partenaire.setNomAffaire(rs.getString(2));
        partenaire.setNomComplet(rs.getString(3));
        partenaire.setDepartement(rs.getString(4));
        // partenaire.setTypeOrganisation(rs.getString(5));
        partenaire.setNbEmploye(rs.getInt(6));
        AdresseDto adresse = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
        adresse.setRue(rs.getString(7));
        adresse.setNumero(rs.getInt(8));
        adresse.setBoite(rs.getString(9));
        adresse.setPays(rs.getString(10));
        adresse.setRegion(rs.getString(11));
        adresse.setCp(rs.getString(12));
        adresse.setVille(rs.getString(13));
        partenaire.setAdresse(adresse);
        partenaire.setEmail(rs.getString(14));
        partenaire.setSiteWeb(rs.getString(15));
        partenaire.setTelephonne(rs.getString(16));
        partenaire.setAjoutParEleve(rs.getBoolean(17));
        resultats.add(partenaire);
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
    return resultats;
  }



  @Override
  public List<UtilisateurDto> effectuerRechercheUtilisateur(String recherche) throws SQLException {
    List<UtilisateurDto> resultats = new ArrayList<UtilisateurDto>();
    ps = this.access.prepare(RECHERCHE_UTILISATEURS_NOM);
    ps.setString(1, recherche);
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        UtilisateurDto utilisateur =
            (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
        utilisateur.setPseudo(rs.getString(1));
        utilisateur.setMdp(rs.getString(2));
        utilisateur.setNom(rs.getString(3));
        utilisateur.setPrenom(rs.getString(4));
        // utilisateur.setSexe(rs.getString(5));
        // utilisateur.setStatut(rs.getString(6));
        LocalDate date = rs.getDate(7).toLocalDate();
        utilisateur.setDateNaissance(date);
        // utilisateur.setCivilite(rs.getString((8));
        AdresseDto adresse = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
        adresse.setRue(rs.getString(9));
        adresse.setNumero(rs.getInt(10));
        adresse.setBoite(rs.getString(11));
        adresse.setRegion(rs.getString(12));
        adresse.setCp(rs.getString(13));
        adresse.setVille(rs.getString(14));
        utilisateur.setAdresse(adresse);
        utilisateur.setNationalite(rs.getString(15));
        utilisateur.setEmail(rs.getString(16));
        utilisateur.setNbrAnneeSuperieur(rs.getInt(17));
        utilisateur.setNumCompte(rs.getString(18));
        utilisateur.setTitulaire(rs.getString(19));
        utilisateur.setNomBanque(rs.getString(20));
        utilisateur.setBic(rs.getString(21));
        date = rs.getDate(22).toLocalDate();
        utilisateur.setDateInscription(date);
        // utilisateur.setSection(section);
        resultats.add(utilisateur);
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
    return resultats;
  }



}
