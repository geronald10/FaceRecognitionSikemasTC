package id.web.goronald.facerecognitionsikemastc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.web.goronald.facerecognitionsikemastc.R;

public class MenuValidasiWajah extends AppCompatActivity {

    private final String TAG = MenuValidasiWajah.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_validasi_wajah);

        Button callAddSetWajah = (Button) findViewById(R.id.btn_add_set_wajah);
        Button callDetectionView = (Button) findViewById(R.id.btn_detection_view);

        callAddSetWajah.setOnClickListener(operate);
        callDetectionView.setOnClickListener(operate);
    }

    View.OnClickListener operate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_set_wajah:
                    Intent intentToSetWajah = new Intent(v.getContext(), AddSetWajahActivity.class);
                    intentToSetWajah.putExtra("identitas_mahasiswa", "5113100112 Ronald Gunawan R");
                    startActivity(intentToSetWajah);
                    break;
                case R.id.btn_detection_view:
                    Intent intentToDetectionView = new Intent(v.getContext(), DetectionActivity.class);
                    startActivity(intentToDetectionView);
                    break;
            }
        }
    };
}
