package com.example.rent.carsdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FilterQueryProvider;

import com.example.rent.carsdatabase.Add.AddNewCarActivity;
import com.example.rent.carsdatabase.listing.ListingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.auto_complite_textview)
    AutoCompleteTextView autoCompleteTextView;

    private MotoDbOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Car car = new CarBuilder()
//                .setMake("Opel")
//                .setModel("Astra")
//                .setImage("http://opel")
//                .setYear(1989)
//                .createCar();
//
//        MotoDbOpenHelper motoDbOpenHelper = new MotoDbOpenHelper(this);
//        motoDbOpenHelper.insertCar(car);


//        motoDbOpenHelper.getWritableDatabase();
//        nie będziemy korzystać z powyższej


        ButterKnife.bind(this);
        dbOpenHelper = new MotoDbOpenHelper(this);

        AutoCompleteAdapter adapter = new AutoCompleteAdapter(this, dbOpenHelper.getAllItems());
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                autoCompleteTextView.setText(cursor.getString(cursor.getColumnIndex(CarsTableContract.COLUMN_MAKE)));
            }
        });

        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return dbOpenHelper.searchQuery(constraint);
            }
        });

    }

    @OnClick(R.id.add_new_car)
    void onAddNewCarButtonClick() {
        Intent intent = new Intent(this, AddNewCarActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.search_car)
    void onSearchCarButtonClick() {
        startActivity(ListingActivity.createIntent(MainActivity.this,
                autoCompleteTextView.getText().toString()));
    }
}
