package chiecnonkydieu.example.nguyetanh.chiecnonkydieu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button play;
    ImageView hinh;
    TextView ketqua;
    String kq;
    int from = 0;

    int phanthuong[] = {2000, 100, 500, 1, 200, 700, 1000, 400, 2, 900, 3, 300, 800, 4, 1000, 400, 600, 300, 5, 200, 900, 700, 6, 300};
    int vitri[] = {0, 15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 165, 180, 195, 210, 225, 240, 255, 270, 285, 300, 315, 330, 345};
    int vong[] = {360, 720, 1080, 1440, 1800};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        play = findViewById(R.id.btnPlay);
        hinh = findViewById(R.id.hinh);
        ketqua = findViewById(R.id.txtKQ);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuly();
            }
        });
    }

    public void xuly() {
        final int position = getRandom(vitri.length);
        int positionVong = getRandom(vong.length);
        int to = vitri[position] + vong[positionVong];
        RotateAnimation animation = new RotateAnimation(from, to, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);   //RELATIVE_TO_SELF tức là trên đối tượng(chiếc nón) mình luôn, 1F là đường kính, 0.5F là bán kính -> để chiếc nón quay theo tâm
        animation.setDuration(6000);    //trong bao lâu hoàn thành hết các animation
        animation.setInterpolator(new DecelerateInterpolator());    //DecelerateInterpolator chạy nhanh sau đó chậm dần đều
        animation.setFillAfter(true);       //xoay xong đứng đó luôn
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //khi kết thúc làm gì đó
                switch (phanthuong[position]) {
                    case 1:
                        kq = "Bạn được nhân đôi số điểm";
                        break;
                    case 2:
                        kq = "Bạn bị mất lượt";
                        break;
                    case 3:
                        kq = "Bạn được một lần rút thăm may mắn";
                        break;
                    case 4:
                        kq = "Bạn bị mất hết điểm";
                        break;
                    case 5:
                        kq = "Bạn được thêm một lượt";
                        break;
                    case 6:
                        kq = "Bạn bị chia đôi số điểm";
                        break;
                    default:
                        kq = "Điểm: " + phanthuong[position];
                        break;

                }
                ketqua.setText(kq);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        hinh.startAnimation(animation);
        from = vitri[position];

    }

    public int getRandom(int max) {
        return (int) (Math.random() * max);
    }
}
