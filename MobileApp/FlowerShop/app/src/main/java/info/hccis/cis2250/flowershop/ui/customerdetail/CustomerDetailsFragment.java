package info.hccis.cis2250.flowershop.ui.customerDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import info.hccis.cis2250.flowershop.R;
import info.hccis.cis2250.flowershop.bo.Customer;

/**
 * Fragment to manage Customer details
 * @author KF
 * @since 01-Mar-2021
 */

public class CustomerDetailsFragment extends Fragment {

    private Customer customer;
    private TextView tvFullName;
    private TextView tvAddress1;
    private TextView tvCity;
    private TextView tvProvince;
    private TextView tvPostalCode;
    private TextView tvPhoneNumber;
    private TextView tvBirthDate;
    private TextView tvLoyaltyCard;


    public static CustomerDetailsFragment newInstance() {
        return new CustomerDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String customerJson = getArguments().getString("customer");
            Gson gson = new Gson();
            customer = gson.fromJson(customerJson, Customer.class);
        }
    }

    /**
     * Added the information from the Customer to display in the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     *
     * @author Kendall
     * @since 02-Mar-2021
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer_details, container, false);

        tvFullName = view.findViewById(R.id.textViewDetailFullName);
        tvAddress1 = view.findViewById(R.id.textViewDetailAddress);
        tvCity = view.findViewById(R.id.textViewDetailCity);
        tvProvince = view.findViewById(R.id.textViewDetailProvince);
        tvPostalCode = view.findViewById(R.id.textViewDetailPostalCode);
        tvPhoneNumber = view.findViewById(R.id.textViewDetailPhoneNumber);;
        tvBirthDate = view.findViewById(R.id.textViewDetailBirthDate);;
        tvLoyaltyCard = view.findViewById(R.id.textViewDetailLoyaltyCard);;

        tvFullName.setText(customer.getFullName());
        tvAddress1.setText(customer.getAddress1());
        tvCity.setText(customer.getCity());
        tvProvince.setText(customer.getProvince());
        tvPostalCode.setText(customer.getPostalCode());
        tvPhoneNumber.setText(customer.getPhoneNumber());
        tvBirthDate.setText(customer.getBirthDate());
        tvLoyaltyCard.setText(customer.getLoyaltyCard());

        return view;
    }

}
