package speedtotext.example.nguyetanh.speedtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button speech;
    TextView content;
    ImageView hinh;
    public static final int RESULT_SPEECH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init() {
        speech = findViewById(R.id.btn);
        content = findViewById(R.id.txt);
        hinh = findViewById(R.id.image);
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuly();
            }
        });
    }

    public void xuly() {
        hinh.setImageResource(0);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(intent, RESULT_SPEECH);   //du lieu de ban di kiem tra qua trinh
            content.setText("");
        } catch (ActivityNotFoundException a) {
            Toast.makeText(MainActivity.this, "Thiết bị bạn không hỗ trợ", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH:
                if(data != null) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    content.setText(text.get(0));
                    showHeart(text.get(0));
                }
                break;
        }
    }

    public void showHeart(String chuoi) {
        String[] splited = chuoi.split("\\s+");
        for (String item : splited) {
            if (item.equalsIgnoreCase("yêu") || item.equalsIgnoreCase("iu") || item.equalsIgnoreCase("love")) {
                hinh.setImageResource(R.drawable.traitim);
            }

        }
    }
}
