package edu.pace.cs6392016.team4.pharmacymap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
/*
CSV Header for reference
FacilityPK,FacilityName,FacilityTypeDesc,FacilityBuildingNo,FacilityStreetName,
FacilityAptNo,FacilityCity,FacilityState,FacilityZipcode,Latitude,Longitude,
FacilityPhone,ContactPhone,ContactPhoneExt,ContactName,AdditionalInfo,PricingDesc1,
PricingDesc2,PricingDesc3,PricingDesc4,PricingDesc5
 */


public class PharmacyDataRead {
    ArrayList<CSVRecord> records;
    public PharmacyDataRead() {
        try {
            Reader in = new FileReader("New_York_City_Locations_Providing_Seasonal_Flu_Vaccinations.csv");
            CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            records = new ArrayList<CSVRecord>(parse.getRecords());
        }

        catch (Exception e) {

        }

    }

    // Return pharmacies csvrecord in same zipcode as user
    public CSVRecord[] getPharmaciesByZipcode(String userZipCode) {
        ArrayList<CSVRecord> matchingZips = new ArrayList<CSVRecord>();
        for (CSVRecord r : records) {
            String zip = r.get("FacilityZipcode");
            if (zip.equals(userZipCode)) {
                matchingZips.add(r);
            }
        }
        CSVRecord[] out = new CSVRecord[matchingZips.size()];
        return matchingZips.toArray(out);
    }




}
