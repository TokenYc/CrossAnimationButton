package net.archeryc.crossbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import net.archeryc.crossanimationbutton.CrossAnimationButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CrossAnimationButton crossButton = (CrossAnimationButton) findViewById(R.id.crossButton);

        crossButton.setCrossButtonStateChangeListener(new CrossAnimationButton.OnCrossButtonStateChangedListener() {
            @Override
            public void onExpanded() {
                //todo
            }

            @Override
            public void onCollapsed() {
                //todo
            }
        });
    }
}
