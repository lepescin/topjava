package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int MEAL_ID = UserTestData.ADMIN_ID + 1;
    public static final int NOT_FOUND_MEAL_ID = 10;


    public static final Meal userMeal1 = new Meal(MEAL_ID, LocalDateTime.of(2020,
            Month.JANUARY, 30, 10, 0), "Завтрак 1", 500);
    public static final Meal userMeal2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2020,
            Month.JANUARY, 30, 13, 0), "Обед 1", 1000);
    public static final Meal userMeal3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2020,
            Month.JANUARY, 30, 20, 0), "Ужин 1", 500);
    public static final Meal userMeal4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2020,
            Month.JANUARY, 31, 0, 0), "Еда на граничное значение 1", 100);
    public static final Meal userMeal5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2020,
            Month.JANUARY, 31, 10, 0), "Завтрак 1", 1000);
    public static final Meal userMeal6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2020,
            Month.JANUARY, 31, 13, 0), "Обед 1", 500);
    public static final Meal userMeal7 = new Meal(MEAL_ID + 6, LocalDateTime.of(2020,
            Month.JANUARY, 31, 20, 0), "Ужин 1", 410);

    public static final Meal adminMeal1 = new Meal(MEAL_ID + 7, LocalDateTime.of(2020, Month.JANUARY,
            30, 10, 0), "Завтрак 2", 500);
    public static final Meal adminMeal2 = new Meal(MEAL_ID + 8, LocalDateTime.of(2020, Month.JANUARY,
            30, 13, 0), "Обед 2", 1000);
    public static final Meal adminMeal3 = new Meal(MEAL_ID + 9, LocalDateTime.of(2020, Month.JANUARY,
            30, 20, 0), "Ужин 2", 500);
    public static final Meal adminMeal4 = new Meal(MEAL_ID + 10, LocalDateTime.of(2020, Month.JANUARY,
            31, 0, 0), "Еда на граничное значение 2", 100);
    public static final Meal adminMeal5 = new Meal(MEAL_ID + 11, LocalDateTime.of(2020, Month.JANUARY,
            31, 10, 0), "Завтрак 2", 1000);
    public static final Meal adminMeal6 = new Meal(MEAL_ID + 12, LocalDateTime.of(2020, Month.JANUARY,
            31, 13, 0), "Обед 2", 500);
    public static final Meal adminMeal7 = new Meal(MEAL_ID + 13, LocalDateTime.of(2020, Month.JANUARY,
            31, 20, 0), "Ужин 2", 410);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 24, 1, 1),
                "new", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setDateTime(LocalDateTime.of(2021, Month.FEBRUARY, 24, 1, 1));
        updated.setDescription("updated");
        updated.setCalories(500);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
