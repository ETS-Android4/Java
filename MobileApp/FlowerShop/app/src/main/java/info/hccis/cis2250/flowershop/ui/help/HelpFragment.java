package info.hccis.cis2250.flowershop.ui.help;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import info.hccis.cis2250.flowershop.R;

import static info.hccis.cis2250.flowershop.R.id.buttonHelp;
import static info.hccis.cis2250.flowershop.R.id.nameHelp;
import static info.hccis.cis2250.flowershop.R.id.emailHelp;
import static info.hccis.cis2250.flowershop.R.id.messageHelp;

//https://tekeye.uk/android/examples/email-contact-form-in-app

public class HelpFragment extends Fragment {

    public Button button;
    public EditText name, email, message;

    public HelpFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);


        button = (Button) view.findViewById(buttonHelp);
        name = (EditText) view.findViewById(nameHelp);
        email = (EditText) view.findViewById(emailHelp);
        message = (EditText) view.findViewById(messageHelp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thank you for your feedback", Toast.LENGTH_LONG).show();

                String to = ((EditText) getView().findViewById(nameHelp)).getText().toString();
                String email = ((EditText) getView().findViewById(emailHelp)).getText().toString();
                String message = ((EditText) getView().findViewById(R.id.messageHelp)).getText().toString();
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                mail.putExtra(Intent.EXTRA_SUBJECT, email);
                mail.putExtra(Intent.EXTRA_TEXT, message);
                mail.setType("message/rfc822");
                startActivity(Intent.createChooser(mail, "Send email via:"));
            }
        });
        return view;

    }
}


