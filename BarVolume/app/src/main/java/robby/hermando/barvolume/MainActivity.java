package robby.hermando.barvolume;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String STATE_RESULT = "state_result";

    //BAGIAN DEKLARASI VARIABLE/KOMPONEN VIEW DENGAN KELAS PRIVATE
    private EditText edtWidth;
    private EditText edtHeight;
    private EditText edtLength;
    private Button btnCount;
    private TextView tvResult;

    //DEKLARASINYA JUGA BISA PAKE INI
/*  EditText edtWidth, edtHeight, edtLength;
    Button btnCount;
    TextView tvResult;*/

    @Override
    protected  void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCount = findViewById(R.id.btn_count);
        tvResult = findViewById(R.id.tv_result);

        btnCount.setOnClickListener(this);

        if (savedInstanceState !=null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() ==  R.id.btn_count){
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtLength.setError("Kolom ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true;
                edtWidth.setError("Kolom ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)){
                isEmptyFields = true;
                edtHeight.setError("Kolom ini tidak boleh kosong");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null){
                isInvalidDouble = true;
                edtLength.setError("Kolom harus input angka");
            }

            if (width == null){
                isInvalidDouble = true;
                edtWidth.setError("Kolom harus input angka");
            }

            if (height == null){
                isInvalidDouble = true;
                edtHeight.setError("Kolom harus input angka");
            }

            if (!isEmptyFields && !isInvalidDouble){
                double volume = length * width * height;
                tvResult.setText(String.valueOf(volume));
            }

        }
    }

    private Double toDouble(String str){
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e){
            return null;
        }
    }
}
