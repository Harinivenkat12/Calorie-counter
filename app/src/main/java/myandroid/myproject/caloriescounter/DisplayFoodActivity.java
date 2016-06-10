package myandroid.myproject.caloriescounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import data.CustomListViewAdapter;
import data.DataBasehandler;
import model.Food;
import util.Util;
import writetoandreadfromfile.myandroid.achitu.caloriescounter.R;

public class DisplayFoodActivity extends AppCompatActivity {
    private DataBasehandler dba;
    private ListView listView;
    private CustomListViewAdapter foodadapter;
    private ArrayList<Food> dbFoods= new ArrayList<>();

    private Food myFood;
    private TextView totalCals, totalFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_food_activity);

        listView=(ListView) findViewById(R.id.listViewId);
        totalCals=(TextView)findViewById(R.id.totalFoodCaloriesTxtVwId);
        totalFoods=(TextView)findViewById(R.id.totalFoodItemsTxtVwId);
        
        refreshData();

    }


    private void refreshData() {

        dbFoods.clear();

        dba= new DataBasehandler(getApplicationContext());

        ArrayList<Food> foodFromDB= dba.getFoods();

        int totalCalories= dba.getTotalCals();
        int totalItems= dba.getTotalItems();

        String formattedCals= Util.formatNumber(totalCalories);
        String  formattedItems= Util.formatNumber(totalItems);

        totalCals.setText("Total Calories : " +formattedCals);
        totalFoods.setText("Total Foods :" +formattedItems);

        for(int i=0; i<foodFromDB.size(); i++){
            String name= foodFromDB.get(i).getFoodName();
            String dateText= foodFromDB.get(i).getRecordDate();
            int cals= foodFromDB.get(i).getCalories();
            int foodId= foodFromDB.get(i).getFoodId();

            Log.v("FOOD IDs :", String .valueOf(foodId));

            myFood=new Food();
            myFood.setFoodName(name);
            myFood.setRecordDate(dateText);
            myFood.setCalories(cals);
            myFood.setFoodId(foodId);

            dbFoods.add(myFood);
        }
        dba.close();

        //set up adapter
         foodadapter= new CustomListViewAdapter(DisplayFoodActivity.this,R.layout.list_row,dbFoods);
        listView.setAdapter(foodadapter);
        foodadapter.notifyDataSetChanged();


    }

}
