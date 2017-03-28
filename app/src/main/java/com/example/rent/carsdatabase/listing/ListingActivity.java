package com.example.rent.carsdatabase.listing;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.rent.carsdatabase.R;
import com.example.rent.carsdatabase.details.DetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListingActivity extends AppCompatActivity {

    private static final String QUERY = "query";

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ButterKnife.bind(this);

        String query = getIntent().getStringExtra(QUERY);
        Fragment fragment = ListingFragment.getInstance(query);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }

    public static Intent createIntent(Context context, String query){
        Intent intent = new Intent(context, ListingActivity.class);
        intent.putExtra(QUERY, query);
        return intent;
    }

    void onCarItemClick(String id){
        android.support.v4.app.Fragment fragment = DetailsFragment.getInstance(id);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        Toast.makeText(this, "CarId " + id, Toast.LENGTH_LONG).show();
    }

}
