package donghobamgio.example.nguyetanh.nghbmgi;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Chronometer dem;
    Button start, pause, stop;
    boolean flag = false;
    long timeWhenPause = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        dem = findViewById(R.id.chronometer);
        start = findViewById(R.id.btnStart);
        pause = findViewById(R.id.btnPause);
        stop = findViewById(R.id.btnStop);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                if (!flag) {
                    long currentTime = SystemClock.elapsedRealtime() + timeWhenPause;
                    dem.setBase(currentTime);
                    dem.start();
                    flag = true;
                }
                break;
            case R.id.btnPause:
                if(flag){
                    dem.stop();
                    timeWhenPause = dem.getBase() - SystemClock.elapsedRealtime();
                    flag = false;
                }
                break;
            case R.id.btnStop:
                if(flag) {
                    dem.stop();
                    flag = false;
                    timeWhenPause = 0;
                }
                break;

        }
    }
}
