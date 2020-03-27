package com.mobiquity.packer;

import com.mobiquity.domain.PackDetails;
import com.mobiquity.exception.APIException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>Package Challenge</h1>
 * <P>This Packer program runs an application that
 * reads the file from the filePath and sorts
 * the package with less weight and with
 * total cost as large as possible.</P>
 *
 * <P>Note: This class is only to read and to display the output.</P>
 *
 * @author  Vikash Chandrasekar
 * @version 1.0
 * @since   2020-03-25
 */

public class Packer {

    /**
     * This method is used to read each and every line of the file
     * from the filePath and to display the output finally.
     *
     * @param filePath This is the only parameter, contains file path: String
     * @return String This returns string of sorted package line by line.
     * @throws APIException This throws APIException on input error.
     */

    public static String pack(String filePath) throws APIException {
        StringBuilder finalPackage = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String rows;
            while ((rows = bufferedReader.readLine()) != null) {
                String[] lines = rows.split(" : ");
                int totalWeight = Integer.parseInt(lines[0]);
                String[] packages = lines[1].split(" ");
                //Splitting the packages based on : and space and mapping it in a list
                List<PackDetails> packDetails = Arrays.stream(packages).map(p -> p.split(","))
                        .map(Packer::getPackageDetails)
                        .collect(Collectors.toList());
                StringBuilder stringBuilder = PackageSorter.mapPackage(totalWeight, packDetails);
                finalPackage.append(stringBuilder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalPackage.toString();
    }

    /**
     * This method is used to map the values in an object PackDetails
     *
     * @param packageDetails This is the only parameter, contain Array of values which is splitted from method pack.
     * @return Object This returns Object PackDetails.
     */
    public static PackDetails getPackageDetails(String[] packageDetails) {
        int index = Integer.parseInt(packageDetails[0].substring(1));
        double weight = Double.parseDouble(packageDetails[1]);
        double price = Double.parseDouble(packageDetails[2].substring(1, packageDetails[2].length() - 1));
        return PackDetails.builder().id(index).weight(weight).price(price).build();
    }
}

