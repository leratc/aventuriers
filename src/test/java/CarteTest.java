import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CarteTest {
    @Test
    @DisplayName("Test handleScriptDeplacement")
    public void test_handleScriptDeplacement() {
        // La carte gère les déplacements en fonction des montagnes et de la taille de la carte.
        Carte carte = new Carte();
        carte.setXWidth(3);
        carte.setYHeight(4);
        carte.getMontagnes().add(new Coordonnee(1,0));
        carte.getMontagnes().add(new Coordonnee(2,1));
        Aventurier aventurier =new Aventurier("Lara", new Coordonnee(1,1),Direction.S,"AADADAGGA" );
        carte.setAventurier(aventurier);
        carte.buildDeplacementsSuccessifsByTour();
        Map<Integer, Coordonnee> deplacementsSuccessifsByTour = carte.getDeplacementsSuccessifsByTour();
        // valeur de la dernière coordonnée
        Integer lastTour = deplacementsSuccessifsByTour.size();
        assertThat( deplacementsSuccessifsByTour.get(lastTour).getX()).isEqualTo(0);
        assertThat( deplacementsSuccessifsByTour.get(lastTour).getY()).isEqualTo(3);
    }
}
