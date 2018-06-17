package phaohoa.example.nguyetanh.phaohoa;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView numberTime;
    Button startTime;
    EditText inputTime;
    Chronometer Time;
    RelativeLayout relativeLayout;
    int timeCountDown;
    Timer timer;
    int stt = 0;
    int[] manghinh = {

            R.drawable.phao0,
            R.drawable.phao1,
            R.drawable.phao2,
            R.drawable.phao3,
            R.drawable.phao4,
            R.drawable.phao5,
            R.drawable.phao6,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init() {
        numberTime = findViewById(R.id.textViewTime);
        startTime = findViewById(R.id.btnStart);
        inputTime = findViewById(R.id.editTextTime);
        Time = findViewById(R.id.chronometerTime);
        relativeLayout = findViewById(R.id.layout);
        Time.setVisibility(View.GONE);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuly();
            }
        });
    }

    public void xuly() {
        if (inputTime.getText().toString().trim().length() <= 0) {
            Toast.makeText(MainActivity.this, "Vui lòng nhập vào thời gian", Toast.LENGTH_SHORT).show();

        } else {
            timeCountDown = Integer.parseInt(inputTime.getText().toString().trim());
            if (timeCountDown >= 0) {
                Time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        if (timeCountDown > 0) {
                            timeCountDown = timeCountDown - 1;
                            numberTime.setText(String.valueOf(timeCountDown));
                        } else {
                            fireWork();
                            Time.stop();
                            //Toast.makeText(MainActivity.this, "Bắn pháo hoa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Time.start();
            } else {
                Toast.makeText(MainActivity.this, "Thời gian không được bé hơn 0", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void fireWork() {
        timer = new Timer();
        AnimateFireWork animateFireWork = new AnimateFireWork(relativeLayout, manghinh);
        timer.scheduleAtFixedRate(animateFireWork, 100, 100);
        stopAnimationFire();
    }

    public class AnimateFireWork extends TimerTask {
        RelativeLayout layout;
        int[] manghinh;

        private AnimateFireWork(RelativeLayout layout, int[] manghinh) {
            this.layout = layout;
            this.manghinh = manghinh;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    layout.setBackgroundResource(manghinh[stt]);
                    stt = stt + 1;
                    if (stt > 6) {
                        stt = 0;
                    }

                }
            });
        }


    }

    public void stopAnimationFire() {
        CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {    //thuc hien 5s, moi 1s thuc hien 1 lan
            @Override
            public void onTick(long l) {
                anHienDoiTuong(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                anHienDoiTuong(View.VISIBLE);
                timer.cancel();
                timer.purge();      //remove all cancer all task
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        relativeLayout.setBackgroundResource(0);
                        numberTime.setText("");
                    }
                }, 0);
            }
        };
        countDownTimer.start();
    }
    public void anHienDoiTuong(int trangthai){
        startTime.setVisibility(trangthai);
        inputTime.setVisibility(trangthai);
        numberTime.setVisibility(trangthai);
    }
}
