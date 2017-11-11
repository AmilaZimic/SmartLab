package ius.smartlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class CurrentStatusActivity extends AppCompatActivity {

    Button btn_open_cam;
    Button btn_manual_control;
    Button btn_see_past_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_status);

        // Locate the button in activity_current_status.xml
        btn_open_cam = (Button) findViewById(R.id.btn_open_cam);

        // Capture button clicks
        btn_open_cam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start OpenCamActivity.class
                Intent openCamIntent = new Intent(CurrentStatusActivity.this, OpenCamActivity.class);
                startActivity(openCamIntent);
            }
        });

        // Locate the button in activity_current_status.xml
        btn_manual_control = (Button) findViewById(R.id.btn_manual_control);

        // Capture button clicks
        btn_manual_control.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start ManualControlActivity.class
                Intent manualControlIntent = new Intent(CurrentStatusActivity.this, ManualControlActivity.class);
                startActivity(manualControlIntent);
            }
        });

        // Locate the button in activity_current_status.xml
        btn_see_past_data = (Button) findViewById(R.id.btn_see_past_data);

        // Capture button clicks
        btn_see_past_data.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start SeePastDataActivity.class
                Intent seePastDataIntent = new Intent(CurrentStatusActivity.this, SeePastDataActivity.class);
                startActivity(seePastDataIntent);
            }
        });
    }
}
