# cyano-android-sdk
cyano-android-sdk 帮助Android webview和网页dapp之间通信。它对Android webview进行了一些方法的封装。  
>webview通信的方式是window.postmeaage()

## 如何使用
将工程当作module导入到项目中

### 示例

#### 初始化
 
```
	CyanoWebView cyanoWebView=new CyanoWebView(context);  
	cyanoWebView.loadUrl(url);
```

#### data
参数data为json的字符串

```
	{"action":"login",
	"params":
		{
		"type":"account",
		"dappName":"My dapp",
		"message":"test message",
		"expired":"201812181000",
		"callback":""
		}
	}
```
#### message
参数message为网页端传来的原文数据。

#### action：login

```
	cyanoWebView.getNativeJsBridge().setHandleLogin(new NativeJsBridge.HandleLogin() {
            @Override
            public void handleAction(String data) {

            }
	});
```

#### action：Invoke
```
	cyanoWebView.getNativeJsBridge().setHandleInvoke(new NativeJsBridge.HandleInvoke() {
            @Override
            public void handleAction(String data) {
               
            }
	});
```

#### action：GetAccount
```
	cyanoWebView.getNativeJsBridge().setHandleGetAccount(new NativeJsBridge.HandleGetAccount() {
            @Override
            public void handleAction(String data) {
             
            }
	});
```

#### action：GetAccount
```
	cyanoWebView.getNativeJsBridge().setHandleInvokeRead(new NativeJsBridge.HandleInvokeRead() {
            @Override
            public void handleAction(String data) {
               
            }
	});
```

#### action：InvokePasswordFree
```
	cyanoWebView.getNativeJsBridge().setHandleInvokePasswordFree(new NativeJsBridge.HandleInvokePasswordFree() {
            @Override
            public void handleAction(String data, String message) {
               
            }
	});
```

#### action：SendBack
将结果传回网页，fastjson转换。
```
	Map map = new HashMap<>();
	map.put("action", "");
	map.put("error", 0);
	map.put("desc", "SUCCESS");
	map.put("result", message);
	cyanoWebView.sendBack(Base64.encodeToString(Uri.encode(JSON.toJSONString(map)).getBytes(), Base64.NO_WRAP));
```

## DEMO
[cyano-android](https://github.com/ontio-cyano/cyano-android)

## 版本
0.0.1
