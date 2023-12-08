package com.yaksha.training.laptopstore.boundary;


import com.yaksha.training.laptopstore.entity.Laptop;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yaksha.training.laptopstore.utils.MasterData.getLaptop;
import static com.yaksha.training.laptopstore.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.laptopstore.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.laptopstore.utils.TestUtils.currentTest;
import static com.yaksha.training.laptopstore.utils.TestUtils.testReport;
import static com.yaksha.training.laptopstore.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testNameNotNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setName(null);
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameNotBlank() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setName("");
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMinThree() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setName(randomStringWithSize(2));
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMaxTwenty() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setName(randomStringWithSize(21));
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPriceNotNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setPrice(null);
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPriceMinZero() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setPrice(-1D);
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPriceMax9999() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setPrice(100000D);
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBrandNotNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setBrand(null);
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testBrandNotBlank() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setBrand("");
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testStorageNotNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setStorage(null);
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testStorageNotBlank() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setStorage("");
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRamNotNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setRam(null);
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRamNotBlank() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setRam("");
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testProcessorNotNull() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setProcessor(null);
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testProcessorNotBlank() throws Exception {
        Laptop laptop = getLaptop();
        laptop.setProcessor("");
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

}
