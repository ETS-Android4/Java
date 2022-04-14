package info.hccis.planttracker.model.jpa;

import java.io.Serializable;

/**
 *
 * @author Kendall Fowler
 * @since 2020-06-21
 */
public class PlantTracker implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String plantSeason;
    private String plantName;
    private String plantDate;
    private int numberPlanted;

    public PlantTracker() {
    }

    public PlantTracker(Integer id) {
        this.id = id;
    }

    public PlantTracker(Integer id, String plantSeason, String plantName, String plantDate, int numberPlanted) {
        this.id = id;
        this.plantSeason = plantSeason;
        this.plantName = plantName;
        this.plantDate = plantDate;
        this.numberPlanted = numberPlanted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlantSeason() {
        return plantSeason;
    }

    public void setPlantSeason(String plantSeason) {
        this.plantSeason = plantSeason;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(String plantDate) {
        this.plantDate = plantDate;
    }

    public int getNumberPlanted() {
        return numberPlanted;
    }

    public void setNumberPlanted(int numberPlanted) {
        this.numberPlanted = numberPlanted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlantTracker)) {
            return false;
        }
        PlantTracker other = (PlantTracker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID:" + id
                + ", Season Planted: " + plantSeason
                + ", Plant Name: " + plantName
                + ", Date Planted: " + plantDate
                + ", Number of Seeds Planted: " + numberPlanted;
    }

}
