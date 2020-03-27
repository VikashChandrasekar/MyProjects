package com.mobiquity.packer;

import com.mobiquity.domain.PackDetails;
import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * This PackageSorterTest runs the unit test cases for the java class PackageSorter.java
 *
 * @author  Vikash Chandrasekar
 * @version 1.0
 * @since   2020-03-25
 */

public class PackageSorterTest {

    List<List<PackDetails>> lists;

    @Test
    public void testSortPackage(){
        PackageSorter packageSorter = PackageSorter.builder().totalWeight(81).totalPackage(createPackDetails()).build();
        StringBuilder stringBuilder = packageSorter.mapPackage(81, createPackDetails());
        Assert.assertEquals("2\n", stringBuilder.toString());
    }

    @Test
    public void testPackageSorting() throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        PackageSorter packageSorter = PackageSorter.builder().totalWeight(81).totalPackage(createPackDetails()).build();
        Method method = PackageSorter.class.getDeclaredMethod("packageSorting");
        method.setAccessible(true);
        StringBuilder stringBuilder = (StringBuilder) method.invoke(packageSorter);
        Assert.assertEquals("2",stringBuilder.toString());
    }

    @Test
    public void testCreatePackages() throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        PackageSorter packageSorter = PackageSorter.builder().totalWeight(81).totalPackage(createPackDetails()).build();
        Method method = PackageSorter.class.getDeclaredMethod("createPackages");
        method.setAccessible(true);
        List<List<PackDetails>> lists = (List<List<PackDetails>>) method.invoke(packageSorter);
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
    public void testFilterFinalPackages() throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        PackageSorter packageSorter = PackageSorter.builder().totalWeight(81).totalPackage(createPackDetails()).build();
        lists = createMorePackDetails();
        packageSorter.pair = lists;
        Method method = PackageSorter.class.getDeclaredMethod("filterFinalPackages");
        method.setAccessible(true);
        List<PackDetails> packDetails1 = (List<PackDetails>) method.invoke(packageSorter);
        Assert.assertEquals(2, packDetails1.get(0).getId());
        Assert.assertEquals(76.0, packDetails1.get(0).getPrice(), 76);
        Assert.assertEquals(73.3, packDetails1.get(0).getWeight(), 73);
    }

    /**
     * This method maps the harcoded values into a list of packDeetails
     *
     * @return List - This retuns the list of Hardcoded package details for mock
     */
    private List<PackDetails> createPackDetails() {
        List<PackDetails> packDetailsList = new ArrayList<>();
        PackDetails packDetails1 = PackDetails.builder().id(1).weight(53.38).price(45.0).build();
        PackDetails packDetails2 = PackDetails.builder().id(2).weight(72.30).price( 76.0).build();
        PackDetails packDetails3 = PackDetails.builder().id(3).weight(46.34).price(48.0).build();
        packDetailsList.add(packDetails1);
        packDetailsList.add(packDetails2);
        packDetailsList.add(packDetails3);
        return packDetailsList;
    }

    /**
     * This method maps the hardcoded values into a list of list
     *
     * @return List of List - This returns list of list contains hardcoded package details in recursive combination
     */
    private List<List<PackDetails>> createMorePackDetails(){
        List<List<PackDetails>> lists = new ArrayList<>();
        List<PackDetails> packDetails = new ArrayList<>();
        PackDetails packDetails1 = PackDetails.builder().id(1).weight(53.38).price(45.0).build();
        packDetails.add(packDetails1);
        lists.add(packDetails);
        List<PackDetails> packDetails2 = new ArrayList<>();
        PackDetails packDetails3 = PackDetails.builder().id(1).weight(53.38).price(45.0).build();
        PackDetails packDetails4 = PackDetails.builder().id(2).weight(72.30).price(76.0).build();
        packDetails2.add(packDetails3);
        packDetails2.add(packDetails4);
        lists.add(packDetails2);
        List<PackDetails> packDetails5 = new ArrayList<>();
        PackDetails packDetails6 = PackDetails.builder().id(2).weight(72.30).price(76.0).build();
        packDetails5.add(packDetails6);
        lists.add(packDetails5);
        List<PackDetails> packDetails7 = new ArrayList<>();
        PackDetails packDetails8 = PackDetails.builder().id(1).weight(53.38).price(45.0).build();
        PackDetails packDetails9 = PackDetails.builder().id(3).weight(46.34).price(48.0).build();
        packDetails7.add(packDetails8);
        packDetails7.add(packDetails9);
        lists.add(packDetails7);
        List<PackDetails> packDetails10 = new ArrayList<>();
        PackDetails packDetails11 = PackDetails.builder().id(1).weight(53.38).price(45.0).build();
        PackDetails packDetails12 = PackDetails.builder().id(2).weight(72.30).price(76.0).build();
        PackDetails packDetails13 = PackDetails.builder().id(3).weight(46.34).price(48.0).build();
        packDetails10.add(packDetails11);
        packDetails10.add(packDetails12);
        packDetails10.add(packDetails13);
        lists.add(packDetails10);
        return lists;
    }

}
