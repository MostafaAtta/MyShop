package com.atta.myshop.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.internal.bind.JsonTreeReader;

public class SessionManager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    private static SessionManager mInstance;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared Preferences file name
    private static final String PREF_NAME = "MyShop";

    // Context
    Context _context;

    public static final String CATEGORY_NAME = "categoryname";

    // Constructor
    private SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public static SessionManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SessionManager(context);
        }
        return mInstance;
    }

    public void setCategoryName(String categoryName) {
        editor.putString(CATEGORY_NAME, categoryName);

        editor.commit();
    }

    public String getCategoryName() {
        return pref.getString(CATEGORY_NAME, "No Category");
    }

    public void deleteCategory(){
        editor.clear();
    }
}
