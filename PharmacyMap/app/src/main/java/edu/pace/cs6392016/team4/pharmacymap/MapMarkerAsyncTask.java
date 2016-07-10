package edu.pace.cs6392016.team4.pharmacymap;

import android.os.AsyncTask;
import org.apache.commons.csv.CSVRecord;

public class MapMarkerAsyncTask extends AsyncTask<String,Void,CSVRecord[]>{

    @Override
    protected CSVRecord[] doInBackground(String... strings) {
        String zipCode = strings[0];
        PharmacyDataRead prd = new PharmacyDataRead();
        return prd.getPharmaciesByZipcode(zipCode);
    }


}
