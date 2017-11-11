package ius.smartlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.button;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.signin);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start SigninActivity.class
                Intent signinIntent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(signinIntent);
            }
        });
    }
}
