package objects.models;

import dao.interfaces.RechercheDao;
import enumere.Etat;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.BizzFactoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MockupRechercheDao implements RechercheDao {
  private BizzFactory factory = new BizzFactoryImpl();

  @Override
  public List<String> donneesTable() throws SQLException {
    List<String> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public List<String> nomPartenaire() throws SQLException {
    List<String> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public List<MobiliteDto> effectuerRechercheMobilite(String recherche) throws SQLException {
    List<MobiliteDto> aReturn = new ArrayList<>();
    MobiliteDto ret = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    ret.setEtat(Etat.ANNULE);;
    if (ret.getEtat().getEtat().equals(recherche)) {
      aReturn.add(ret);
      return aReturn;
    } else {
      return null;
    }
  }

  @Override
  public List<PartenaireDto> effectuerRecherchePartenaire(String recherche) throws SQLException {
    List<PartenaireDto> aReturn = new ArrayList<>();
    PartenaireDto ret = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    ret.setNomLegal("lol");
    if (ret.getNomLegal().equals(recherche)) {
      aReturn.add(ret);
      return aReturn;
    } else {
      return null;
    }
  }

  @Override
  public List<UtilisateurDto> effectuerRechercheUtilisateur(String recherche) throws SQLException {
    List<UtilisateurDto> aReturn = new ArrayList<>();
    UtilisateurDto ret = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    ret.setNom("lama");
    if (ret.getNom().equals(recherche)) {
      aReturn.add(ret);
      return aReturn;
    } else {
      return null;
    }
  }

}
