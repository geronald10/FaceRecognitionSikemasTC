package id.web.goronald.facerecognitionsikemastc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;

import ch.zhaw.facerecognitionlibrary.Helpers.FileHelper;
import id.web.goronald.facerecognitionsikemastc.R;

public class AddSetWajahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set_wajah);

        final Intent intent = getIntent();
        final String userInfo = intent.getStringExtra("identitas_mahasiswa");
        TextView textName = (TextView) findViewById(R.id.tv_user_detail);
        textName.setText(userInfo);

        final ToggleButton btnTrainingTest = (ToggleButton) findViewById(R.id.btn_training_test);
        final ToggleButton btnReferenceDeviation = (ToggleButton) findViewById(R.id.btn_reference_deviation);
        final ToggleButton btnTimeManually = (ToggleButton) findViewById(R.id.btn_time_manually);
        Button btnStart = (Button) findViewById(R.id.btn_start);

        btnTrainingTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnTrainingTest.isChecked()) {
                    btnReferenceDeviation.setEnabled(true);
                } else {
                    btnReferenceDeviation.setEnabled(false);
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToStart = new Intent(v.getContext(), AddSetWajahPreviewActivity.class);
                intentToStart.putExtra("Name", userInfo);
                intentToStart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                if (btnTimeManually.isChecked()) {
                    intentToStart.putExtra("Method", AddSetWajahPreviewActivity.MANUALLY);
                } else {
                    intentToStart.putExtra("Method", AddSetWajahPreviewActivity.TIME);
                }

                if (btnTrainingTest.isChecked()) {
                    // Add photos to "Test" folder
                    if (isNameAlreadyUsed(new FileHelper().getTestList(), userInfo)) {
                        Toast.makeText(getApplicationContext(), "This name is already used. Please choose another one.", Toast.LENGTH_SHORT).show();
                    } else {
                        intentToStart.putExtra("Folder", "Test");
                        if (btnReferenceDeviation.isChecked()) {
                            intentToStart.putExtra("Subfolder", "deviation");
                        } else {
                            intentToStart.putExtra("Subfolder", "reference");
                        }
                        startActivity(intentToStart);
                    }
                } else {
                    // Add photos to "Training" folder

                    if (isNameAlreadyUsed(new FileHelper().getTrainingList(), userInfo)) {
                        Toast.makeText(getApplicationContext(), "This name is already used. Please choose another one.", Toast.LENGTH_SHORT).show();
                    } else {
                        intentToStart.putExtra("Folder", "Training");
                        startActivity(intentToStart);
                    }
                }
            }
        });
    }

    private boolean isNameAlreadyUsed(File[] list, String name){
        boolean used = false;
        if(list != null && list.length > 0){
            for(File person : list){
                // The last token is the name --> Folder name = Person name
                String[] tokens = person.getAbsolutePath().split("/");
                final String foldername = tokens[tokens.length-1];
                if(foldername.equals(name)){
                    used = true;
                    break;
                }
            }
        }
        return used;
    }
}
