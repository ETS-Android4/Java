package info.hccis.cis2250.flowershop.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import info.hccis.cis2250.flowershop.bo.Customer;


@Dao
public interface CustomerDAO {

    @Insert
    public long add(Customer customer);

    @Update
    public void update(Customer customer);
    @Delete
    public void delete(Customer customer);

    @Query("select * from customer")
    public List<Customer> get();

    @Query("select * from customer where id =:id")
    public Customer get(int id);

    @Query("delete from customer")
    public void deleteAll();

}
