package info.hccis.cis2250.flowershop.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import info.hccis.cis2250.flowershop.bo.Customer;


@Database(entities = {Customer.class},version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract info.hccis.cis2250.flowershop.dao.CustomerDAO customerDAO();

}
