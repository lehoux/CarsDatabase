package com.example.rent.carsdatabase.Add;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rent.carsdatabase.Car;
import com.example.rent.carsdatabase.CarBuilder;
import com.example.rent.carsdatabase.MotoDbOpenHelper;
import com.example.rent.carsdatabase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewCarActivity extends AppCompatActivity {

    private MotoDbOpenHelper databaseOpenHelper;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.year)
    EditText year;

    @BindView(R.id.model)
    EditText model;

    @BindView(R.id.make)
    EditText make;

    private String urlImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);
        ButterKnife.bind(this);

        databaseOpenHelper = new MotoDbOpenHelper(this);
    }

    @OnClick(R.id.add_car)
    void onAddCarButtonClick() {
        Car car = new CarBuilder()
                .setMake(make.getText().toString())
                .setModel(model.getText().toString())
                .setYear(Integer.parseInt(year.getText().toString()))
                .setImage(urlImage)
                .createCar();

        boolean added = databaseOpenHelper.insertCar(car);

        if (added) {
            Toast.makeText(this, "dodano", Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.add_image)
    void onAddImageButtonClick() {
        View promptView = LayoutInflater.from(this).inflate(R.layout.dialog_prompt, null);
        EditText imageUrl = (EditText) promptView.findViewById(R.id.url_image);

        new AlertDialog.Builder(this)
                .setView(promptView)
                .setPositiveButton("Ok", (dialog, which) -> {
                    urlImage = imageUrl.getText().toString();
                    Glide.with(AddNewCarActivity.this)
                            .load(urlImage)
                            .into(imageView);
                }).show();

    }
}
