package dao.model;

import dal.DalAccessServices;
import dao.interfaces.DossierDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DossierDaoImpl implements DossierDao {
  static final String INSERT_STATEMENT = "INSERT INTO mobipl.dossiers"
      + "(mobilite, contrat_de_bourse, convention,charte_etudiant,"
      + "preuve_passage_linguistique_avant,document_engagement, attestation_sejour,"
      + "releve_de_notes, certificat_stage,rapport_final,"
      + "preuve_passage_linguistique_apres, encode_mobi,"
      + "encode_pro_eco, encode_mobility_tool, version)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
  static final String SELECT_ALL_STATEMENT =
      "SELECT d.mobilite, d.contrat_de_bourse, d.convention, d.charte_etudiant,"
          + "d.preuve_passage_linguistique_avant, d.document_engagement,"
          + "d.attestation_sejour, d.releve_de_notes, d.certificat_stage,"
          + "d.rapport_final, d.preuve_passage_linguistique_apres,"
          + "d.encode_mobi, d.encode_pro_eco, d.encode_mobility_tool FROM mobipl.dossiers d";
  static final String SELECT_STATEMENT =
      "SELECT  d.contrat_de_bourse, d.convention, d.charte_etudiant,"
          + "d.preuve_passage_linguistique_avant, d.document_engagement,"
          + "d.attestation_sejour, d.releve_de_notes, d.certificat_stage,"
          + "d.rapport_final, d.preuve_passage_linguistique_apres,"
          + "d.encode_mobi, d.encode_pro_eco, d.encode_mobility_tool, d.version "
          + "FROM mobipl.dossiers d WHERE d.mobilite = ?";
  static final String UPDATE_STATEMENT = "UPDATE mobipl.dossiers SET contrat_de_bourse = ?,"
      + "convention = ?, charte_etudiant = ?," + "preuve_passage_linguistique_avant = ?,"
      + "document_engagement = ?, attestation_sejour = ?,"
      + "releve_de_notes = ?, certificat_stage = ?,"
      + "rapport_final = ?, preuve_passage_linguistique_apres = ?,"
      + "encode_mobi = ?, encode_pro_eco = ?, encode_mobility_tool =?, version = ?"
      + "WHERE mobilite = ? AND version = ?";
  static final String SELECT_MOBI_STATEMENT =
      "SELECT d.mobilite, d.contrat_de_bourse, d.convention, d.charte_etudiant"
          + ", d.preuve_passage_linguistique_avant, d.document_engagement, d.attestation_sejour"
          + ", d.releve_de_notes,d.certificat_stage, d.rapport_final,"
          + " d.preuve_passage_linguistique_apres,d.encode_mobi, d.encode_pro_eco, "
          + "d.encode_mobility_tool,d.version  FROM mobipl.dossiers d WHERE d.mobilite=?";
  static final String DELETE_STATEMENT = "DELETE FROM mobipl.dossiers WHERE mobilite = ?";
  private DalAccessServices access;
  private BizzFactory factory;

  public DossierDaoImpl(DalAccessServices access, BizzFactory factory) {
    this.access = access;
    this.factory = factory;
  }

  @Override
  public void insert(DossierDto dossier) {
    PreparedStatement ps = this.access.prepare(INSERT_STATEMENT);
    try {
      ps.setInt(1, dossier.getMobilite().getId());
      ps.setBoolean(2, dossier.getContratBourse());
      ps.setBoolean(3, dossier.getConvention());
      ps.setBoolean(4, dossier.getCharteEtudiant());
      ps.setBoolean(5, dossier.getPreuvePassageLinguistiqueAvant());
      ps.setBoolean(6, dossier.getDocumentEngagement());
      ps.setBoolean(7, dossier.getAttestationSejour());
      ps.setBoolean(8, dossier.getReleveNote());
      ps.setBoolean(9, dossier.getCertificatStage());
      ps.setBoolean(10, dossier.getRapportFinal());
      ps.setBoolean(11, dossier.getPreuvePassageLinguistiqueApres());
      ps.setBoolean(12, dossier.getEncodageMobi());
      ps.setBoolean(13, dossier.getEncodageProEco());
      ps.setBoolean(14, dossier.getEncodageMobilityTool());
      ps.execute();
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<DossierDto> selectAll() {
    PreparedStatement ps = this.access.prepare(SELECT_ALL_STATEMENT);
    List<DossierDto> listeARet = new ArrayList<DossierDto>();
    try {
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        do {
          DossierDto dossier = (DossierDto) factory.getDto(DossierDto.class.getSimpleName());
          MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
          mobiliteDto.setId(rs.getInt(1));
          dossier.setMobilite(mobiliteDto);
          dossier.setContratBourse(rs.getBoolean(2));
          dossier.setConvention(rs.getBoolean(3));
          dossier.setCharteEtudiant(rs.getBoolean(4));
          dossier.setPreuvePassageLinguistiqueAvant(rs.getBoolean(5));
          dossier.setDocumentEngagement(rs.getBoolean(6));
          dossier.setAttestationSejour(rs.getBoolean(7));
          dossier.setReleveNote(rs.getBoolean(8));
          dossier.setCertificatStage(rs.getBoolean(9));
          dossier.setRapportFinal(rs.getBoolean(10));
          dossier.setPreuvePassageLinguistiqueApres(rs.getBoolean(11));
          dossier.setEncodageMobi(rs.getBoolean(12));
          dossier.setEncodageProEco(rs.getBoolean(13));
          dossier.setEncodageMobilityTool(rs.getBoolean(14));
          listeARet.add(dossier);
        } while (rs.next());
        return listeARet;
      } else {
        return null;
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public DossierDto select(DossierDto dossier) {
    PreparedStatement ps = this.access.prepare(SELECT_STATEMENT);
    try {
      ps.setInt(1, dossier.getMobilite().getId());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          dossier.setContratBourse(rs.getBoolean(1));
          dossier.setConvention(rs.getBoolean(2));
          dossier.setCharteEtudiant(rs.getBoolean(3));
          dossier.setPreuvePassageLinguistiqueAvant(rs.getBoolean(4));
          dossier.setDocumentEngagement(rs.getBoolean(5));
          dossier.setAttestationSejour(rs.getBoolean(6));
          dossier.setReleveNote(rs.getBoolean(7));
          dossier.setCertificatStage(rs.getBoolean(8));
          dossier.setRapportFinal(rs.getBoolean(9));
          dossier.setPreuvePassageLinguistiqueApres(rs.getBoolean(10));
          dossier.setEncodageMobi(rs.getBoolean(11));
          dossier.setEncodageProEco(rs.getBoolean(12));
          dossier.setEncodageMobilityTool(rs.getBoolean(13));
          return dossier;
        } else {
          return null;
        }
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public void update(DossierDto dossier) {
    PreparedStatement ps = this.access.prepare(UPDATE_STATEMENT);
    try {
      ps.setBoolean(1, dossier.getContratBourse());
      ps.setBoolean(2, dossier.getConvention());
      ps.setBoolean(3, dossier.getCharteEtudiant());
      ps.setBoolean(4, dossier.getPreuvePassageLinguistiqueAvant());
      ps.setBoolean(5, dossier.getDocumentEngagement());
      ps.setBoolean(6, dossier.getAttestationSejour());
      ps.setBoolean(7, dossier.getReleveNote());
      ps.setBoolean(8, dossier.getCertificatStage());
      ps.setBoolean(9, dossier.getRapportFinal());
      ps.setBoolean(10, dossier.getPreuvePassageLinguistiqueApres());
      ps.setBoolean(11, dossier.getEncodageMobi());
      ps.setBoolean(12, dossier.getEncodageProEco());
      ps.setBoolean(13, dossier.getEncodageMobilityTool());
      ps.setInt(14, dossier.getVersion() + 1);
      ps.setInt(15, dossier.getMobilite().getId());
      ps.setInt(16, dossier.getVersion());
      ps.execute();
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public void delete(DossierDto dossier) {
    PreparedStatement ps = this.access.prepare(DELETE_STATEMENT);
    try {
      ps.setInt(1, dossier.getMobilite().getId());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return;
        }
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }


  public DossierDto selectByMobi(MobiliteDto mobilite) {
    try {
      PreparedStatement ps = this.access.prepare(SELECT_MOBI_STATEMENT);
      ps.setInt(1, mobilite.getId());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        DossierDto dossier = (DossierDto) factory.getDto(DossierDto.class.getSimpleName());
        MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
        mobiliteDto.setId(rs.getInt(1));
        dossier.setMobilite(mobiliteDto);
        dossier.setContratBourse(rs.getBoolean(2));
        dossier.setConvention(rs.getBoolean(3));
        dossier.setCharteEtudiant(rs.getBoolean(4));
        dossier.setPreuvePassageLinguistiqueAvant(rs.getBoolean(5));
        dossier.setDocumentEngagement(rs.getBoolean(6));
        dossier.setAttestationSejour(rs.getBoolean(7));
        dossier.setReleveNote(rs.getBoolean(8));
        dossier.setCertificatStage(rs.getBoolean(9));
        dossier.setRapportFinal(rs.getBoolean(10));
        dossier.setPreuvePassageLinguistiqueApres(rs.getBoolean(11));
        dossier.setEncodageMobi(rs.getBoolean(12));
        dossier.setEncodageProEco(rs.getBoolean(13));
        dossier.setEncodageMobilityTool(rs.getBoolean(14));
        dossier.setVersion(rs.getInt(15));
        return dossier;
      } else {
        return null;
      }
    } catch (SQLException excep) {

      throw new InternalError();
    }

  }
}
