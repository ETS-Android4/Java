package info.hccis.cis2250.flowershop.ui.customers;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.hccis.cis2250.flowershop.R;
import info.hccis.cis2250.flowershop.bo.Customer;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Customer} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 * @author BJM
 * @modified KF
 * @since 02-Mar-2021
 */
public class MyCustomerListRecyclerViewAdapter extends RecyclerView.Adapter<MyCustomerListRecyclerViewAdapter.ViewHolder> {

    private final List<Customer> mValues;
    private final CustomersFragment.OnListFragmentInteractionListener mListener;

    public MyCustomerListRecyclerViewAdapter(List<Customer> items, CustomersFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mFullName.setText(mValues.get(position).getFullName());


        /* BJM 20200202
           When the user selects a row from the recyclerview, this on click listener will handle
           the event.  The onListFragmentInteraction method that is implemented in the MainActivity
           will be called.  This method is used to allow this fragment to communicate with the
           activity.  The activity will then take care of sending the ui to the detail fragment
           associated with the row that was clicked in the recyclerview.
         */

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("BJM", "clicked on a row of the recyclerview.  ");


                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Class to manage view within RecyclerView
     * @author BJM
     * @modified KF
     * @since 02-Mar-2021
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mFullName;
        public Customer mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mFullName = (TextView) view.findViewById(R.id.full_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mFullName.toString() + "'";
        }
    }
}