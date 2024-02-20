import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CartePlayGroundTest {
    @Test
    @DisplayName("Test recherche aux trésors()")
    public void test_recherche_aux_tresors() {

        Carte carte = new Carte();
        carte.setXWidth(3);
        carte.setYHeight(4);
        carte.getMontagnes().add(new Coordonnee(1, 0));
        carte.getMontagnes().add(new Coordonnee(2, 1));
        Aventurier aventurier = new Aventurier("Lara", new Coordonnee(1, 1), Direction.S, "AADADAGGA");
        carte.setAventurier(aventurier);
        carte.buildDeplacementsSuccessifsByTour();

        Map<Coordonnee, Integer> tresors = new HashMap<>();
        tresors.put(new Coordonnee(0, 3), 2);
        tresors.put(new Coordonnee(1, 3), 3);
        AventurierPlayGround aventurierPlayGround = new AventurierPlayGround();
        aventurierPlayGround.setTresors(tresors);
        aventurierPlayGround.getMapAventurierCarteInstance().put(aventurier.getNomAventurier(), carte);
        aventurierPlayGround.setMaxTour();

        aventurierPlayGround.rechercheAuxTresors();
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get(aventurier.getNomAventurier()).getAventurier().getCoordonneeCourante().getX()).isEqualTo(0);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get(aventurier.getNomAventurier()).getAventurier().getCoordonneeCourante().getY()).isEqualTo(3);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get(aventurier.getNomAventurier()).getAventurier().getNombreDeTresorsTrouve()).isEqualTo(3);
    }
    @Test
    @DisplayName("Test buildPlayGroundFromResourceFile()")
    public void test_buildPlayGroundFromResourceFile() {
        String fileName = "src/test/resources/aventuriersScript.txt";
        AventurierPlayGround aventurierPlayGround = new AventurierPlayGround();
        aventurierPlayGround.buildPlayGroundFromResourceFile(fileName);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().size()).isEqualTo(1);
    }
    @Test
    @DisplayName("Test recherche aux tresors FromResourceFile()")
    public void test_recherche_aux_tresors_FromResourceFile() {
        String fileName = "src/test/resources/aventuriersScript.txt";
        AventurierPlayGround aventurierPlayGround = new AventurierPlayGround();
        aventurierPlayGround.buildPlayGroundFromResourceFile(fileName);

        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().size()).isEqualTo(1);
        aventurierPlayGround.rechercheAuxTresors();
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Lara").getAventurier().getCoordonneeCourante().getX()).isEqualTo(0);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Lara").getAventurier().getCoordonneeCourante().getY()).isEqualTo(3);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Lara").getAventurier().getNombreDeTresorsTrouve()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test recherche aux tresors FromResourceFile avec 2()")
    public void test_recherche_aux_tresors_FromResourceFile_avec_2() {
        String fileName = "src/test/resources/2aventuriersScript.txt";
        AventurierPlayGround aventurierPlayGround = new AventurierPlayGround();
        aventurierPlayGround.buildPlayGroundFromResourceFile(fileName);

        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().size()).isEqualTo(2);
        aventurierPlayGround.rechercheAuxTresors();
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Lara").getAventurier().getCoordonneeCourante().getX()).isEqualTo(0);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Lara").getAventurier().getCoordonneeCourante().getY()).isEqualTo(3);
        System.out.println(aventurierPlayGround.getMapAventurierCarteInstance().get("Lara").getDeplacementsSuccessifsByTour());
        // y a plus que 2 car Indiana a piqué le 2ème trésor au 3ème tour en x=0, y=3), au 5ème tour dans cette corrdonnées, les 2 trésors sont déjà pris.
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Lara").getAventurier().getNombreDeTresorsTrouve()).isEqualTo(2);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Indiana").getAventurier().getCoordonneeCourante().getX()).isEqualTo(0);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Indiana").getAventurier().getCoordonneeCourante().getY()).isEqualTo(2);
        assertThat(aventurierPlayGround.getMapAventurierCarteInstance().get("Indiana").getAventurier().getNombreDeTresorsTrouve()).isEqualTo(2);
        System.out.println(aventurierPlayGround.getMapAventurierCarteInstance().get("Indiana").getDeplacementsSuccessifsByTour());
    }

}
