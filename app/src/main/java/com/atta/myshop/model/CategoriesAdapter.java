package com.atta.myshop.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.myshop.R;
import com.atta.myshop.ui.home.HomeFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private List<Category> categories;
    private Activity activity;


    public CategoriesAdapter(ArrayList<Category> data, Activity activity) {

        this.categories = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        final Category category = categories.get(position) ;

        final int id = category.getId();
        final String image = category.getImage();
        final String name = category.getCategory();

        holder.categoryName.setText(name);


        if (category.getImage() != null) {

            final String imageURL = APIClient.Images_BASE_URL + image;
            Picasso.get()
                    .load(imageURL)
                    .resize(120, 120)
                    .into(holder.categoryImage);
        }

        holder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager.getInstance(activity).setCategoryName(name);
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation
                        .findNavController(activity, R.id.nav_host_fragment)
                        .navigate(HomeFragmentDirections.actionNavigationHomeToProductsFragment2(category.getId()));
            }
        });



    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage, favImage;

        MyViewHolder(View view) {
            super(view);
            categoryName = view.findViewById(R.id.name_txt);
            categoryImage = view.findViewById(R.id.imageView);
            favImage = view.findViewById(R.id.favImage);

        }
    }

    public List<Category> getList(){
        return categories;
    }

}
