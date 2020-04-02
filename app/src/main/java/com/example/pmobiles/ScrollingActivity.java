package com.example.pmobiles;

import android.content.Intent;
import android.os.Bundle;

import com.example.pmobiles.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ScrollingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        Intent intent  = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();

        String[] arr = message.split(" ");
        arr = Arrays.copyOfRange(arr, 1, arr.length);
        final ArrayList<String> links = new ArrayList<>(Arrays.asList(arr));
        links.add("link7");
        links.add("link8");
        links.add("link9");
        links.add("link10");
        links.add("link11");
        final ListView listView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, links);
        listView.setAdapter(adapter);
        listView.setNestedScrollingEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String elem = (String) listView.getItemAtPosition(position);
                Toast t = Toast.makeText(getApplicationContext(), elem , Toast.LENGTH_SHORT);
                t.show();
            }
        });

    }
}
