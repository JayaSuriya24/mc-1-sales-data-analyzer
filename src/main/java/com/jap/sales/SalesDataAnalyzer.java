package com.jap.sales;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalesDataAnalyzer {
    private List<SalesRecord> salesRecords;

    // Read the data from the file and store in a List
    public List<SalesRecord> readFile(String fileName) {
        List<SalesRecord> salesRecordList = new ArrayList<>();
        // read the CSV file
        try {
            FileReader fis = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fis);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                String date = split[0];
                int customerID = Integer.parseInt(split[1]);
                int productCategory = Integer.parseInt(split[2]);
                String paymentMethod = split[3];
                double value = Double.parseDouble(split[4]);
                double timeOnSite = Double.parseDouble(split[5]);
                int clicksInSite = Integer.parseInt(split[6]);
                salesRecordList.add(new SalesRecord(date,
                        customerID,
                        productCategory,
                        paymentMethod,
                        value,
                        timeOnSite,
                        clicksInSite));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // get one row at a time
        // read the values from the row
        // use the values to create an object
        // add that object in a list
        // return the list
        return salesRecordList;
    }

    // Sort the customers based on purchase amount
    public List<SalesRecord> getAllCustomersSortedByPurchaseAmount(List<SalesRecord> salesData, AmountComparator amountComparator) {
        // sort the sales record list, according to the criteria set by the amountComparator parameter
        Collections.sort(salesData, amountComparator);
        return salesData;
    }

    // Find the top customer who spent the maximum time on the site
    public SalesRecord getTopCustomerWhoSpentMaxTimeOnSite(List<SalesRecord> salesData, TimeOnSiteComparator timeOnSiteComparator) {
        Collections.sort(salesData, timeOnSiteComparator);
        return salesData.get(0);
    }
}
