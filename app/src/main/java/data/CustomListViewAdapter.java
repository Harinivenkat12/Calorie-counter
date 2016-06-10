package data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import model.*;
import myandroid.myproject.caloriescounter.FoodDetailsActivity;
import writetoandreadfromfile.myandroid.achitu.caloriescounter.R;

import java.util.ArrayList;

/**
 * Created by achitu on 6/8/16.
 */
public class CustomListViewAdapter extends ArrayAdapter<Food> {

    private int layoutResource;
    private Activity activity;
    private ArrayList<Food> foodList= new ArrayList<>();

    public CustomListViewAdapter(Activity act, int resource, ArrayList<Food> data) {
        super(act, resource,data);
        layoutResource= resource;
        activity=act;
        foodList=data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Food getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public int getPosition(Food item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row= convertView;
        ViewHolder holder= null;

        if(row== null || row.getTag()==null){

            LayoutInflater inflater= LayoutInflater.from(activity);
            row=inflater.inflate(layoutResource,null);

            holder= new ViewHolder();

            holder.foodName= (TextView) row.findViewById(R.id.foodTextId);
            holder.foodDate=(TextView) row.findViewById(R.id.dateTextid);
            holder.foodCalories=(TextView) row.findViewById(R.id.caloriesId);

            row.setTag(holder);
        } else{

            holder=(ViewHolder) row.getTag();
        }

        holder.food= getItem(position);

        holder.foodName.setText(holder.food.getFoodName());
        holder.foodDate.setText(holder.food.getRecordDate());
        holder.foodCalories.setText(String.valueOf(holder.food.getCalories()));

        final ViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(activity, FoodDetailsActivity.class);

                Bundle mBundle=new Bundle();
                mBundle.putSerializable("userObj", finalHolder.food);
                i.putExtras(mBundle);

                activity.startActivity(i);

            }
        });


        return row;
    }


    public class ViewHolder{

        Food food;
        TextView foodName;
        TextView foodCalories;
        TextView foodDate;
    }
}