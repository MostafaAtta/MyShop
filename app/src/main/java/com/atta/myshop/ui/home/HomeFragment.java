package com.atta.myshop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.myshop.R;
import com.atta.myshop.model.APIClient;
import com.atta.myshop.model.CategoriesAdapter;
import com.atta.myshop.model.CategoriesResult;
import com.atta.myshop.model.Category;
import com.atta.myshop.model.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;

    CategoriesAdapter myAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recycler);

        getCategories();

        String favCat = SessionManager.getInstance(getContext()).getCategoryName();
        Toast.makeText(getContext(), favCat, Toast.LENGTH_SHORT).show();

        return root;
    }


    public void getCategories() {

        //defining the call
        Call<CategoriesResult> call = APIClient.getInstance().getApi().getCategories();

        //calling the api
        call.enqueue(new Callback<CategoriesResult>() {
            @Override
            public void onResponse(Call<CategoriesResult> call, Response<CategoriesResult> response) {

                if (response.body() != null){
                    if (response.body().getCategories() != null){

                        ArrayList<Category> categories = response.body().getCategories();

                        if (categories.size() > 0){

                            showRecyclerView(categories);
                        }else {
                            showMessage("Categories not found , try again later");
                        }

                    }
                }else {
                    showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<CategoriesResult> call, Throwable t) {

                showMessage(t.getMessage());
            }
        });
    }

    public void showMessage(String error) {

        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    public void showRecyclerView(ArrayList<Category> categories) {

        myAdapter = new CategoriesAdapter(categories, getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(myAdapter);

    }

}