package com.example.doppeldash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FoodFragment extends Fragment {

    private DoppeldashDatabase db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View foodRecyc = inflater.inflate(R.layout.fragment_food_list, container, false); // create and inflate food fragment view

        RecyclerView foodRecycView = (RecyclerView) foodRecyc.findViewById(R.id.foodListRecycler); //initialise recycler view
        foodRecycView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)); // set recycler layout manager

        FoodAdapter foodAdapter = new FoodAdapter(db);

        foodRecycView.setAdapter(foodAdapter);

        return foodRecyc;
    }

    private class FoodAdapter extends RecyclerView.Adapter<FoodVHolder>{
        private DoppeldashDatabase data;

        public FoodAdapter (DoppeldashDatabase newData) {this.data = newData;}

        @Override
        public int getItemCount() {
            return data.getNumFood();
        }


        @Override
        public FoodVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater foodVLI = LayoutInflater.from(getActivity());
            return new FoodVHolder(foodVLI, parent);
        }

        @Override
        public void onBindViewHolder(FoodVHolder fvhn, int index){
            fvhn.bind(data.getFood(index));
        }
    }

    private class FoodVHolder extends RecyclerView.ViewHolder{

        private ImageView foodImage;
        private TextView foodName;
        private TextView foodDesc;
        private TextView foodPrice;

        public FoodVHolder(LayoutInflater fLI, ViewGroup parent){
            super(fLI.inflate(R.layout.food_item_layout, parent, false));

            foodImage = (ImageView) itemView.findViewById(R.id.foodImage);
            foodName = (TextView) itemView.findViewById(R.id.foodLabel);
            foodDesc = (TextView) itemView.findViewById((R.id.foodDescription));
            foodPrice = (TextView) itemView.findViewById(R.id.foodPrice);
        }

        public void bind(Food newFItem){
            String uri = "@drawable/" + newFItem.getImage();
            int imageResource = getResources().getIdentifier(uri, "drawable", getContext().getPackageName());
            foodImage.setImageResource(imageResource);
            foodName.setText(newFItem.getName());
            foodDesc.setText(newFItem.getDescription());
            foodPrice.setText("$"+String.valueOf(newFItem.getPrice()));
        }

    }

    public void setDatabase(DoppeldashDatabase inDB)
    {
        db = inDB;
    }
}