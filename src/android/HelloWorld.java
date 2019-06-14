package cordova.plugin.hello.world;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Testing background service
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
// import android.support.v7.app.AppCompatActivity;
import android.util.Log;
/**
 * This class echoes a string called from JavaScript.
 */
// public class HelloWorld extends CordovaPlugin {
// public class HelloWorld extends AppCompatActivity {
public class HelloWorld {


  Intent mServiceIntent;
  private SensorService mSensorService;
  Context ctx;

  public Context getCtx() {
    return ctx;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ctx = this;
    setContentView(R.layout.activity_main);
    mSensorService = new SensorService(getCtx());
    mServiceIntent = new Intent(getCtx(), mSensorService.getClass());
    if (!isMyServiceRunning(mSensorService.getClass())) {
        startService(mServiceIntent);
    }
  }

  private boolean isMyServiceRunning(Class<?> serviceClass) {
    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
        if (serviceClass.getName().equals(service.service.getClassName())) {
            Log.i ("isMyServiceRunning?", true+"");
            return true;
        }
    }
    Log.i ("isMyServiceRunning?", false+"");
    return false;
  }


  @Override
  protected void onDestroy() {
      stopService(mServiceIntent);
      Log.i("MAINACT", "onDestroy!");
      super.onDestroy();

  }

    // @Override
    // public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    //     if (action.equals("coolMethod")) {
    //         String message = args.getString(0);
    //         this.coolMethod(message, callbackContext);
    //         return true;
    //     }
    //     return false;
    // }
    //
    // private void coolMethod(String message, CallbackContext callbackContext) {
    //     if (message != null && message.length() > 0) {
    //         callbackContext.success(message);
    //     } else {
    //         callbackContext.error("Expected one non-empty string argument.");
    //     }
    // }




}
