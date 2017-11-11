package ius.smartlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManualControlActivity extends AppCompatActivity {

    Button btn_current_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_control);

        // Locate the button in activity_main.xml
        btn_current_status = (Button) findViewById(R.id.btn_current_status);

        // Capture button clicks
        btn_current_status.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start CurrentStatusActivity.class
                Intent currentStatusIntent = new Intent(ManualControlActivity.this, CurrentStatusActivity.class);
                startActivity(currentStatusIntent);
            }
        });
    }
}
