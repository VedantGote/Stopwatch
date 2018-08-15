package com.example.vedantgote.stopwatch;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {


    Button st,sp,re,sl;
    ListView liv;
    TextView tv;
    long millS,timebuf,updatet,startt=0L;
    int min,sec,msec;
    List<String> l;
    ArrayAdapter<String> ad;
    Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        st=(Button)findViewById(R.id.b1);
        sp=(Button)findViewById(R.id.b2);
        re=(Button)findViewById(R.id.b3);
        sl=(Button)findViewById(R.id.b4);
        tv=(TextView) findViewById(R.id.tv);
        liv = (ListView)findViewById(R.id.liv);
        l = new ArrayList<String>();
        ad = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,l);
        liv.setAdapter(ad);
        h = new Handler();
        }
     public void start(View view)
     {
         startt = SystemClock.uptimeMillis();
         h.postDelayed(r,0);
         re.setEnabled(false);
     }

     public void pause(View view)
     {
         timebuf +=millS;
         h.removeCallbacks(r);
         re.setEnabled(true);
     }

     public void reset(View view)
     {
         millS=0;
         startt=0;
         updatet=0;
         min=0;
         sec=0;
         msec=0;
         timebuf=0;
         tv.setText("00:00:00");
         l.clear();
         ad.notifyDataSetChanged();
     }

     public  void save(View view)
     {
         l.add(tv.getText().toString());
         ad.notifyDataSetChanged();
     }

    public Runnable r = new Runnable(){
        public void run()
        {
            millS = SystemClock.uptimeMillis()-startt;
            updatet = timebuf + millS;

            sec = (int)updatet/1000;
            min = sec/60;
            sec = sec%60;
            msec = (int) sec%1000;

            tv.setText(Integer.toString(min) + ":" + String.format("%02d",sec)+":" + String.format("%03d",msec));
            h.postDelayed(this,0);

        }
    };}


