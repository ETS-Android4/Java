package info.hccis.cis2250.flowershop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import info.hccis.cis2250.flowershop.bo.Customer;
import info.hccis.cis2250.flowershop.dao.MyAppDatabase;
import info.hccis.cis2250.flowershop.ui.customers.CustomersFragment;

import static android.view.View.*;

/**
 * Class to manage main activity
 * @modified KF
 * @since 27-Feb-2021
 */
public class MainActivity extends AppCompatActivity implements CustomersFragment.OnListFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;
    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //disable fab from the app
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_about, R.id.nav_recycler_view,
                R.id.nav_linear_layout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //BJM 20200131 Build the app database object
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "canesdb").allowMainThreadQueries().build();

      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /*
     * Method used when navigating from the menu items
     * https://developer.android.com/guide/navigation/navigation-ui
     *
     * Date: 2021/01/22
     * Purpose: used for material design presentation - feedback fragment
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * This method will be used in the camper fragment when the user clicks on a row of the
     * camper recyclerview.  This method will transfer the user to a details fragment.  The
     * id of the camper will be passed to the fragment and used to load the correct camper details.
     * Will use the arraylist associated with the recyclerview.
     *
     * @param item the customer
     * @author BJM
     * @since 20200124
     *
     */
    @Override
    public void onListFragmentInteraction(Customer item) {

        /*
            BJM 20200131
            Send the user to a details fragment.
        */

        Log.d("bjm", "item communicated from fragment: " + item.toString());


        /* BJM 20200202
           Put the json representation of the camper into the bundle to be passed to the fragment.
           This will be used in the details fragment.
        */

        Bundle bundle = new Bundle();
        Gson gson = new Gson();
        bundle.putString("customer", gson.toJson(item));

        /*
          BJM 20200202
          Use the navigation controller object stored as an attribute of the main
          activity to nagivate the ui to the camper detail fragment.
        */

        Log.d("BJM","Changing nav_host_fragment to the camper detail fragment");

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.nav_customer_detail, bundle);

    }



}