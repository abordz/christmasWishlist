package prototype.optimind.alertreminder;

import java.util.Date;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ReminderService extends Service {

	ContentInstance content;
	Context context;
	Handler handler;
	Checker checkerThread;
	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}

	@Override
	public void onCreate() {
		content = new ContentInstance();
		context = this.getApplicationContext();
		handler =new Handler();
		checkerThread = new Checker();
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		handler.post(checkerThread);
		return 0;
	}

	class Checker implements Runnable
	{
		boolean enable=true;
		long targetTime,now;
		int count = 0;
		public void stop()
		{
			enable = false;
		}
		public void run() {
			while(enable)
			{
			
				try {
					targetTime=content.getInstances(context);
					now = new Date().getTime();
					if(targetTime<=now||count==5)
					{
						Log.e("GOOD","ITS NOW");
						enable = false;
						Intent toMain = new Intent(context,MainActivity.class);
						toMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(toMain);
					}
					Thread.sleep(5000L);
					count++;
					Log.e("SLEEP","SERVICE SLEEPS Now:" + now);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
}
