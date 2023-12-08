package com.yaksha.training.laptopstore.service;

import com.yaksha.training.laptopstore.entity.Laptop;
import com.yaksha.training.laptopstore.repository.LaptopRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.laptopstore.utils.MasterData.asJsonString;
import static com.yaksha.training.laptopstore.utils.MasterData.getLaptop;
import static com.yaksha.training.laptopstore.utils.MasterData.getLaptopList;
import static com.yaksha.training.laptopstore.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.laptopstore.utils.TestUtils.businessTestFile;
import static com.yaksha.training.laptopstore.utils.TestUtils.currentTest;
import static com.yaksha.training.laptopstore.utils.TestUtils.testReport;
import static com.yaksha.training.laptopstore.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class LaptopServiceTest {

    @Mock
    private LaptopRepository laptopRepository;

    @InjectMocks
    private LaptopService laptopService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetLaptops() throws Exception {
        List<Laptop> actual = getLaptopList(5);
        when(laptopRepository.findAll()).thenReturn(actual);
        List<Laptop> expected = laptopService.getLaptops();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveLaptop() throws Exception {
        Laptop actual = getLaptop();
        when(laptopRepository.save(actual)).thenReturn(actual);
        Laptop expected = laptopService.saveLaptop(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetLaptop() throws Exception {
        Laptop actual = getLaptop();
        when(laptopRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Laptop expected = laptopService.getLaptop(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceDeleteLaptop() throws Exception {
        Laptop actual = getLaptop();
        boolean expected = laptopService.deleteLaptop(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

    @Test
    public void testServiceSearchLaptopWithNull() throws Exception {
        List<Laptop> actual = getLaptopList(5);
        when(laptopRepository.findAll()).thenReturn(actual);
        List<Laptop> expected = laptopService.searchLaptops(null);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchLaptopWithSearchName() throws Exception {
        String searchKey = randomStringWithSize(2);
        List<Laptop> actual = getLaptopList(5);
        when(laptopRepository.findByLaptopNameAndBrand(searchKey)).thenReturn(actual);
        List<Laptop> expected = laptopService.searchLaptops(searchKey);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

}
