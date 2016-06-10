package myandroid.myproject.caloriescounter;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DataBasehandler;
import model.Food;
import writetoandreadfromfile.myandroid.achitu.caloriescounter.R;

public class FoodDetailsActivity extends AppCompatActivity {

    private TextView foodname, calories, dateTaken;
    private Button shareButton;

    private int foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details_activity);

        foodname = (TextView) findViewById(R.id.detsFoodNameId);
        calories = (TextView) findViewById(R.id.detsCaloriesId);
        dateTaken = (TextView) findViewById(R.id.detsDatetakenId);

        shareButton = (Button) findViewById(R.id.detsShareBtnId);

        Food foods = (Food) getIntent().getSerializableExtra("userObj");

        foodname.setText(foods.getFoodName());
        calories.setText(String.valueOf(foods.getCalories()));
        dateTaken.setText(foods.getRecordDate());

        foodId = foods.getFoodId();

        calories.setTextSize(35f);
        calories.setTextColor(Color.BLUE);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCalories();
            }
        });
    }

    public void shareCalories() {

        StringBuilder dataString = new StringBuilder();

        String foodName = foodname.getText().toString();
        String cals = calories.getText().toString();
        String date = dateTaken.getText().toString();

        dataString.append(("Food Name: " + foodName + "\n"));
        dataString.append("Calories:" + cals + "\n");
        dataString.append("Consumed On: " + date + "\n");


        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT, "My Calorie Intake");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recepient@example.com"});
        i.putExtra(Intent.EXTRA_TEXT, dataString.toString());

        try {
            startActivity(Intent.createChooser(i, "Send mail ..."));

        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Please install email app before sending", Toast.LENGTH_LONG).show();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_food_items_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.deleteItemId) {

            AlertDialog.Builder alert= new AlertDialog.Builder(FoodDetailsActivity.this);

            alert.setTitle(" Delete?");
            alert.setMessage("Are you sure to delete this item?");
            alert.setNegativeButton("No", null);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DataBasehandler dba = new DataBasehandler(getApplicationContext());
                    dba.deleteFoodItems(foodId);
                    Toast.makeText(getApplicationContext(), "Food Item Deleted !", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FoodDetailsActivity.this, DisplayFoodActivity.class));

                    //remove the activity from the activity stack

                    FoodDetailsActivity.this.finish();
                }
            });
            alert.show();

        }
        return super.onOptionsItemSelected(item);

    }
}
