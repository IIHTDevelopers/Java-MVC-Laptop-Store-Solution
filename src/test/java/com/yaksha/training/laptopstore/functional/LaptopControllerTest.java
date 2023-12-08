package com.yaksha.training.laptopstore.functional;

import com.yaksha.training.laptopstore.controller.LaptopController;
import com.yaksha.training.laptopstore.entity.Laptop;
import com.yaksha.training.laptopstore.service.LaptopService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.yaksha.training.laptopstore.utils.MasterData.asJsonString;
import static com.yaksha.training.laptopstore.utils.MasterData.getLaptop;
import static com.yaksha.training.laptopstore.utils.MasterData.getLaptopList;
import static com.yaksha.training.laptopstore.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.laptopstore.utils.TestUtils.businessTestFile;
import static com.yaksha.training.laptopstore.utils.TestUtils.currentTest;
import static com.yaksha.training.laptopstore.utils.TestUtils.testReport;
import static com.yaksha.training.laptopstore.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class LaptopControllerTest {

    @Mock
    private LaptopService laptopService;

    @InjectMocks
    private LaptopController laptopController;

    private MockMvc mockMvc;

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
    public void testControllerListLaptopsHome() throws Exception {
        List<Laptop> expected = getLaptopList(5);
        when(laptopService.getLaptops()).thenReturn(expected);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-laptops")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("laptops")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerListLaptops() throws Exception {
        List<Laptop> expected = getLaptopList(5);
        when(laptopService.getLaptops()).thenReturn(expected);
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-laptops")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("laptops")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/addLaptopForm")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-laptop-form"), businessTestFile);
    }

    @Test
    public void testControllerSaveLaptop() throws Exception {
        Laptop laptop = getLaptop();
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/laptop/list"), businessTestFile);
    }

    @Test
    public void testControllerSaveLaptopHasError() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setId(null);
        laptop.setName(null);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-laptop-form"), businessTestFile);
    }

    @Test
    public void testControllerUpdateLaptopHasError() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setBrand(null);
        MvcResult result = this.mockMvc.perform(post("/saveLaptop")
                .flashAttr("laptop", laptop)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-laptop-form"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Laptop laptop = getLaptop();
        when(laptopService.getLaptop(laptop.getId())).thenReturn(laptop);
        MvcResult result = this.mockMvc.perform(get("/updateLaptopForm")
                .param("laptopId", laptop.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-laptop-form"), businessTestFile);
    }

    @Test
    public void testControllerDeleteLaptop() throws Exception {
        Laptop laptop = getLaptop();
        MvcResult result = this.mockMvc.perform(get("/delete")
                .param("laptopId", laptop.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/laptop/list"), businessTestFile);
    }

    @Test
    public void testControllerSearchLaptops() throws Exception {
        String key = randomStringWithSize(2);
        List<Laptop> expected = getLaptopList(5);
        when(laptopService.searchLaptops(key)).thenReturn(expected);
        MvcResult result = this.mockMvc.perform(post("/search")
                .param("theSearchName", key)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-laptops")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("laptops")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

}
