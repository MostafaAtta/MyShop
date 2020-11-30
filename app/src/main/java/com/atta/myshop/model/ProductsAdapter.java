package com.atta.myshop.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.myshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Product> products;
    private Context context;


    public ProductsAdapter(ArrayList<Product> data, Context context) {

        this.products = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        final Product product = products.get(position) ;

        final int id = product.getId();
        final String[] images = product.getImages();
        final String name = product.getName();
        final double price = product.getPrice();

        holder.productName.setText(name);
        holder.priceTxt.setText(String.valueOf(price + " EGP"));


            final String imageURL = APIClient.Images_BASE_URL + images[0];
            Picasso.get()
                    .setLoggingEnabled(true);

        Picasso.get()
                .load(imageURL)
                .into(holder.productImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(context.getApplicationContext(), ProductsActivity.class);
                intent.putExtra("cat_id", product.getId());
                context.startActivity(intent);
                */
            }
        });



    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        ImageView productImage;
        TextView priceTxt;

        MyViewHolder(View view) {
            super(view);
            productName = view.findViewById(R.id.name_txt);
            priceTxt = view.findViewById(R.id.price_txt);
            productImage = view.findViewById(R.id.product_imageView);

        }
    }

    public List<Product> getList(){
        return products;
    }

}
