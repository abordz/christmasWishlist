package prototype.optimind.alertreminder;


import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	ToggleButton tglActivate;
	Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tglActivate = (ToggleButton)findViewById(R.id.tgl_activate);
        intent = new Intent(this, ReminderService.class);
        tglActivate.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				if(isChecked)
				{
					
					startService(intent);
				}
				else	
				{
					
				}
				
			}
        	
        });
        ContentInstance content = new ContentInstance();
        content.getInstances(this.getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
