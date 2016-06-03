package com.vivianaranha.headlessfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        TheFragment counterFragment = (TheFragment) getFragmentManager().findFragmentByTag("counter_fragment");

        if(counterFragment == null){
            counterFragment = new TheFragment();
            getFragmentManager().beginTransaction().add(counterFragment, "counter_fragment").commit();

        }

        counterFragment.setmCounterView(textView);

    }

    public void startCounting(View view) {
        TheFragment counterFragment = (TheFragment) getFragmentManager().findFragmentByTag("counter_fragment");
        if(counterFragment != null){
            counterFragment.startCounting();
        }
    }

    public void stopCounting(View view) {
        TheFragment counterFragment = (TheFragment) getFragmentManager().findFragmentByTag("counter_fragment");
        if(counterFragment != null){
            counterFragment.stopCounting();
        }
    }
}
