package com.mobiquity.packer;

import com.mobiquity.domain.PackDetails;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h1>Package Sorter</h1>
 * <p>This PackageSorter program helps in sorting of package
 * and appends it in StringBuilder.</p>
 *
 * @author  Vikash Chandrasekar
 * @version 1.0
 * @since   2020-03-25
 */

@Builder
public class PackageSorter {

    private double totalWeight;
    private List<PackDetails> totalPackage;
    public List<List<PackDetails>> pair;

    /**
     * This method will map the totalWeight and packDetails to the object.
     *
     * @param totalWeight This is the first parameter which hold maximum weight of a package can hold.
     * @param packDetails This is the second parameter which holds the list of object(package).
     * @return StringBuilder This returns the string with new line appended.
     */

    public static StringBuilder mapPackage(int totalWeight, List<PackDetails> packDetails) {
        //mapping the values to the object PackageSorter packer.
        PackageSorter packer = PackageSorter.builder().totalWeight(totalWeight).totalPackage(packDetails).build();
        StringBuilder stringBuilder = packer.packageSorting();
        return stringBuilder.append("\n");
    }

    /**
     * This method sorts appends the packages and append it to the final string response
     *
     * @return StringBuilder This returns the sorted package string.
     */
    private StringBuilder packageSorting() {
        List<PackDetails> sortedPackage = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        //looping totalPacking and remove if weight is greater than totalWeight
        totalPackage.removeIf(i -> i.getWeight() > totalWeight);
        pair = createPackages();
        if (pair.size() == 0) {
            stringBuilder.append("-");
        } else {
            List<PackDetails> finalPackages = filterFinalPackages();
            //sorting the package
            Collections.sort(finalPackages);
            sortedPackage.addAll(finalPackages);
            if (sortedPackage.size() > 0) {
                boolean isFirst = true;
                for (PackDetails packDetails1 : sortedPackage) {
                    if (isFirst) {
                        stringBuilder.append(packDetails1.getId());
                        isFirst = false;
                    } else {
                        stringBuilder.append(",").append(packDetails1.getId());
                    }
                }
            }
        }
        return stringBuilder;
    }

    /**
     * This method creates the combination of packages in the recursive method
     *
     * @return List<List < PackaDetails>> - This returns list of list of combination packages
     */
    private List<List<PackDetails>> createPackages() {
        pair = new ArrayList<>();
        for (PackDetails packDetails1 : totalPackage) { //loop through every item
            int packageCombinationsSize = pair.size();
            for (int y = 0; y < packageCombinationsSize; y++) {
                List<PackDetails> combination = pair.get(y);
                List<PackDetails> finalCombination = new ArrayList<>(combination);
                finalCombination.add(packDetails1);
                pair.add(finalCombination);
            }
            //forms the pair of packages
            List<PackDetails> current = new ArrayList<>();
            current.add(packDetails1);
            pair.add(current);
        }
        return pair;
    }

    /**
     * This method forms the best combination of the given packages and returns a list of it.
     *
     * @return List This returns the list of final package details before sorting.
     */
    private List<PackDetails> filterFinalPackages() {
        List<PackDetails> bestCombination = new ArrayList<>();
        double maxCost = 0;
        double maxWeight = 100; //max weight is 100
        for (List<PackDetails> packDetails : pair) {
            //adding all the weights
            double weight = packDetails.stream().mapToDouble(i -> i.getWeight()).sum();
            if (weight < totalWeight) {
                //adding all the prices
                double price = packDetails.stream().mapToDouble(i -> i.getPrice()).sum();
                if (price > maxCost) {
                    maxCost = price;
                    bestCombination = packDetails;
                    maxWeight = weight;
                } else if (price == maxCost) {
                    if (weight < maxWeight) {
                        maxCost = price;
                        bestCombination = packDetails;
                        maxWeight = weight;
                    }
                }
            }
        }
        return bestCombination;
    }
}
