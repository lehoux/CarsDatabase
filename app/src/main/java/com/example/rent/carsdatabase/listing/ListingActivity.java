package com.example.rent.carsdatabase.listing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.rent.carsdatabase.R;

public class ListingActivity extends AppCompatActivity {

    private static final String QUERY = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
    }

    public static Intent createIntent(Context context, String query){
        Intent intent = new Intent(context, ListingActivity.class);
        intent.putExtra(QUERY, query);
        return intent;
    }

}
