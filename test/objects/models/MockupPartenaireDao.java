package objects.models;

import dao.interfaces.PartenaireDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.PartenaireDto;
import objects.model.BizzFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MockupPartenaireDao implements PartenaireDao {
  private BizzFactory factory = new BizzFactoryImpl();

  @Override
  public boolean insert(PartenaireDto partenaire) {
    return true;
  }

  @Override
  public List<PartenaireDto> selectAll() {
    List<PartenaireDto> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public PartenaireDto selectById(PartenaireDto partenaire) {
    PartenaireDto ret = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    ret.setId(1);
    if (ret.getId() == partenaire.getId()) {
      return partenaire;
    } else {
      return null;
    }
  }


  @Override
  public PartenaireDto selectByName(PartenaireDto part) {
    PartenaireDto ret = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    ret.setNomComplet("Mon Fromage bien aimé");
    ret.setNomLegal("Mon Fromage bien aimé");
    if (ret.getNomComplet().equals(part.getNomLegal())) {
      return ret;
    } else {
      return null;
    }
  }

  @Override
  public boolean update(PartenaireDto partenaire) {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public void delete(PartenaireDto partenaire) {
    // TODO Auto-generated method stub

  }


  @Override
  public List<PartenaireDto> selectNames() {
    List<PartenaireDto> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public PartenaireDto selectId(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PartenaireDto selectAjout(PartenaireDto part) {
    // TODO Auto-generated method stub
    PartenaireDto aret = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    aret.setId(5);
    aret.setNomLegal("coucou");
    aret.setAjoutParEleve(true);
    return aret;
  }


  @Override
  public List<PartenaireDto> selectPartenairesDispos() {
    List<PartenaireDto> liste = new ArrayList<>();
    return liste;
  }

  @Override
  public List<PartenaireDto> selectPartenairesSupprimes() {
    List<PartenaireDto> liste = new ArrayList<>();
    return liste;
  }



}
