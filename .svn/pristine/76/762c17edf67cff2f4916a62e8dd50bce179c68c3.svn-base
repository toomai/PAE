package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;

public interface RechercheDao {


  List<String> donneesTable() throws SQLException;

  List<String> nomPartenaire() throws SQLException;

  List<MobiliteDto> effectuerRechercheMobilite(String recherche) throws SQLException;

  List<PartenaireDto> effectuerRecherchePartenaire(String recherche) throws SQLException;

  List<UtilisateurDto> effectuerRechercheUtilisateur(String recherche) throws SQLException;

}
