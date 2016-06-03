package com.vivianaranha.headlessfragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by sooheib on 6/3/16.
 */
public class TheFragment extends Fragment {

    private WeakReference<TextView> mCounterView;

    private SimpleAsyncCounter simpleAsyncCounter = new SimpleAsyncCounter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setmCounterView(TextView textView){
        mCounterView = new WeakReference<TextView>(textView);
    }

    public void startCounting(){
        if(simpleAsyncCounter.getStatus() == AsyncTask.Status.RUNNING){
            return;
        }
        if(simpleAsyncCounter.getStatus() == AsyncTask.Status.FINISHED){
            simpleAsyncCounter = new SimpleAsyncCounter();
        }
        simpleAsyncCounter.execute();
    }

    public void stopCounting() {
        if (simpleAsyncCounter.getStatus() != AsyncTask.Status.RUNNING) {
            return;
        }
        simpleAsyncCounter.cancel(true);
    }

    private class SimpleAsyncCounter extends AsyncTask<Void, Integer, Void> {

        private int mViewIsGoneCount = 0;

        @Override
        protected Void doInBackground(Void... params) {
            int count = 0;

            while(!isCancelled()){
                count++;
                publishProgress(count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return  null;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TextView view = mCounterView.get();
            if(view != null){
                view.setText("Count: "+values[0]);
                mViewIsGoneCount = 0;
            } else {
                mViewIsGoneCount++;
                if(mViewIsGoneCount < 5){
                    //Toast
                } else {
                    cancel(true);
                }
            }
        }
    }
}
