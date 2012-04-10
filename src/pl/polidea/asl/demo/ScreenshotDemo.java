package pl.polidea.asl.demo;

import pl.polidea.asl.*;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources.NotFoundException;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;

public class ScreenshotDemo extends Activity {

	/*
	 * The ImageView used to display taken screenshots.
	 */
	private ImageView imgScreen;

	private ServiceConnection aslServiceConn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			aslProvider = IScreenshotProvider.Stub.asInterface(service);
		}
	};
	private IScreenshotProvider aslProvider = null;


    /** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        imgScreen = (ImageView)findViewById(R.id.imgScreen);
        Button btn = (Button)findViewById(R.id.btnTakeScreenshot); 
        btn.setOnClickListener(btnTakeScreenshot_onClick); 

        // connect to ASL service
        //Intent intent = new Intent(ScreenshotService.class.getName());
        Intent intent = new Intent();
        intent.setClass(this, ScreenshotService.class);
        //intent.addCategory(Intent.ACTION_DEFAULT);
        bindService (intent, aslServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy() {
    	unbindService(aslServiceConn);
    	super.onDestroy();
    }


    private View.OnClickListener btnTakeScreenshot_onClick = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			try {
				if (aslProvider == null)
					Toast.makeText(ScreenshotDemo.this, R.string.n_a, Toast.LENGTH_SHORT).show();
				else if (!aslProvider.isAvailable())
					Toast.makeText(ScreenshotDemo.this, R.string.native_n_a, Toast.LENGTH_SHORT).show();
				else {
					String file = aslProvider.takeScreenshot();
					if (file == null)
						Toast.makeText(ScreenshotDemo.this, R.string.screenshot_error, Toast.LENGTH_SHORT).show();
					else {
						Toast.makeText(ScreenshotDemo.this, R.string.screenshot_ok, Toast.LENGTH_SHORT).show();
						Bitmap screen = BitmapFactory.decodeFile(file);
						imgScreen.setImageBitmap(screen);
						
					}
				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// squelch
			}

		}
	};
}