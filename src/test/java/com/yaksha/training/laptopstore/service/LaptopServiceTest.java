package com.yaksha.training.laptopstore.service;

import com.yaksha.training.laptopstore.entity.Laptop;
import com.yaksha.training.laptopstore.repository.LaptopRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
        Pageable pageable = PageRequest.of(0, 5);
        Page<Laptop> laptopPage = new PageImpl<>(actual);
        when(laptopRepository.findAllLaptop(pageable)).thenReturn(laptopPage);
        Page<Laptop> expected = laptopService.getLaptops(pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
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
        Pageable pageable = PageRequest.of(0, 5);
        Page<Laptop> laptopPage = new PageImpl<>(actual);
        when(laptopRepository.findAllLaptop(pageable)).thenReturn(laptopPage);
        Page<Laptop> expected = laptopService.searchLaptops(null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchLaptopWithSearchName() throws Exception {
        String searchKey = randomStringWithSize(2);
        List<Laptop> actual = getLaptopList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Laptop> laptopPage = new PageImpl<>(actual);
        when(laptopRepository.findByNameOrBrandOrProcessor(searchKey, pageable)).thenReturn(laptopPage);
        Page<Laptop> expected = laptopService.searchLaptops(searchKey, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceUpdateBestSeller() throws Exception {
        Laptop actual = getLaptop();
        boolean expected = laptopService.updateBestSeller(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

}
