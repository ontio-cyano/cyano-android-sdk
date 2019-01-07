# cyano-android-sdk
cyano-android-sdk 帮助Android webview和网页dapp之间通信。它对Android webview进行了一些方法的封装。  
>webview通信的方式是window.postmeaage()

## 如何使用
将工程当作module导入到项目中

[数据格式请参照CEP1文档](https://github.com/ontio-cyano/CEPs/blob/master/CEP1.mediawiki)

#### 初始化
 
```
	CyanoWebView cyanoWebView=new CyanoWebView(context);  
	cyanoWebView.loadUrl(url);
```


#### action：login

```
	cyanoWebView.getNativeJsBridge().setHandleLogin(new NativeJsBridge.HandleLogin() {
            @Override
            public void handleAction(String data) {
            /* TODO
            * 1. params参数中解析出message
            * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
            * String message=reqJson.getJSONObject("params").getString("message");
            *
            * 2.弹出密码框,解出钱包account,对message进行签名，注意耗时操作。
            *
            * 3. 拼接返回结果，包括：publicKey、type、user message signature
            *  JSONObject result = new JSONObject();
            *   result.put("publickey", 钱包公钥);
            *   result.put("type", "account");
            *   result.put("user", 钱包地址);
            *   result.put("message", message);
            *   result.put("signature", 签名结果);
            * 
            * 4.发送返回结果到webView
            * String action=reqJson.getString("action");
            * String version=reqJson.getString("version");
            * String id=reqJson.getString("id");
            * cyanoWebView.sendSuccessToWeb(action,version, id, result);
            */
            }
	});
```


#### action：Invoke
```
	cyanoWebView.getNativeJsBridge().setHandleInvoke(new NativeJsBridge.HandleInvoke() {
            @Override
            public void handleAction(String data) {
              /* TODO
               * 1.弹出密码输入框，解出钱包account，将data构建交易，对交易进行签名，预执行获取结果，注意耗时操作。
               *
               * 2.将预知行结果解析出Notify结果，显示手续费，如果结果中包含ONT,ONG合约地址，需显示转账金额和收款地址，
               *
               * 3.用户确认后发送交易到链上
               *
               * 3.发送返回结果到webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, 交易hash);
               */
            }
	});
```

#### action：GetAccount
```
	cyanoWebView.getNativeJsBridge().setHandleGetAccount(new NativeJsBridge.HandleGetAccount() {
            @Override
            public void handleAction(String data) {
              /* TODO
               * 1.发送返回结果到webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, 钱包地址);
               */
            }
	});
```

#### action：InvokeRead
```
	cyanoWebView.getNativeJsBridge().setHandleInvokeRead(new NativeJsBridge.HandleInvokeRead() {
            @Override
            public void handleAction(String data) {
              /* TODO
               * 1.将data构建交易，预执行获取结果，注意耗时操作。
               * 
               * 2.发送返回结果到webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, 预知行结果);
               */
            }
	});
```

#### action：InvokePasswordFree
```
	cyanoWebView.getNativeJsBridge().setHandleInvokePasswordFree(new NativeJsBridge.HandleInvokePasswordFree() {
            @Override
            public void handleAction(String data, String message) {
              /* TODO
               * 1.第一次操作和action：Invoke相同，同时保存password和message
               *
               * 2.当第二次收到相同的message时候，将用保存的密码进行签名，预知行获取结果
               *
               * 3.预知行结果不用显示给用户确认
               *
               * 3.发送返回结果到webView
               * com.alibaba.fastjson.JSONObject reqJson = JSON.parseObject(data);
               * String action=reqJson.getString("action");
               * String version=reqJson.getString("version");
               * String id=reqJson.getString("id");
               * cyanoWebView.sendSuccessToWeb(action,version, id, 交易hash);
               */
            }
	});
```
#### 失败返回结果


```
	/*
	* 需要错误返回时调用
	*/
	cyanoWebView.sendFailToWeb(action,error,version,id,result);
```

1. action：请求的action

2. version：请求的version

3. id：请求的id

5. error：失败时的[错误码](https://github.com/ontio-cyano/CEPs/blob/master/CEP1.mediawiki#Error_code)

4. result：处理后的结果


## DEMO
[cyano-android](https://github.com/ontio-cyano/cyano-android)

## 版本
0.0.1
