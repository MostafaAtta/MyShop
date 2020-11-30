package com.atta.myshop.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("get_categories")
    Call<CategoriesResult> getCategories();


    @GET("get_products/{cat_id}")
    Call<ProductsResult> getProducts(
            @Path("cat_id") int catId
    );

}
