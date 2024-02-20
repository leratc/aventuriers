

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

class AventurierPlayGround{

    Map<String, Carte> mapAventurierCarteInstance= new HashMap<>();
    private Map<Coordonnee, Integer> tresors = new HashMap<>();
    Integer tourMax = 0;
    public AventurierPlayGround buildPlayGroundFromResourceFile(String fileName) {
        AventurierPlayGround aventurierPlayGround =new AventurierPlayGround();
        File file= new File(fileName);
        Carte theCarte=new Carte();
        List<Aventurier> aventuriers = new ArrayList<>();
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(oneLine -> {
                  String lineWithoutBlank=  oneLine.replaceAll("\\s+","");
                  // skip empty line and comment
                 if (lineWithoutBlank !=null && !lineWithoutBlank.isEmpty() && !lineWithoutBlank.startsWith("#")  ) {

                     String[] lineCols = lineWithoutBlank.split("-");
                     switch (lineCols[0]) {
                         case "C":
                             theCarte.setXWidth(Integer.valueOf(lineCols[1]));
                             theCarte.setYHeight(Integer.valueOf(lineCols[2]));
                             break;
                         case "M":
                             Coordonnee coordonneeCarte = new Coordonnee(Integer.valueOf(lineCols[1]), Integer.valueOf(lineCols[2]));
                             theCarte.getMontagnes().add(coordonneeCarte);
                             break;
                         case "T":
                             Coordonnee coordonneeTresor = new Coordonnee(Integer.valueOf(lineCols[1]), Integer.valueOf(lineCols[2]));
                             Integer nbTresor = Integer.valueOf(lineCols[3]);
                             this.tresors.put(coordonneeTresor, nbTresor);
                             break;
                         case "A":
                             String nomAventurier = lineCols[1];
                             Coordonnee coordonneeInitiale = new Coordonnee(Integer.valueOf(lineCols[2]), Integer.valueOf(lineCols[3]));
                             Direction directionInitiale = Direction.valueOf(lineCols[4]);
                             String scriptDeplacement = lineCols[5];
                             Aventurier aventurier = new Aventurier(nomAventurier, coordonneeInitiale, directionInitiale, scriptDeplacement);
                             aventuriers.add(aventurier);
                             break;
                         default:
                             throw new IllegalStateException("Unexpected value lineCols: " + lineCols[0]);
                     }
                 }
            });

        } catch (IOException e) {
              e.printStackTrace();
        }
        aventuriers.stream().forEach(aventurier -> {
           Carte theCarteClone=new Carte(aventurier);
           theCarteClone.setXWidth(theCarte.getXWidth());
           theCarteClone.setYHeight(theCarte.getYHeight());
           theCarteClone.setMontagnes(theCarte.getMontagnes());

           theCarteClone.buildDeplacementsSuccessifsByTour();
           mapAventurierCarteInstance.put(aventurier.getNomAventurier(), theCarteClone);
        });
        setMaxTour();
        return aventurierPlayGround;
    }

    public void setMaxTour() {
        this.tourMax = this.mapAventurierCarteInstance.values().stream().map(carte -> carte.getDeplacementsSuccessifsByTour().size()).max(Integer::compareTo).orElse(0);
    }

    void rechercheAuxTresors() {
       for (Integer tourCourant=1; tourCourant<=tourMax; tourCourant++) {
            for (Carte aventurierCarteInstance : this.mapAventurierCarteInstance.values()) {
                if (tourCourant <= aventurierCarteInstance.getDeplacementsSuccessifsByTour().size()) {
                    Coordonnee coordonneeAventutierCourant = aventurierCarteInstance.getDeplacementsSuccessifsByTour().get(tourCourant);
                    Integer nbTresorEnCoordonnee = getNombreDeTresorsEnCoordonnee(coordonneeAventutierCourant);
                    if (nbTresorEnCoordonnee != null) {
                        aventurierCarteInstance.incrementeNombreDeTresorsTrouve();
                        decrementeNbTresorEnCoordonee(coordonneeAventutierCourant, nbTresorEnCoordonnee);
                    }
                }
            };
        }
   }
    Integer getNombreDeTresorsEnCoordonnee(Coordonnee coordonneeAventutierCourant) {
        return this.tresors.get(coordonneeAventutierCourant);
    }

   void decrementeNbTresorEnCoordonee(Coordonnee coordonneeAventutierCourant,Integer nbTresorEnCoordonnee) {
        Integer nbTresorEnCoordonneeRestant =  nbTresorEnCoordonnee - 1;
        if (nbTresorEnCoordonneeRestant <= 0) {
            tresors.remove(coordonneeAventutierCourant);
        }
        else {
            this.tresors.put(coordonneeAventutierCourant, nbTresorEnCoordonneeRestant);
        }
    }
    public Map<String, Carte> getMapAventurierCarteInstance() {
        return mapAventurierCarteInstance;
    }

    public void setMapAventurierCarteInstance(Map<String, Carte> mapAventurierCarteInstance) {
        this.mapAventurierCarteInstance = mapAventurierCarteInstance;
    }

    public Map<Coordonnee, Integer> getTresors() {
        return tresors;
    }

    public void setTresors(Map<Coordonnee, Integer> tresors) {
        this.tresors = tresors;
    }

    @Override
    public String toString() {
        return "AventurierPlayGround{" +
                "mapAventurierCarteInstance=" + mapAventurierCarteInstance +
                ", tresors=" + tresors +
                '}';
    }
}
