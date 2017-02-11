package objects.models;

import dao.interfaces.PaysDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.PaysDto;
import objects.model.BizzFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MockupPaysDao implements PaysDao {
  private BizzFactory factory = new BizzFactoryImpl();

  @Override
  public List<PaysDto> selectAll() {
    List<PaysDto> aReturn = new ArrayList<>();
    return aReturn;
  }

  @Override
  public PaysDto selectByNom(PaysDto demande) {
    PaysDto ret = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
    ret.setNom("Belgique");
    if (ret.getNom().equals(demande.getNom())) {
      return ret;
    } else {
      return null;
    }
  }

  @Override
  public void update(PaysDto demande) {
    // TODO Auto-generated method stub

  }

  @Override
  public PaysDto selectById(PaysDto pays) {
	  PaysDto ret = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
	    ret.setCodeIso("BE");
	    if (ret.getCodeIso().equals(pays.getCodeIso())) {
	      return pays;
	    } else {
	      return null;
	    }
  }

}
