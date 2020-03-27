package com.mobiquity.packer;

import com.mobiquity.domain.PackDetails;
import com.mobiquity.exception.APIException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Packer {

    public static String pack(String filePath) throws APIException {
        StringBuilder finalPackage = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String rows;
            while ((rows = bufferedReader.readLine()) != null) {
                String[] lines = rows.split(" : ");
                int totalWeight = Integer.parseInt(lines[0]);
                String[] packages = lines[1].split(" ");
                List<PackDetails> packDetails = Arrays.stream(packages).map(p -> p.split(","))
                                                                        .map(Packer::getPackageDetails)
                                                                        .collect(Collectors.toList());
                PackageSorter packageSorter = new PackageSorter(totalWeight, packDetails);
                StringBuilder stringBuilder = packageSorter.sortPackage(totalWeight, packDetails);
                finalPackage.append(stringBuilder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(finalPackage.toString());
        return finalPackage.toString();
    }

    public static PackDetails getPackageDetails(String[] packageDetails) {
        int index = Integer.parseInt(packageDetails[0].substring(1));
        double weight = Double.parseDouble(packageDetails[1]);
        double price = Double.parseDouble(packageDetails[2].substring(1, packageDetails[2].length() - 1));
        return new PackDetails(index, weight, price);
    }

    //Main method only for testing purpose.
    public static void main(String[] args) throws APIException {
        pack("C:\\Users\\x018975\\Desktop\\example_inputs.txt");
    }


}

