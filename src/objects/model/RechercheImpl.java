package objects.model;

import objects.interfaces.dto.RechercheDto;

public class RechercheImpl implements RechercheDto {

  private String table;


  public void setTable(String table) {
    this.table = table;
  }

  @Override
  public String getTable() {
    return this.table;
  }

  @Override
  public void rechercheTable() {

  }

  @Override
  public void effectuerRecherche() {
    // TODO Auto-generated method stub

  }

}
