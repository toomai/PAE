package objects.models;

import dao.interfaces.UtilisateurDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.BizzFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MockupUtilisateurDao implements UtilisateurDao {
  private BizzFactory factory = new BizzFactoryImpl();

  @Override
  public boolean insert(UtilisateurDto utilisateur) {
    return true;
  }

  @Override
  public List<UtilisateurDto> selectAll() {
    List<UtilisateurDto> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public UtilisateurDto select(UtilisateurDto utilisateur) {
    UtilisateurDto ret = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    ret.setPseudo("lama");
    ret.setMdp("1000:732df80317afa7d10785c57e0ff06654:5b8e5bb8b2a7c1263875746a89915a38502fd27eccaf940d488d8e28679972b16328381b7f5ed931751f5a7fb8b949759414300efc4ac2d692b827f2cb872b07");
    if (ret.getPseudo().equals(utilisateur.getPseudo())) {
      return ret;
    } else {
      throw new InternalError();

    }
  }

  @Override
  public boolean update(UtilisateurDto demandeur, UtilisateurDto utilisateur) {
    return true;
  }

  @Override
  public void delete(UtilisateurDto utilisateur) {
    // TODO Auto-generated method stub

  }

  @Override
  public UtilisateurDto selectByName(String nom, String prenom) {
    UtilisateurDto ret = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    ret.setNom("Kendrik");
    ret.setPrenom("Lamar");
    if (ret.getNom().equals(nom) && ret.getPrenom().equals(prenom)) {
      return ret;
    } else {
      return null;
    }
  }

  public UtilisateurDto getEtudiant(UtilisateurDto dto) {
    UtilisateurDto ret = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    ret.setPseudo("lama");
    return ret;

  }

  @Override
  public List<UtilisateurDto> selectEtudiants() {
    List<UtilisateurDto> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public boolean updateStatus(UtilisateurDto utilisateur) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<String> selectEtudiantsPseudos() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean emptyOrNot() {
    // TODO Auto-generated method stub
    return false;
  }


}
