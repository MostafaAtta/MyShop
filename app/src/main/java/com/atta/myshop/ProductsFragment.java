package com.atta.myshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.myshop.model.APIClient;
import com.atta.myshop.model.Product;
import com.atta.myshop.model.ProductsAdapter;
import com.atta.myshop.model.ProductsResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends Fragment {

    View root;

    RecyclerView recyclerView;

    ProductsAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_products, container, false);


        recyclerView = root.findViewById(R.id.products_recycler);

        int catId = ProductsFragmentArgs.fromBundle(getArguments()).getCatId();

        getProducts(catId);

        return root;
    }

    public void getProducts(int catId){

        Call<ProductsResult> call = APIClient.getInstance().getApi().getProducts(catId);


        call.enqueue(new Callback<ProductsResult>() {
            @Override
            public void onResponse(Call<ProductsResult> call, Response<ProductsResult> response) {

                if (response.body() != null){
                    if (response.body().getProducts() != null){

                        ArrayList<Product> products = response.body().getProducts();

                        showRecyclerView(products);

                    }
                }

            }

            @Override
            public void onFailure(Call<ProductsResult> call, Throwable t) {

            }
        });


    }


    public void showRecyclerView(ArrayList<Product> products) {

        myAdapter = new ProductsAdapter(products, getContext());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerView.setAdapter(myAdapter);
    }
}