package info.hccis.cis2250.flowershop.ui.contacts;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import info.hccis.cis2250.flowershop.R;

/**
 * Fragment used for adding contacts using a content provider.
 *
 * @author Kendall Fowler
 * @since 29-MAR-2021
 */
public class ContactFragment extends Fragment implements View.OnClickListener {

    private Button addContact;
    private RadioButton emailHome, emailWork, phoneHome, phoneMobile;
    private EditText name, email, phoneNumber;


    private String rgEmail = "Home";
    private String rgPhone = "Home";

    public ContactFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        name = (EditText) getView().findViewById(R.id.textName);
        email = (EditText) getView().findViewById(R.id.textEmail);
        phoneNumber = (EditText) getView().findViewById(R.id.textPhone);

        addContact = (Button) getView().findViewById(R.id.buttonAdd);
        addContact.setOnClickListener(this);

        emailHome = (RadioButton) getView().findViewById(R.id.emailHome);
        emailHome.setOnClickListener(this);

        emailWork = (RadioButton) getView().findViewById(R.id.emailWork);
        emailWork.setOnClickListener(this);

        phoneHome = (RadioButton) getView().findViewById(R.id.phoneHome);
        phoneHome.setOnClickListener(this);

        phoneMobile = (RadioButton) getView().findViewById(R.id.phoneMobile);
        phoneMobile.setOnClickListener(this);
    }

    /**
     * Class with Switch to control buttons and radio buttons
     *
     * @param view
     * @author Kendall Fowler
     * @since 29-MAR-2021
     */
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonAdd:
                addContactFromTextFields();
                break;
            case R.id.emailHome:
                if (emailHome.isChecked()) {
                    rgEmail = "Home";
                }
                break;
            case R.id.emailWork:
                if (emailWork.isChecked()) {
                    rgEmail = "Work";
                }
                break;
            case R.id.phoneHome:
                if (phoneHome.isChecked()) {
                    rgPhone = "Home";
                }
                break;
            case R.id.phoneMobile:
                if (phoneMobile.isChecked()) {
                    rgPhone = "Mobile";
                }
                break;
        }
    }

    /**
     * Take the user-input data from the text fields (and radio buttons), opens the Contacts app and has fields auto-populated.
     *
     * @author Kendall Fowler
     * @since 29-MAR-2021
     */
    public void addContactFromTextFields() {
        Intent insertContactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        insertContactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        insertContactIntent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
        insertContactIntent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
        insertContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.getText().toString());

        if (rgEmail == "Home") {
            insertContactIntent.putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);
        } else {
            insertContactIntent.putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        }
        if (rgPhone == "Home") {
            insertContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
        } else {
            insertContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        }

        startActivity(insertContactIntent);
    }

}