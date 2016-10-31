package me.leefeng.superplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by limxing on 2016/10/31.
 */

public class LMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lmain);
    }

    public void superVideo(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
