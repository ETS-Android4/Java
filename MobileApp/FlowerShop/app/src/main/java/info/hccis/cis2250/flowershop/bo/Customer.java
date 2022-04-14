package info.hccis.cis2250.flowershop.bo;

import android.widget.TextView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

//import java.io.Serializable;

/**
 * Class to manage Customer information
 * @author Kendall
 * @since 01-Mar-2021
 */

@Entity (tableName = "customer" )
public class Customer {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    //private static final long serialVersionUID = 1L;

    //private Integer id;


    @ColumnInfo(name = "fullName")
    private String fullName;

    @ColumnInfo(name = "address1")
    private String address1;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "province")
    private String province;

    @ColumnInfo(name = "postalCode")
    private String postalCode;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "birthDate")
    private String birthDate;

    @ColumnInfo(name = "loyaltyCard")
    private String loyaltyCard;

    //Empty Constructor
    public Customer() {
    }

    //Id Constructor
    public Customer(Integer id) {
        this.id = id;
    }

    //Constructor
    public Customer(Integer id, String fullName, String address1, String city, String province, String postalCode, String phoneNumber, String birthDate, String loyaltyCard) {
        this.id = id;
        this.fullName = fullName;
        this.address1 = address1;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.loyaltyCard = loyaltyCard;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(String loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                "full Name: " + fullName;
    }
}