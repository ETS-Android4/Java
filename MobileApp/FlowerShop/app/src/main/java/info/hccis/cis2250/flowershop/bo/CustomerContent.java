package info.hccis.cis2250.flowershop.bo;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.hccis.cis2250.flowershop.MainActivity;
import info.hccis.cis2250.flowershop.api.JsonCustomerApi;
import info.hccis.cis2250.flowershop.ui.customers.CustomersFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CustomerContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Customer> CUSTOMERS = new ArrayList<Customer>();

    /**
     * Reload the Room db with the provided list.
     *
     * @param customers
     * @author KF
     * @since 09-03-2021
     * <p>
     */

    public static void reloadCampersInRoom(List<Customer> customers) {
        //first delete all
        MainActivity.myAppDatabase.customerDAO().deleteAll();
        for (Customer current : customers) {
            MainActivity.myAppDatabase.customerDAO().add(current);
        }

    }

    /**
     * Get the list of customers that are currently in the Room database.
     *
     * @return the customers
     * @author KF
     * @since 09-03-2021
     */

    public static List<Customer> getCustomersFromRoom() {

        Log.d("bjm get from db", "Loading from room.");


        List<Customer> customersBack = MainActivity.myAppDatabase.customerDAO().get();
        Log.d("bjm", "loaded from room db, size=" + customersBack.size());
        Log.d("bjm", "Here they are");
        for (Customer current : customersBack) {
            Log.d("bjm", current.toString());
        }
        Log.d("bjm", "that's it");

        return customersBack;


    }


    /**
     * Load the customers.  This method will use the rest service to provide the data.  The reason it is
     * in this class is because it is changing the value to the CUSTOMERS list.  This is the list which
     * is used to back the RecyclerView.
     *
     * @author  KF
     * @since 27-Feb-2021
     * @since 09-03-2021
     */

    public static void loadCustomers(Activity context) {

        Log.d("BJM", "Accessing api at:" + Util.CUSTOMER_BASE_API);

        //Use Retrofit to connect to the service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.CUSTOMER_BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonCustomerApi jsonCustomerApi = retrofit.create(JsonCustomerApi.class);

        //Create a list of campers.
        Call<List<Customer>> call = jsonCustomerApi.getCustomers();

        //final reference to activity to get shared preferences
        final Activity contextIn = context;

        call.enqueue(new Callback<List<Customer>>() {

            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {

                List<Customer> customers;

                customers = response.body();

                if (!response.isSuccessful()) {
                    Log.d("bjm rest", "not successful response from rest for campers Code=" + response.code());
                    customers = getCustomersFromRoom();
                } else {
                    customers = response.body();
                    reloadCampersInRoom(customers);
                }

                Log.d("bjm", "data back from service call #returned=" + customers.size());

                //**********************************************************************************
                // Now that we have the campers, will use them to assign values to the list which
                // is backing the recycler view.
                //**********************************************************************************

                CustomerContent.CUSTOMERS.clear();
                CustomerContent.CUSTOMERS.addAll(customers);

                //**********************************************************************************
                // The CamperListFragment has a recyclerview which is used to show the camper list.
                // This recyclerview is backed by the camper list in the CamperContent class.  After
                // this list is loaded, need to notify the adapter from the recyclerview that the
                // data is changed.
                //**********************************************************************************

                CustomersFragment.getRecyclerView().getAdapter().notifyDataSetChanged();

                //Remove the progress bar from the view.
                CustomersFragment.clearProgressBarVisitiblity();

            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {

                //**********************************************************************************
                // If the api call failed, give a notification to the user.
                //**********************************************************************************
                Log.d("bjm", "api call failed");
                Log.d("bjm", t.getMessage());

                CustomerContent.CUSTOMERS.clear();
                CustomerContent.CUSTOMERS.addAll(getCustomersFromRoom());

                CustomersFragment.getRecyclerView().getAdapter().notifyDataSetChanged();

                //Remove the progress bar from the view.
                CustomersFragment.clearProgressBarVisitiblity();

            }
        });
    }
}