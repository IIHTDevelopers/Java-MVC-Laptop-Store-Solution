package com.yaksha.training.laptopstore.exception;

import com.yaksha.training.laptopstore.controller.LaptopController;
import com.yaksha.training.laptopstore.entity.Laptop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static com.yaksha.training.laptopstore.utils.MasterData.getLaptop;
import static com.yaksha.training.laptopstore.utils.TestUtils.currentTest;
import static com.yaksha.training.laptopstore.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.laptopstore.utils.TestUtils.testReport;
import static com.yaksha.training.laptopstore.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class LaptopExceptionTest {

    @InjectMocks
    private LaptopController laptopController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(laptopController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSaveLaptopNameAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setId(null);
        laptop.setName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveLaptopBrandAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setId(null);
        laptop.setBrand(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveLaptopPrizeAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setId(null);
        laptop.setPrice(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveLaptopStorageAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setId(null);
        laptop.setStorage(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveLaptopRamAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setId(null);
        laptop.setRam(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveLaptopProcessAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setId(null);
        laptop.setProcessor(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-laptop-form")), exceptionTestFile);
    }


    @Test
    public void testExceptionUpdateLaptopNameAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateLaptopBrandAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setBrand(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateLaptopPrizeAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setPrice(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateLaptopStorageAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setStorage(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateLaptopRamAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setRam(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-laptop-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateLaptopProcessAsNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setProcessor(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-laptop-form")), exceptionTestFile);
    }


}