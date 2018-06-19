package com.example.shinji_win.file;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shinji_win.file.util.IOUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IOUtils.writeToFile(getApplicationContext(), "こんにちは", IOUtils.FILE_BOOTSTRAP_DATA);

            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jsonObj = IOUtils.readAsString(getApplicationContext(), IOUtils.FILE_BOOTSTRAP_DATA);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(jsonObj);
            }
        });
    }
}
