package boctham.example.nguyetanh.boctham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button boctham;
    TextView hoten;
    int stop = 0;
    Timer timer;
    int itemOnArray;
    String[] list = {
            "Lê Văn Sang",
            "Nguyễn Thị Huyền",
            "Dương Thị Thắm",
            "Nguyễn Thị Bích Dân",
            "Cao Văn Tùng",
            "Nguyễn Quốc Hiệp",
            "Nguyễn Hải Phong",
            "Nguyễn Bá Lộc",
            "Phùng Thị Kết Hoan",
            "Nguyễn Hải Sơn",
            "Phùng Thị Ninh",
            "Cao Văn Đức"
    };
    ArrayList<String> danhsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        boctham = findViewById(R.id.button);
        hoten = findViewById(R.id.textView);
        danhsach = new ArrayList<>();
        //add tất cả phần tử trong list vào danh sách
        Collections.addAll(danhsach, list);
        boctham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuly();
            }
        });
    }

    public void xuly() {
        timer = new Timer(); //thừa Timer timer bị loi dung lại
        RunRandom runRandom = new RunRandom();
        timer.scheduleAtFixedRate(runRandom, 0, 100);
        if(!danhsach.isEmpty()) {
            danhsach.remove(itemOnArray);
        }
    }

    public class RunRandom extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!danhsach.isEmpty()) {
                        if (stop == 100) {
                            stop = 0;
                            timer.cancel();

                        } else {
                            int totalItem = danhsach.size(); //tong phan tu: 9
                            itemOnArray = getRandom(totalItem);  //0 -> 8
                            hoten.setText(danhsach.get(itemOnArray));
                        }
                        stop = stop + 1;
                        //Log.d("GiaTri:", danhsach.get(itemOnArray));
                    }
                }
            });
        }

        private int getRandom(int max) {
            return (int) (Math.random() * max);

        }
    }
}
