package com.yaksha.training.laptopstore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.training.laptopstore.entity.Laptop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    private static Random rnd = new Random();

    public static Laptop getLaptop() {
        Laptop laptop = new Laptop();
        laptop.setId(1L);
        laptop.setName(randomStringWithSize(10));
        laptop.setBrand(randomStringWithSize(10));
        laptop.setPrice(1000D);
        laptop.setStorage(randomStringWithSize(10));
        laptop.setRam(randomStringWithSize(10));
        laptop.setProcessor(randomStringWithSize(10));
        return laptop;
    }

    public static List<Laptop> getLaptopList(int size) {
        Long id = 0L;
        List<Laptop> laptops = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Laptop laptop = new Laptop();
            laptop.setId(++id);
            laptop.setName(randomStringWithSize(10));
            laptop.setBrand(randomStringWithSize(10));
            laptop.setPrice(1000D);
            laptop.setStorage(randomStringWithSize(10));
            laptop.setRam(randomStringWithSize(10));
            laptop.setProcessor(randomStringWithSize(10));
            laptops.add(laptop);
        }
        return laptops;
    }


    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
