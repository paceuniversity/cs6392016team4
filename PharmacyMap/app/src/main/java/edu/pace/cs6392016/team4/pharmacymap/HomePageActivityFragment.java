package edu.pace.cs6392016.team4.pharmacymap;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomePageActivityFragment extends Fragment {

    public HomePageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_page_fragment_main, container, false);
        final EditText zipCode = (EditText) rootView.findViewById(R.id.searchMap);
        Button search = (Button)rootView.findViewById(R.id.button);
        search.setOnClickListener( new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               String input;
               if (zipCode.getText().length() > 0) {
                   input = zipCode.getText().toString();
                   input = "geo:?q=pharmacies " + input;
               }
               else {
                   input = "geo:40.710412,-74.006137?q=pharmacies";
               }
               Uri gmmIntentUri = Uri.parse(input);
               Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
               mapIntent.setPackage("com.google.android.apps.maps");
               startActivity(mapIntent);
           }
        });


        return rootView;

    }
}
