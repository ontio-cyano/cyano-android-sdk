package com.github.cyano.cynao;

import android.net.Uri;
import android.util.Base64;
import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

import static com.github.cyano.cynao.Common.CYANO_SPLIT_TAG;
import static com.github.cyano.cynao.Common.CYANO_WEB_TAG;
import static com.github.cyano.cynao.Common.GET_ACCOUNT;
import static com.github.cyano.cynao.Common.INVOKE;
import static com.github.cyano.cynao.Common.INVOKE_PASSWORD_FREE;
import static com.github.cyano.cynao.Common.INVOKE_READ;
import static com.github.cyano.cynao.Common.LOGIN;

public class NativeJsBridge {
    private HandleLogin handleLogin;
    private HandleInvoke handleInvoke;
    private HandleGetAccount handleGetAccount;
    private HandleInvokeRead handleInvokeRead;
    private HandleInvokePasswordFree handleInvokePasswordFree;

    private CyanoWebView cyanoWebView;

    public NativeJsBridge(CyanoWebView cyanoWebView) {
        this.cyanoWebView = cyanoWebView;
    }

    @JavascriptInterface
    public void postMessage(String userInfo) {
        if (userInfo.contains(CYANO_WEB_TAG)) {
            final String[] split = userInfo.split(CYANO_SPLIT_TAG);
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
                case LOGIN:
                    if (handleLogin != null) {
                        handleLogin.handleAction(result);
                    }
                    break;
                case INVOKE:
                    if (handleInvoke != null) {
                        handleInvoke.handleAction(result);
                    }
                    break;
                case GET_ACCOUNT:
                    if (handleGetAccount != null) {
                        handleGetAccount.handleAction(result);
                    }
                    break;
                case INVOKE_READ:
                    if (handleInvokeRead != null) {
                        handleInvokeRead.handleAction(result);
                    }
                    break;
                case INVOKE_PASSWORD_FREE:
                    if (handleInvokePasswordFree != null) {
                        handleInvokePasswordFree.handleAction(result,message);
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

    public void setHandleInvokePasswordFree(HandleInvokePasswordFree handleInvokePasswordFree) {
        this.handleInvokePasswordFree = handleInvokePasswordFree;
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

    public interface HandleInvokePasswordFree {
        public void handleAction(String data,String message);
    }


}
