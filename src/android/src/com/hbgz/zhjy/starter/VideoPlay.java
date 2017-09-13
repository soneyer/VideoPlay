package com.hbgz.zhjy.starter;

import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

/**
 * Created by Administrator on 2017/8/14/014.
 */

public class VideoPlay extends CordovaPlugin {

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if ("videoPlay".equals(action)){
//            Intent intent = new Intent(this.cordova.getActivity(), VideoActivity.class);
            Intent intent = new Intent(this.cordova.getActivity(), GSYVideoActivity.class);
            intent.putExtra("videoUri", args.getString(0));
            intent.putExtra("callBackUri", args.getString(1));
            this.cordova.getActivity().startActivity(intent);
            return true;
        }
        return false;
    }
}
