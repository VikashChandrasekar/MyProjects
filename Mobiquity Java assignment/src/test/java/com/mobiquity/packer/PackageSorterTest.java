package com.mobiquity.packer;

import com.mobiquity.domain.PackDetails;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class PackageSorterTest {

    List<List<PackDetails>> lists = new ArrayList<>();

    @Test
    public void testSortPackage(){
        int totalWeight = 81;
        List<PackDetails> packDetails = createPackDetails();
        PackageSorter packageSorter = new PackageSorter(totalWeight, packDetails);
        StringBuilder stringBuilder = packageSorter.sortPackage(totalWeight, packDetails);
        Assert.assertEquals("2\n", stringBuilder.toString());
    }

    @Test
    public void testPackageSorting(){
        int totalWeight = 81;
        List<PackDetails> packDetails = createPackDetails();
        PackageSorter packageSorter = new PackageSorter(totalWeight, packDetails);
        StringBuilder stringBuilder = packageSorter.packageSorting();
        Assert.assertEquals("2",stringBuilder.toString());
    }

    @Test
    public void testCreatePackages(){
        int totalWeight = 81;
        List<PackDetails> packDetails = createPackDetails();
        PackageSorter packageSorter = new PackageSorter(totalWeight, packDetails);
        List<List<PackDetails>> lists = packageSorter.createPackages();
        Assert.assertEquals(1,lists.get(0).get(0).getId());
        Assert.assertEquals(53.38,lists.get(0).get(0).getWeight(), 53);
        Assert.assertEquals(45.0,lists.get(0).get(0).getPrice(), 45);
        Assert.assertEquals(2,lists.get(1).get(1).getId());
        Assert.assertEquals(72.3,lists.get(1).get(1).getWeight(),73);
        Assert.assertEquals(76.0,lists.get(1).get(1).getPrice(),76);
        Assert.assertEquals(3,lists.get(3).get(1).getId());
        Assert.assertEquals(46.34,lists.get(3).get(1).getWeight(),46);
        Assert.assertEquals(48.0,lists.get(3).get(1).getPrice(),48);
    }

    @Test
    public void testFilterFinalPackages(){
        int totalWeight = 81;
        List<PackDetails> packDetails = createPackDetails();
        lists = createMorePackDetails();
        PackageSorter packageSorter = new PackageSorter(totalWeight, packDetails);
        packageSorter.packageCombinations = lists;
        List<PackDetails> packDetails1 = packageSorter.filterFinalPackages();
        Assert.assertEquals(2, packDetails1.get(0).getId());
        Assert.assertEquals(76.0, packDetails1.get(0).getPrice(), 76);
        Assert.assertEquals(73.3, packDetails1.get(0).getWeight(), 73);
    }

    private List<PackDetails> createPackDetails() {
        List<PackDetails> packDetailsList = new ArrayList<>();
        PackDetails packDetails1 = new PackDetails(1,53.38, 45.0);
        PackDetails packDetails2 = new PackDetails(2, 72.30, 76.0);
        PackDetails packDetails3 = new PackDetails(3,46.34, 48.0);
        packDetailsList.add(packDetails1);
        packDetailsList.add(packDetails2);
        packDetailsList.add(packDetails3);
        return packDetailsList;
    }

    private List<List<PackDetails>> createMorePackDetails(){
        List<List<PackDetails>> lists = new ArrayList<>();
        List<PackDetails> packDetails = new ArrayList<>();
        PackDetails packDetails1 = new PackDetails(1,53.38, 45.0);
        packDetails.add(packDetails1);
        lists.add(packDetails);
        List<PackDetails> packDetails2 = new ArrayList<>();
        PackDetails packDetails3 = new PackDetails(1,53.38, 45.0);
        PackDetails packDetails4 = new PackDetails(2, 72.30, 76.0);
        packDetails2.add(packDetails3);
        packDetails2.add(packDetails4);
        lists.add(packDetails2);
        List<PackDetails> packDetails5 = new ArrayList<>();
        PackDetails packDetails6 = new PackDetails(2, 72.30, 76.0);
        packDetails5.add(packDetails6);
        lists.add(packDetails5);
        List<PackDetails> packDetails7 = new ArrayList<>();
        PackDetails packDetails8 = new PackDetails(1,53.38, 45.0);
        PackDetails packDetails9 = new PackDetails(3,46.34, 48.0);
        packDetails7.add(packDetails8);
        packDetails7.add(packDetails9);
        lists.add(packDetails7);
        List<PackDetails> packDetails10 = new ArrayList<>();
        PackDetails packDetails11 = new PackDetails(1,53.38, 45.0);
        PackDetails packDetails12 = new PackDetails(2, 72.30, 76.0);
        PackDetails packDetails13 = new PackDetails(3,46.34, 48.0);
        packDetails10.add(packDetails11);
        packDetails10.add(packDetails12);
        packDetails10.add(packDetails13);
        lists.add(packDetails10);
        return lists;
    }

}
