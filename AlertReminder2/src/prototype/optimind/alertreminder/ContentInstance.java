package prototype.optimind.alertreminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Instances;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;

public class ContentInstance {
	private static final String DEBUG_TAG = "MyActivity";
	public static final String[] INSTANCE_PROJECTION = new String[] {
	    Instances.EVENT_ID,      // 0
	    Instances.BEGIN,         // 1
	    Instances.TITLE          // 2
	  };
	  
	// The indices for the projection array above.
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_BEGIN_INDEX = 1;
	private static final int PROJECTION_TITLE_INDEX = 2;
	
public long getInstances(Context context)
{
	
	

	// Specify the date range you want to search for recurring
	// event instances
	Calendar beginTime = Calendar.getInstance();
   beginTime.set(2012, 9, 23, 8, 0);
	long startMillis = beginTime.getTimeInMillis();
	Calendar endTime = Calendar.getInstance();
	endTime.set(2013, 10, 24, 8, 0);
	long endMillis = endTime.getTimeInMillis();
	  
	Cursor cur = null;
	ContentResolver cr =context.getContentResolver();

	// The ID of the recurring event whose instances you are searching
	// for in the Instances table
	String selection = Instances.EVENT_ID + " = ?";
	String[] selectionArgs = new String[] {"207"};

	// Construct the query with the desired date range.
	Uri.Builder builder = Uri.parse("content://com.android.calendar/instances/when").buildUpon();
    //Uri.Builder builder = Uri.parse("content://com.android.calendar/calendars").buildUpon();
    long now = new Date().getTime();

    ContentUris.appendId(builder, now - DateUtils.DAY_IN_MILLIS* 10000);
    ContentUris.appendId(builder, now + DateUtils.DAY_IN_MILLIS * 10000);
    

    cur = cr.query(builder.build(),
            new String[]  { "title", "begin", "end", "allDay"}, "Calendars._id=" + 1,
            null, "startDay ASC, startMinute ASC");
	  Log.e("Cursor SIZE", cur.getCount() +"");
	  long earlyTime=0l;
	while (cur.moveToNext()) {
	    String title = null;
	    long eventID = 0;
	    long beginVal = 0;    
	    
	    // Get the field values
	    eventID = cur.getLong(PROJECTION_ID_INDEX);
	    earlyTime =beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
	    title = cur.getString(PROJECTION_TITLE_INDEX);
	              
	    // Do something with the values. 
	    Log.e(DEBUG_TAG, "Event:  " + title); 
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(beginVal);  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy/HH:mm");
	    Log.e(DEBUG_TAG, "Date: " + formatter.format(calendar.getTime()));    
	    earlyTime = cur.getLong(PROJECTION_BEGIN_INDEX);
	}
	return earlyTime;
	 }

}



