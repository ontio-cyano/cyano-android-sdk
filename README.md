# cyano-android-sdk
cyano-android-sdk 帮助Android webview和网页dapp纪念性通信。它对Android webview进行了一些方法的封装。  
>webview通信的方式是window.postmeaage()

## 如何使用
将工程当作module导入到项目中
###示例

* 初始化
 
```
	CyanoWebView cyanoWebView=new CyanoWebView(context);  
	cyanoWebView.loadUrl(url);
```


* action：login

```
	mWebView.getNativeJsBridge().setHandleLogin(new NativeJsBridge.HandleLogin() {
            @Override
            public void handleAction(final String data) {
            }
	});
```

* action：Invoke
* action：GetAccount

## 版本
0.0.1
