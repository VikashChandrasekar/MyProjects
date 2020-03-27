package com.mobiquity.packer;

import com.mobiquity.domain.PackDetails;
import com.mobiquity.exception.APIException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class PackerTest {

    @Mock
    private Packer packer;

    @Test
    public void testPack() throws APIException {
        File file = new File("src/test/resources/example_input");
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testPackage() throws APIException{
        File file = new File("src/test/resources/example_input");
        String finalString = packer.pack(file.getPath());
        Assert.assertEquals("4\n-\n2,7\n8,9\n", finalString);
    }

    @Test
    public void testGetPackageDetails(){
        String[] packageDetails = {"(1", "45.6", "$75)"};
        PackDetails packDetails1 = packer.getPackageDetails(packageDetails);
        Assert.assertEquals(1, packDetails1.getId());
        Assert.assertEquals(45.6, packDetails1.getWeight(), 45);
        Assert.assertEquals(75.0, packDetails1.getPrice(), 75);
    }
}