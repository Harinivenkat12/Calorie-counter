package model;

import java.io.Serializable;

/**
 * Created by achitu on 6/8/16.
 */
public class Food implements Serializable {


    private static final long serialVersionUID = 10L;

    private String foodName;
    private int calories;
    private int foodId;
    private String recordDate;


    public Food(String food, int cals, int id, String date) {
        this.foodName = food;
        this.calories = cals;
        this.foodId = id;
        this.recordDate = date;
    }

    public Food(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}

