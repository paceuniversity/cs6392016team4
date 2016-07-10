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

import org.apache.commons.csv.CSVRecord;

import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomePageActivityFragment extends Fragment {

    public HomePageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_page_fragment_main,container,false);
        final EditText zipCode = (EditText) rootView.findViewById(R.id.searchMap);
        Button lookUp = (Button)rootView.findViewById(R.id.executeQuery);
        lookUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input;
                if(zipCode.getText().length() > 0) {
                    input = "10038";
                }
                else {
                    input = zipCode.getText().toString();
                }

                MapMarkerAsyncTask mat = new MapMarkerAsyncTask();
                CSVRecord pharmacy = null;
                mat.execute(input);
                try {
                    pharmacy = mat.get();
                    String query = pharmacy.get("FacilityBuildingNo") +
                            " " +
                            pharmacy.get("FacilityStreetName");
                    String geoUri = "geo:"+query+"?z=12";
                    Uri geo = Uri.parse(geoUri);
                    Intent intent = new Intent(Intent.ACTION_VIEW,geo);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });
        return rootView;
    }
}
