package writetoandreadfromfile.myandroid.achitu.caloriescounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.DataBasehandler;
import model.Food;
import myandroid.myproject.caloriescounter.DisplayFoodActivity;

public class MainActivity extends AppCompatActivity {

    private EditText foodName, foodCals;
    private Button submitButton;

    private DataBasehandler dba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodName=(EditText)findViewById(R.id.foodnameId);
        foodCals=(EditText)findViewById(R.id.caloriesNumId);
        submitButton=(Button)findViewById(R.id.submitButtonId);

        dba= new DataBasehandler(MainActivity.this);
        
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 saveDatatoDB();
            }
        });

    }

    private void saveDatatoDB() {

        Food food= new Food();
        String name= foodName.getText().toString().trim();
        String calsString= foodCals.getText().toString().trim();

        int cals= Integer.parseInt(calsString);

        if(name=="" || calsString==""){
            Toast.makeText(getApplicationContext(),"Fields cannot be empty", Toast.LENGTH_LONG).show();
        } else {
            food.setFoodName(name);
            food.setCalories(cals);

            dba.addFoodItems(food);
            dba.close();

            //clear the form
            foodName.setText("");
            foodCals.setText("");

            //take users to next screen
            startActivity(new Intent(getApplicationContext(), DisplayFoodActivity.class));
        }

























    }
}
