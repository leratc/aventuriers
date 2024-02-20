import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Carte {
    private Aventurier aventurier;
    private Integer XWidth; // size of the grid
    private Integer YHeight; // top left corner is (x=0, y=0)
    private List<Coordonnee> montagnes=new ArrayList<>();
    private Map<Integer,Coordonnee> deplacementsSuccessifsByTour=new HashMap<>();

    public Carte() {
    }
    public Carte(Aventurier aventurier) {
        this.aventurier = aventurier;
    }

    public List<Coordonnee> getMontagnes() {
        return montagnes;
    }

    public void setMontagnes(List<Coordonnee> montagnes) {
        this.montagnes = montagnes;
    }

    public Aventurier getAventurier() {
        return aventurier;
    }

    public void setAventurier(Aventurier aventurier) {
        this.aventurier = aventurier;
    }

    public Integer getXWidth() {
        return XWidth;
    }

    public void setXWidth(Integer XWidth) {
        this.XWidth = XWidth;
    }

    public Integer getYHeight() {
        return YHeight;
    }

    public void setYHeight(Integer YHeight) {
        this.YHeight = YHeight;
    }

    public void buildDeplacementsSuccessifsByTour() {
        Map<Integer,Coordonnee> deplacementsSuccessifsByTour=new HashMap<>();
        Integer tour = 0;
        //changement direction et deplacement Carte
        String[] scriptDeplacement= this.getAventurier().getScriptDeplacement().split("");
        for (String s:scriptDeplacement) {
            if (s.equals("A")) {
                Coordonnee coordonnee = this.aventurier.tickCoord(this.getXWidth(), this.getYHeight(), this.montagnes);
                if (coordonnee != null) {
                    tour = tour + 1;
                    deplacementsSuccessifsByTour.put(tour,coordonnee);
                    this.getAventurier().setCoordonneeCourante(coordonnee);
                }
            }
            else if (s.equals("D") || s.equals("G")) {
                this.aventurier.nextDirection(TurnDirection.valueOf(s));
            }
        }
        this.deplacementsSuccessifsByTour=deplacementsSuccessifsByTour;
    }
    void incrementeNombreDeTresorsTrouve() {
         this.aventurier.setNombreDeTresorsTrouve(this.aventurier.getNombreDeTresorsTrouve() + 1);
    }

    public Map<Integer, Coordonnee> getDeplacementsSuccessifsByTour() {
        return deplacementsSuccessifsByTour;
    }

    public void setDeplacementsSuccessifsByTour(Map<Integer, Coordonnee> deplacementsSuccessifsByTour) {
        this.deplacementsSuccessifsByTour = deplacementsSuccessifsByTour;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "aventurier=" + aventurier +
                ", XWidth=" + XWidth +
                ", YHeight=" + YHeight +
                ", montagnes=" + montagnes +
                '}';
    }
}
