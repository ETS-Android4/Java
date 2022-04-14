package info.hccis.cis2250.flowershop.ui.customers;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import info.hccis.cis2250.flowershop.R;
import info.hccis.cis2250.flowershop.bo.Customer;
import info.hccis.cis2250.flowershop.bo.CustomerContent;

/**
 * Class to manage the Customer Fragment
 * @author  KF
 * @since 01-Mar-2021
 */
public class CustomersFragment extends Fragment {

    private static RecyclerView recyclerView;
    private TextView textViewResult;
    private static ProgressBar progressBar;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public static RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * Method to clear this fragments progress bar
     * @author  KF
     * @since 01-Mar-2021
     */
    public static void clearProgressBarVisitiblity(){
        try {
            progressBar.setVisibility(View.GONE);
        }catch(Exception e){
            Log.d("bjm","could not clear the progress bar - user may have left fragment.");
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        // Set the adapter
        Context context = getView().getContext();
        recyclerView = getView().findViewById(R.id.list);
        progressBar = getView().findViewById(R.id.progressBarLoadingCustomers);

        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        recyclerView.setAdapter(new MyCustomerListRecyclerViewAdapter(CustomerContent.CUSTOMERS, mListener));

        //******************************************************************************************
        // BJM 20200117
        // Call the method which will load the campers list associated with the recyclerView.
        // Want to set this up so the list will be loaded when the use navigates to the recyclerview.
        // Note that this method will notify the adapter that the list has changed.
        //******************************************************************************************

        CustomerContent.loadCustomers(getActivity());


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customers, container, false);

        //Toast Code
        Toast.makeText(getActivity(),"Welcome to the Customer Page", Toast.LENGTH_LONG).show();

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Customer item);
    }



}