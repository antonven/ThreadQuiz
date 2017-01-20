package myapps.wycoco.com.threadquiz;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;



public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Button tru, fls;
    TextView scr, color, time;
    Switch swtch;
    int count = 10, max = 150, min = 50;
    FrameLayout fm;
    Handler handler;
    String col[] = {"Red", "Blue", "Green", "Violet"};
    int colors[] = {android.R.color.holo_red_dark, android.R.color.holo_green_dark, android.R.color.holo_purple};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tru = (Button)findViewById(R.id.trueBtn);
        fls = (Button)findViewById(R.id.falseBtn);
        scr = (TextView)findViewById(R.id.scoreView);
        color = (TextView)findViewById(R.id.colorText);
        time = (TextView)findViewById(R.id.timeText);
        swtch = (Switch)findViewById(R.id.switch1);
        fm = (FrameLayout)findViewById(R.id.colorFrame);

        time.setText(String.valueOf(count));
        tru.setOnClickListener(this);
        fls.setOnClickListener(this);
        swtch.setOnCheckedChangeListener(this);

//        handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                count = count - 1000;
//                if(count > 0) {
//                    handler.postDelayed(this, 1000);
//                }
//            }
//        };
//        handler.postDelayed(run, 1000);
    }

    class MyRunnable implements Runnable{
        @Override
        public void run() {

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {

        time = (TextView)findViewById(R.id.timeText);
        Random r = new Random();
        color = (TextView)findViewById(R.id.colorText);


        int s = r.nextInt(4-1) + 1;

        if(b){
            Thread t = new Thread() {
                @Override
                public void run() {
                    while(b) {
                        while(count > 0) {
                            try {

                                Thread.sleep(1000);
                                count--;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        time.setText(String.valueOf(count));
                                        Random r = new Random();


                                        int s = r.nextInt(4-1) + 1;
                                        fm.setBackgroundColor(colors[s]);
                                        color.setText(col[s]);

                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }
            };

            t.start();
        }
       if(!b){
           tru.setEnabled(false);
           fls.setEnabled(false);
           Thread.interrupted();
       }

    }


    @Override
    public void onClick(View view) {

    }



}
