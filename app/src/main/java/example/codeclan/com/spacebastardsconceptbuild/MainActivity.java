package example.codeclan.com.spacebastardsconceptbuild;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        startActivity(new Intent(this, GameActivity.class));
        setContentView(new GameView(this));
    }
}


