package phieudangky.example.nguyetanh.phieudangky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox toan, van, anh, hoa, ly;
    Button click;
    String monhoc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init() {
        toan = findViewById(R.id.chkToan);
        van = findViewById(R.id.chkVan);
        anh = findViewById(R.id.chkAnh);
        hoa = findViewById(R.id.chkHoa);
        ly = findViewById(R.id.chkLy);
        click = findViewById(R.id.btnClick);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuly();
            }
        });
    }

    public void xuly() {
        if (!toan.isChecked() && !van.isChecked() && !anh.isChecked() && !hoa.isChecked() && !ly.isChecked()) {
            Toast.makeText(this, "Vui lòng chọn ít nhất một môn học", Toast.LENGTH_SHORT).show();
        } else {
            if (toan.isChecked()) {
                monhoc = monhoc + "Toán, ";
            }
            if (van.isChecked()) {
                monhoc = monhoc + "Văn, ";
            }
            if (anh.isChecked()) {
                monhoc = monhoc + "Anh, ";
            }
            if (hoa.isChecked()) {
                monhoc = monhoc + "Hóa, ";
            }
            if (ly.isChecked()) {
                monhoc = monhoc + "Lý, ";
            }
            Toast.makeText(this, deleteLastCharacter(monhoc), Toast.LENGTH_SHORT).show();
            monhoc = "";
        }
    }

    public String deleteLastCharacter(String str) {
        str = str.substring(0, str.length() - 2);
        return str;
    }

}
