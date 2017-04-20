package com.cordovaplugin;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class WiFiSSID extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("getSSID")) {
      String message = args.getString(0);
      this.getSSID(message, callbackContext);
      return true;
    }
    return false;
  }

  private void getSSID(String message, CallbackContext callbackContext) {
    try {
      String ssid = getWIFISSID(cordova.getActivity());
      Log.d("getSSID", "ssid = "+ssid);
      callbackContext.success(ssid);
    } catch (Exception e) {
      e.printStackTrace();
      callbackContext.error("native error.");
    }
  }

  /**
   * 获取当前WIFI  ssid
   *
   * @param context
   * @return
   */
  public static String getWIFISSID(Context context) {
    String wifiId = "";
    WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    if (wifiMgr != null) {
      WifiInfo info = wifiMgr.getConnectionInfo();
      wifiId = info != null ? info.getSSID() : "";
    }
    return wifiId;
  }
}
