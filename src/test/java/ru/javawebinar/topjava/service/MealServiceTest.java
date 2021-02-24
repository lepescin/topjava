package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    public MealServiceTest() {
    }

    @Test
    public void create() {
        Meal created = mealService.create(getNew(), UserTestData.USER_ID);
        Integer createdId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(createdId);
        assertMatch(created, newMeal);
        assertMatch(mealService.get(createdId, UserTestData.USER_ID), newMeal);
    }

    @Test
    public void duplicatedDateTimeCreate() {
        LocalDateTime dateTime = userMeal1.getDateTime();
        Meal newMeal = getNew();
        newMeal.setDateTime(dateTime);
        assertThrows(DuplicateKeyException.class, () -> mealService.create(newMeal, UserTestData.USER_ID));
    }

    @Test
    public void get() {
        Meal meal = mealService.get(userMeal1.getId(), UserTestData.USER_ID);
        assertMatch(meal, userMeal1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                mealService.get(NOT_FOUND_MEAL_ID, UserTestData.USER_ID));
    }

    @Test
    public void getAll() {
        List<Meal> userMeals = mealService.getAll(UserTestData.USER_ID);
        List<Meal> adminMeals = mealService.getAll(UserTestData.ADMIN_ID);
        assertMatch(userMeals, Arrays.asList(
                userMeal7, userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1));
        assertMatch(adminMeals, Arrays.asList(
                adminMeal7, adminMeal6, adminMeal5, adminMeal4, adminMeal3, adminMeal2, adminMeal1));
    }

    @Test
    public void update() {
        mealService.update(getUpdated(), UserTestData.USER_ID);
        Meal updated = getUpdated();
        assertMatch(mealService.get(updated.getId(), UserTestData.USER_ID), updated);
    }

    @Test
    public void delete() {
        mealService.delete(userMeal1.getId(), UserTestData.USER_ID);
        assertThrows(NotFoundException.class, () ->
                mealService.delete(userMeal1.getId(), UserTestData.USER_ID));
    }

}