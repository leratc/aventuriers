import java.util.List;
import java.util.Map;

class Aventurier {
    private String nomAventurier;
    private Coordonnee coordonneeCourante;
    private Direction direction;
    private Integer nombreDeTresorsTrouve=0;
    private String scriptDeplacement;
    public Aventurier(String nomAventurier, Coordonnee coordonnee, Direction direction, String scriptDeplacement) {
        this.nomAventurier = nomAventurier;
        this.coordonneeCourante = coordonnee;
        this.direction = direction;
        this.scriptDeplacement = scriptDeplacement;
    }

    Coordonnee tickCoord(Integer xLimit,Integer yLimit, List<Coordonnee> montagnes ) {
        Coordonnee newCoordonnee= new Coordonnee(this.coordonneeCourante.x, this.coordonneeCourante.y);
        switch (this.direction) {
            case E:
                if (this.coordonneeCourante.x < xLimit) {
                    newCoordonnee.setX(this.coordonneeCourante.x + 1);
                }
                break;
            case W:
                if (this.coordonneeCourante.x > 0) {
                    newCoordonnee.setX(this.coordonneeCourante.x - 1);
                }
                break;
            case N:
                if (this.coordonneeCourante.y < yLimit) {
                    newCoordonnee.setY(this.coordonneeCourante.y - 1);
                }
                break;
            case S:
                if (this.coordonneeCourante.y > 0) {
                    newCoordonnee.setY(this.coordonneeCourante.y + 1);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
        if (!montagnes.contains(newCoordonnee)) {
            this.coordonneeCourante = newCoordonnee;
            return newCoordonnee;
        }
        else {
            System.out.print("En Direction"+ this.direction+ "Avancement bloqué en coordonnée " + this.coordonneeCourante.x +" "+this.coordonneeCourante.y+
                    ", il y a une montagne en coordonnée "+newCoordonnee.getX() +" "+newCoordonnee.getY());
            return null;
        }
    }
    void nextDirection(TurnDirection td) {
        this.setDirection(this.direction.nextDirection(td));
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getScriptDeplacement() {
        return scriptDeplacement;
    }

    public void setScriptDeplacement(String scriptDeplacement) {
        this.scriptDeplacement = scriptDeplacement;
    }


    public Coordonnee getCoordonneeCourante() {
        return coordonneeCourante;
    }

    public void setCoordonneeCourante(Coordonnee coordonneeCourante) {
        this.coordonneeCourante = coordonneeCourante;
    }

    public Integer getNombreDeTresorsTrouve() {
        return nombreDeTresorsTrouve;
    }

    public void setNombreDeTresorsTrouve(Integer nombreDeTresorsTrouve) {
        this.nombreDeTresorsTrouve = nombreDeTresorsTrouve;
    }

    public String getNomAventurier() {
        return nomAventurier;
    }

    public void setNomAventurier(String nomAventurier) {
        this.nomAventurier = nomAventurier;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "coordonneeCourante=" + coordonneeCourante +
                ", direction=" + direction +
                ", nombreDeTresorsTrouve=" + nombreDeTresorsTrouve +
                ", scriptDeplacement='" + scriptDeplacement + '\'' +
                '}';
    }
}
