package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    void createMeal(Meal meal);

    void updateMeal(int id, Meal meal);

    void deleteMeal(int id);

    Meal getMealById(int id);

    List<Meal> getAllMeals();
}
