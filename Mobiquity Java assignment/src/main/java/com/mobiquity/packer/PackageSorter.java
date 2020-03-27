package com.mobiquity.packer;

import com.mobiquity.domain.PackDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PackageSorter {

    private double totalWeight;
    private List<PackDetails> totalPackage;
    public List<List<PackDetails>> packageCombinations;

    public PackageSorter(double maxWeight, List<PackDetails> packDetails) {
        totalWeight = maxWeight;
        totalPackage = packDetails;
        packageCombinations = new ArrayList<>();
    }

    public StringBuilder sortPackage(int totalWeight, List<PackDetails> packDetails) {
        PackageSorter packer = new PackageSorter(totalWeight, packDetails);
        StringBuilder stringBuilder = packer.packageSorting();
        return stringBuilder.append("\n");
    }

    public StringBuilder packageSorting() {
        List<PackDetails> sortedPackage = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        totalPackage.removeIf(i -> i.getWeight() > totalWeight);
        packageCombinations = createPackages();
        if (packageCombinations.size() == 0) {
            stringBuilder.append("-");
        } else {
            List<PackDetails> finalPackages = filterFinalPackages();
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

    public List<List<PackDetails>> createPackages() {
        //loop through every item
        for (PackDetails packDetails1 : totalPackage) {
            int packageCombinationsSize = packageCombinations.size();
            for (int y = 0; y < packageCombinationsSize; y++) {
                List<PackDetails> combination = packageCombinations.get(y);
                List<PackDetails> finalCombination = new ArrayList<>(combination);
                finalCombination.add(packDetails1);
                packageCombinations.add(finalCombination);
            }
            List<PackDetails> current = new ArrayList<>();
            current.add(packDetails1);
            packageCombinations.add(current);
        }
        return packageCombinations;
    }

    public List<PackDetails> filterFinalPackages() {
        List<PackDetails> bestCombination = new ArrayList<>();
        double maxCost = 0;
        double maxWeight = 100; //max weight is 100
        for (List<PackDetails> combination : packageCombinations) {
            double combinationWeight = combination.stream().mapToDouble(i -> i.getWeight()).sum();
            if (combinationWeight < totalWeight) {
                double combinationPrice = combination.stream().mapToDouble(i -> i.getPrice()).sum();
                if (combinationPrice > maxCost) {
                    maxCost = combinationPrice;
                    bestCombination = combination;
                    maxWeight = combinationWeight;
                } else if (combinationPrice == maxCost) {    //use lightest weight
                    if (combinationWeight < maxWeight) {
                        maxCost = combinationPrice;
                        bestCombination = combination;
                        maxWeight = combinationWeight;
                    }
                }
            }
        }
        return bestCombination;
    }
}
