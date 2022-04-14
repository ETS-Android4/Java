/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.hccis.planttracker.entity.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Auto Generated Entity Class
 * @author Kendall Fowler
 * @since 2020-06-23
 */
@Entity
@Table(name = "planttracker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlantTracker.findAll", query = "SELECT p FROM PlantTracker p"),
    @NamedQuery(name = "PlantTracker.findById", query = "SELECT p FROM PlantTracker p WHERE p.id = :id"),
    @NamedQuery(name = "PlantTracker.findByPlantSeason", query = "SELECT p FROM PlantTracker p WHERE p.plantSeason = :plantSeason"),
    @NamedQuery(name = "PlantTracker.findByPlantName", query = "SELECT p FROM PlantTracker p WHERE p.plantName = :plantName"),
    @NamedQuery(name = "PlantTracker.findByPlantDate", query = "SELECT p FROM PlantTracker p WHERE p.plantDate = :plantDate"),
    @NamedQuery(name = "PlantTracker.findByNumberPlanted", query = "SELECT p FROM PlantTracker p WHERE p.numberPlanted = :numberPlanted")})
public class PlantTracker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "plantSeason")
    private String plantSeason;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "plantName")
    private String plantName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "plantDate")
    private String plantDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numberPlanted")
    private int numberPlanted;

    /**
     * Auto Generated Default Constructor
     * @author Kendall Fowler
     * @since 2020-06-23
     */
    public PlantTracker() {
    }

    public PlantTracker(Integer id) {
        this.id = id;
    }

    /**
     * Auto Generated Constructor
     * @author Kendall Fowler
     * @since 2020-06-23
     */
    public PlantTracker(Integer id, String plantSeason, String plantName, String plantDate, int numberPlanted) {
        this.id = id;
        this.plantSeason = plantSeason;
        this.plantName = plantName;
        this.plantDate = plantDate;
        this.numberPlanted = numberPlanted;
    }

    //Getters and Setters
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

    /**
     * Auto Generated toString for entity
     * @return 
     * @author Kendall Fowler
     * @since 2020-06-23
     */
    @Override
    public String toString() {
        return "info.hccis.planttracker.entity.jpa.PlantTracker[ id=" + id + " ]";
    }
    
}
