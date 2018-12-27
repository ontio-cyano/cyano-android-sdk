package com.github.cyano.cynao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class NativeJsBridge {
    private HandleLogin handleLogin;
    private HandleInvoke handleInvoke;
    private HandleGetAccount handleGetAccount;
    private HandleInvokeRead handleInvokeRead;

    private CyanoWebView cyanoWebView;

    public NativeJsBridge(CyanoWebView cyanoWebView) {
        this.cyanoWebView = cyanoWebView;
    }

    @JavascriptInterface
    public void postMessage(String userInfo) {
        if (userInfo.contains("ontprovider://ont.io")) {
            final String[] split = userInfo.split("params=");
            if (cyanoWebView != null) {
                cyanoWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        handleAction(split[split.length - 1]);
                    }
                });
            }
        }
    }


    private void handleAction(String message) {
        byte[] decode = Base64.decode(message, Base64.NO_WRAP);
        String result = Uri.decode(new String(decode));
//        {"action":"login","params":{"type":"account","dappName":"My dapp","message":"test message","expired":"201812181000","callback":""}}
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            String action = jsonObject.getString("action");
            switch (action) {
                case "login":
                    if (handleLogin != null) {
                        handleLogin.handleAction(result);
                    }
                    break;
                case "invoke":
                    if (handleInvoke != null) {
                        handleInvoke.handleAction(result);
                    }
                    break;
                case "getAccount":
                    if (handleGetAccount != null) {
                        handleGetAccount.handleAction(result);
                    }
                    break;
                case "invokeRead":
                    if (handleInvokeRead != null) {
                        handleInvokeRead.handleAction(result);
                    }
                    break;
                default:
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void setHandleLogin(HandleLogin handleLogin) {
        this.handleLogin = handleLogin;
    }

    public void setHandleInvoke(HandleInvoke handleInvoke) {
        this.handleInvoke = handleInvoke;
    }

    public void setHandleGetAccount(HandleGetAccount handleGetAccount) {
        this.handleGetAccount = handleGetAccount;
    }

    public void setHandleInvokeRead(HandleInvokeRead handleInvokeRead) {
        this.handleInvokeRead = handleInvokeRead;
    }


    public interface HandleLogin {
        public void handleAction(String data);
    }

    public interface HandleInvoke {
        public void handleAction(String data);
    }

    public interface HandleGetAccount {
        public void handleAction(String data);
    }

    public interface HandleInvokeRead {
        public void handleAction(String data);
    }
}
