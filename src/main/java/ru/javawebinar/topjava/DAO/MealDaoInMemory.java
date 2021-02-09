package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMemory implements MealDao {
    private final AtomicInteger mealsCount = new AtomicInteger();
    private final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    public MealDaoInMemory() {
        for (Meal meal : MealsUtil.mealList) {
            mealMap.put(meal.getId(), meal);
        }
    }

    @Override
    public void createMeal(Meal meal) {
        int id = mealsCount.incrementAndGet();
        meal.setId(id);
        mealMap.put(id, meal);
    }

    @Override
    public void updateMeal(int id, Meal meal) {
        mealMap.computeIfPresent(id, (k, v) -> meal);
    }

    @Override
    public void deleteMeal(int id) {
        mealMap.remove(id);
    }

    @Override
    public Meal getMealById(int id) {
        return mealMap.get(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(mealMap.values());
    }
}
