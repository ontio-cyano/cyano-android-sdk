English | [中文](README_CN.md)

# cyano-android-sdk
cyano-android-sdk helps to communicate between Android webview and dapp.  It encapsulates some methods for Android webview.  
>webview communication is done by window.postmessage()

* [WALLET](#WALLET)
* ONTID   (NOT SUPPORT)
    * [How to use](#How_to_use_ONTID)
    * [Rapid integration](#ONTID_Rapid_integration)
* [DEMO](#demo)
* [Download links](#Download)

# Integrate
Import the project as a module into the project

[Refer to CEP1 document for data format](https://github.com/ontio-cyano/CEPs/blob/master/CEPS/CEP1.mediawiki)

## WALLET

* Initialization
 
```
	CyanoWebView cyanoWebView=new CyanoWebView(context);  
	cyanoWebView.loadUrl(url);
```


* action：login

```
	cyanoWebView.getNativeJsBridge().setHandleLogin(new NativeJsBridge.HandleLogin() {
            @Override
            public void handleAction(String data) {
            /* TODO
            * 1. Parse message in params parameter
            * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
            * String message=reqJson.getJSONObject("params").getString("message");
            *
            * 2.Show the password dialog, unscramble the wallet account, sign the message, and pay attention to the time-consuming operation.
            *
            * 3. Stitching Return Results，Include：publicKey、type、user message signature
            *  JSONObject result = new JSONObject();
            *   result.put("publickey", Wallet public key);
            *   result.put("type", "account");
            *   result.put("user", Wallet address);
            *   result.put("message", message);
            *   result.put("signature", Signature result);
            * 
            * 4.Send the return result to webView
            * String action=reqJson.getString("action");
            * String version=reqJson.getString("version");
            * String id=reqJson.getString("id");
            * cyanoWebView.sendSuccessToWeb(action,version, id, result);
            */
            }
	});
```


* action：Invoke
```
	cyanoWebView.getNativeJsBridge().setHandleInvoke(new NativeJsBridge.HandleInvoke() {
            @Override
            public void handleAction(String data) {
              /* TODO
               * 1.Show the password dialog, unscramble the wallet account, construct the transaction with data, sign the transaction, pre-execute the acquisition results, and pay attention to time-consuming operation.
               *
               * 2.Resolve the result of the forecasting bank to the Notify result and display the handling fee. If the result contains the ONT and ONG contract address, the transfer amount and the receipt address should be displayed.
               *
               * 3.Users confirm and send the transaction to the chain
               *
               * 4.Send transaction hash to webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, 交易hash);
               */
            }
	});
```

* action：GetAccount
```
	cyanoWebView.getNativeJsBridge().setHandleGetAccount(new NativeJsBridge.HandleGetAccount() {
            @Override
            public void handleAction(String data) {
              /* TODO
               * 1.Send wallet address to webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, wallet address);
               */
            }
	});
```

* action：InvokeRead
```
	cyanoWebView.getNativeJsBridge().setHandleInvokeRead(new NativeJsBridge.HandleInvokeRead() {
            @Override
            public void handleAction(String data) {
              /* TODO
               * 1.Build a transaction with data, pre-execute to get results, and pay attention to time-consuming operations.
               * 
               * 2.Send the predictive action result to webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, Pre-execution results);
               */
            }
	});
```

* action：InvokePasswordFree
```
	cyanoWebView.getNativeJsBridge().setHandleInvokePasswordFree(new NativeJsBridge.HandleInvokePasswordFree() {
            @Override
            public void handleAction(String data, String message) {
              /* TODO
               * 1.The first operation is the same as action: Invoke, saving both password and message
               *
               * 2.When the same message is received the second time, it will be signed with the saved password, and the result will be obtained with the predicted action.
               *
               * 3.Predictive action results need not be displayed for user confirmation
               *
               * 4.Send transaction hash to webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, Transaction hash);
               */
            }
	});
```
* Failed return result


```
	/*
	* Called when an error return is required
	*/
	cyanoWebView.sendFailToWeb(action,error,version,id,result);

    action：Request action

    version：Request version

    id：Request id

    error：Error code
    
    result：The results after treatment
```   

[Error code](https://github.com/ontio-cyano/CEPs/blob/master/CEPS/CEP1.mediawiki#error-code)

## How_to_use_ONTID

* ONT ID Authentication

```
  mWebView.getNativeJsBridge().setAuthentication(new NativeJsBridge.HandleAuthentication() {
            @Override
            public void handleAction(String data) {
                JSONObject jsonObject = JSON.parseObject(data);
                String subAction = jsonObject.getJSONObject("params").getString("subaction");
                switch (subAction) {
                    case "getIdentity":
              /* TODO
               * 1.Send ONT_ID to webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, ONT_ID);
               */
                        break;
                    case "submit":
              /* TODO
               * 1.Send the returned H5 content back to the wallet backstage
               * 2.Return the sent result
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, true);
               */
                        break;
                    case "getRegistryOntidTx":
              /* TODO
               * 1.Send saved ONT_ID transactions to webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, ONT_ID Transaction Content);
               */
                        break;
                    default:
                }
            }
        });
```

* ONT ID authorization

```

        mWebView.getNativeJsBridge().setAuthorization(new NativeJsBridge.HandleAuthorization() {
            @Override
            public void handleAction(String data) {
                JSONObject jsonObject = JSON.parseObject(data);
                String subAction = jsonObject.getJSONObject("params").getString("subaction");
                switch (subAction) {
                    case "requestAuthorization":
              /* TODO
               * 1.Save the received data and jump to the authorization interface
               * mWebView.loadUrl(Constant.CYANO_AUTH_URL);
               */
                        break;
                    case "getAuthorizationInfo":
              /* TODO
               * 1.Change the subaction that saves the data to getAuthorizationInfo
               * 
               * 2.Return the result
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, Preserved data);
               */
                        break;
                    case "decryptClaim":
               /* TODO
               * 1.Show the password dialog to process encrypted data
               *  JSONArray parse = jsonObject.getJSONObject("params").getJSONArray("message");
               * String[] datas = new String[parse.size()];
               * for (int i = 0; i < parse.size(); i++) {
                    datas[i] = parse.getString(i);
               * }
               *
               * 2.Decrypt, get the result of decryption
               *
               * 3.Return the result
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, Decryption results);
               */
                        break;
                    case "deleteOntid":
              /* TODO
               * 1.Pop-up password box to verify ONT ID password
               * 
               * 2.Delete the local ONT ID and close the page after the password is successful
               */
                        break;
                    default:
                }
            }
        });
```

## ONTID_Rapid_integration
We have encapsulated the ONT ID related processing and can use it directly if there is no customized page.

* add permission
 
```
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.SYSTEM_OVERLAY_WINDOW" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
```

* register activity
 
```
    <activity android:name="com.github.ont.connector.ontid.CreateOntIdActivity" />
    <activity android:name="com.github.ont.connector.ontid.ImportOntIdActivity" />
    <activity android:name="com.github.ont.connector.ontid.OntIdWebActivity" />
    <activity android:name="com.github.ont.connector.ontid.TestFrameActivity" />
    <activity android:name="com.github.ont.connector.ontid.ExportOntIdActivity" />
```

* add ONT lib

Copy the repositories folder to the project

Add build. gradle to the exterior of the project
```
allprojects {
    repositories {
        flatDir {
            dirs '../repositories'
        }
    }
}
```

If the wallet needs integration, add it to the corresponding build. gradle file
```
  implementation(name: 'ontolib-release', ext: 'aar')
```

* Photo selection

Modify the Picture Selector Library in [build.gradle](https://github.com/ontio-cyano/cyano-android-sdk/blob/master/cyano_lib/build.gradle), which is currently used by Matisse Picture Selector

Modify the corresponding image processing in [com.github.ont.connector.update.ImageUtil](https://github.com/ontio-cyano/cyano-android-sdk/blob/master/cyano_lib/src/main/java/com/github/ont/connector/update/ImageUtil.java)

* Network request

Modify the network framework in [build.gradle](https://github.com/ontio-cyano/cyano-android-sdk/blob/master/cyano_lib/build.gradle), currently using okhttp framework

Modify the corresponding network request in [com.github.ont.connector.update.NetUtil](https://github.com/ontio-cyano/cyano-android-sdk/blob/master/cyano_lib/src/main/java/com/github/ont/connector/update/NetUtil.java)

* Setting the storage path of wallet file

Modify the WALLET_FILE fields in [Constant](https://github.com/ontio-cyano/cyano-android-sdk/blob/master/cyano_lib/src/main/java/com/github/ont/cyano/Constant.java) so that ont SDK stores wallet files with the same path

* Setting the Receive Address of the Server

Modify the WALLET_RECEIVER_URL field in [Constant](https://github.com/ontio-cyano/cyano-android-sdk/blob/master/cyano_lib/src/main/java/com/github/ont/cyano/Constant.java) and return [authenticationId](https://github.com/ontio-cyano/integration-docs/blob/master/%E9%92%B1%E5%8C%85%E5%AF%B9%E6%8E%A5-OntId%E8%AE%A4%E8%AF%81.md),submit to the wallet server.

* start-up

Start the ONT ID interface, the private key of ONT ID, password and ONT default wallet are the same. Before entering the interface, you need to check whether the wallet has been created.


```
        String defaultAccountAddress = OntSdk.getInstance().getWalletMgr().getWallet().getDefaultAccountAddress();

        if (TextUtils.isEmpty(defaultAccountAddress)) {
        //TODO Handling of Uncreated Wallet
        } else {
                    Intent intent = new Intent(baseActivity, TestFrameActivity.class);
                    startActivity(intent);
        }
```

## DEMO
[cyano-android](https://github.com/ontio-cyano/cyano-android)

## Download
[Click me to download](http://101.132.193.149/files/app-debug.apk)

## version
0.0.1
