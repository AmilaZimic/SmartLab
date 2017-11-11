package ius.smartlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpenCamActivity extends AppCompatActivity {

    Button btn_open_cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cam);

        // Locate the button in activity_main.xml
        btn_open_cam = (Button) findViewById(R.id.btn_current_status);

        // Capture button clicks
        btn_open_cam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start CurrentStatusActivity.class
                Intent currentStatusIntent = new Intent(OpenCamActivity.this, CurrentStatusActivity.class);
                startActivity(currentStatusIntent);
            }
        });
    }
}
