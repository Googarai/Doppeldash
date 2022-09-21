package com.example.doppeldash;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View restRecyc = inflater.inflate(R.layout.fragment_restaurant, container, false);

        RecyclerView restRecycView = (RecyclerView) restRecyc.findViewById(R.id.restListRecycler);
        restRecycView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        RestaurantData data = RestaurantData.get(); // retrieve restaurant data object (list of restaurants)
        RestaurantAdapter restAdapt = new RestaurantAdapter(data);

        restRecycView.setAdapter(restAdapt);


        // Inflate the layout for this fragment
        return restRecyc;
    }

    private class RestaurantAdapter extends RecyclerView.Adapter<RestVHolder>{
        private RestaurantData data;

        public RestaurantAdapter (RestaurantData newData) {
            this.data = newData;
        }


        @Override
        public int getItemCount() {
            return data.getSize();
        }

        @NonNull
        @Override
        public RestVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater restVLI = LayoutInflater.from(getActivity());
            return new RestVHolder(restVLI, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RestVHolder rvhn, int position) {
            rvhn.bind(data.getRestaurant(position));


        }


    }

    private class RestVHolder extends RecyclerView.ViewHolder{

        private ImageView restLogo;
        private TextView restName;
        private Button restMenu;

        public RestVHolder(LayoutInflater rLI, ViewGroup parent){
            super(rLI.inflate(R.layout.restaurant_layout, parent, false));

            restLogo = (ImageView) itemView.findViewById(R.id.restLogo);
            restName = (TextView) itemView.findViewById(R.id.restName);
            restMenu = (Button) itemView.findViewById(R.id.restMenuButton);
        }

        public void bind(Restaurant newRItem){
            String uri = "@drawable/" + newRItem.getImage();
            int imageResource = getResources().getIdentifier(uri, "drawable", getContext().getPackageName());
            restLogo.setImageResource(imageResource);
            restName.setText(newRItem.getName());
        }

    }
}