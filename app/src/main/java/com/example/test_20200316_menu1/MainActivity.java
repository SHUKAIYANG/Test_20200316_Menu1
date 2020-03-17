package com.example.test_20200316_menu1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private int currentColor;
    private int newColor;
    private ImageView imageViewId;
    private TextView textViewReturn;
    private final int ChangePicture = 1; //  item id for listen
    private final int ChangeColor = 2;
    private final int SelectInfo = 3;

    private Context context;
    private boolean colorFlag;
    private final String TAG = "main";
    private boolean pictureFlag;
    private final int InfoRequestCode = 10;
    private final int InfoReturnCode = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;



        textViewTitle = (TextView) findViewById(R.id.textView_title);

        currentColor = textViewTitle.getCurrentTextColor();

        newColor = 0xff800080;

        colorFlag =  true;      //  flag for change color , defautl: true

        pictureFlag = true;      //  flag for change  picture , defautl: true

        imageViewId = (ImageView) findViewById(R.id.imageView_id);

        textViewReturn = (TextView) findViewById(R.id.textView_return);
        textViewReturn.setText("");


    } // onCreate()

    //  create Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(1, ChangePicture, Menu.NONE, "Change Picture");
        menu.add(1, ChangeColor, menu.NONE, "Change Color");
        menu.add(1, SelectInfo, Menu.NONE, "Select Info");

        return true; //  teacher said: must return true , so that menu can show up.
    }


    // listen Menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d(TAG, "item id = " + item.getItemId());

        switch (item.getItemId()) {
            case ChangePicture:
                if (pictureFlag) {
                    imageViewId.setImageResource(R.drawable.steak);
                    textViewTitle.setText("You select steak.");
                    pictureFlag = false;
                }else {
                    imageViewId.setImageResource(R.drawable.soup);
                    textViewTitle.setText("You select soup.");
                    pictureFlag = true;
                }
                break;

            case ChangeColor:
                if(colorFlag) {
                    textViewTitle.setTextColor(newColor);
                    colorFlag = false;
                }else {
                    textViewTitle.setTextColor(currentColor);
                    colorFlag = true;
                }
                break;

            case SelectInfo:
                Intent intent = new Intent(context, InfoActivity.class);
                startActivityForResult(intent, InfoRequestCode);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == InfoRequestCode) {
            if(resultCode == InfoReturnCode) {
                Log.d(TAG, "requestCode = " + requestCode);
                String returnData = data.getStringExtra("infodata");
                textViewReturn.setText(returnData);
            }
        }
    }

} // class MainActivity
