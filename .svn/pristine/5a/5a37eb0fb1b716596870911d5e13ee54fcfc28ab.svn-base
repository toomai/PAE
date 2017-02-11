package objects.models;

import dao.interfaces.MobiliteDao;
import enumere.Etat;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.BizzFactoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MockupMobiliteDao implements MobiliteDao {
  private BizzFactory factory = new BizzFactoryImpl();

  @Override
  public void insert(MobiliteDto mobilite) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<MobiliteDto> selectAll() {
    List<MobiliteDto> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public List<MobiliteDto> select(MobiliteDto mobilite) {
    List<MobiliteDto> aReturn = new ArrayList<>();
    MobiliteDto ret = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    ret.setPeriode("30");
    ret.setEtat(Etat.A_PAYER);
    if (ret.getEtat().equals(mobilite.getEtat()) || ret.getPeriode().equals(mobilite.getPeriode())) {
      aReturn.add(ret);
      return aReturn;
    } else {
      return null;
    }
  }

  @Override
  public List<MobiliteDto> selectParUtilisateur(UtilisateurDto utilisateur) {
    List<MobiliteDto> aReturn = new ArrayList<>();
    MobiliteDto ret = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    UtilisateurDto retUt = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    PartenaireDto partenaireDto =
        (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    retUt.setPseudo("Lama");
    ret.setEtudiant(retUt);
    partenaireDto.setId(1);
    ret.setPartenaire(partenaireDto);
    ret.setId(1);
    if (ret.getEtudiant().getPseudo().equals(utilisateur.getPseudo())) {
      aReturn.add(ret);
      return aReturn;
    } else {
      return null;
    }
  }

  @Override
  public MobiliteDto selectByChoix(MobiliteDto mobilite) {
    MobiliteDto ret = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    ret.setChoix(1);
    if (ret.getChoix() == mobilite.getChoix()) {
      return ret;
    } else {
      return null;
    }
  }

  @Override
  public MobiliteDto selectById(MobiliteDto mobi) {
    MobiliteDto ret = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    ret.setId(1);
    if (ret.getId() == mobi.getId()) {
      return ret;
    } else {
      return null;
    }
  }

  @Override
  public void update(MobiliteDto mobilite) throws SQLException {}

  @Override
  public void delete(MobiliteDto mobilite) {}

  @Override
  public MobiliteDto selectByUserAndPartner(String user, int idPartenaire) {
    MobiliteDto ret = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    UtilisateurDto retUt = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    PartenaireDto partenaireDto =
        (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    retUt.setPseudo("Lama");
    partenaireDto.setId(1);
    ret.setEtudiant(retUt);
    ret.setPartenaire(partenaireDto);
    if (ret.getEtudiant().getPseudo().equals(user) && ret.getPartenaire().getId() == idPartenaire) {
      return ret;

    } else {
      return null;
    }
  }

  @Override
  public List<MobiliteDto> getAllMobilitesPaiementDemande() {
    List<MobiliteDto> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public MobiliteDto selectByUser(UtilisateurDto utilisateur) {
    MobiliteDto ret = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    UtilisateurDto retUt = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    retUt.setPseudo("Lama");
    ret.setEtudiant(retUt);
    if (utilisateur.getPseudo().equals(ret.getEtudiant().getPseudo())) {
      return ret;
    } else {
      return null;
    }
  }

  @Override
  public void updateEtat(MobiliteDto mobilite) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateAnnulation(MobiliteDto mobilite) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean selectByPartenaire(PartenaireDto partenaire) {
    // TODO Auto-generated method stub
    return partenaire == null;
  }

}
