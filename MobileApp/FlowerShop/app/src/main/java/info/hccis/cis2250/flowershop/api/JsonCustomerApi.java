package info.hccis.cis2250.flowershop.api;

import java.util.List;

import info.hccis.cis2250.flowershop.bo.Customer;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonCustomerApi {

    /**
     * This abstract method to be created to allow retrofit to get list of campers
     * @return List of customer
     * @since 20200202
     * @author BJM (with help from the retrofit research.
     * @modified KF
     * @since 27-Feb-2021
     */

    @GET("customers")
    Call<List<Customer>> getCustomers();

}
