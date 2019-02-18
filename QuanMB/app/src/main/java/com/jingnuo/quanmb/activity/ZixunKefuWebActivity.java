package com.jingnuo.quanmb.activity;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class ZixunKefuWebActivity extends BaseActivityother {
    //控件
    private WebView webView;
    ProgressBar mPrigressBer;
    TextView textview_webtitle;


    String web_title = "";
    String type = "";
    String url = "";

    @Override
    public int setLayoutResID() {
        return R.layout.activity_zixun_kefu_web;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        WebSettings settings = webView.getSettings();
        web_title = getIntent().getStringExtra("webtitle");
        type = getIntent().getStringExtra("type");
        url = getIntent().getStringExtra("URL");
        textview_webtitle.setText(web_title);
        if (type.equals("全民帮客服中心")) {
            url = Urls.Baseurl_zixunkefu;
        } else if (type.equals("首页弹窗")) {
            if(Staticdata.static_userBean.getData()==null){
                url = Urls.newpeoplecoupon;
            }else {
                url = Urls.newpeoplecoupon+"?mobile_no="+ Staticdata.static_userBean.getData().getAppuser().getMobile_no();
            }
            settings.setUseWideViewPort(true);
        }else if(type.equals("优惠活动")){
            url = Urls.Baseurl_index+ Staticdata.static_userBean.getData().getAppuser().getClient_no();
        }
        else if(type.equals("匹配商家界面")){
            url = Urls.pipeijiemianhuodong+"?mobile_no="+ Staticdata.static_userBean.getData().getAppuser().getMobile_no();
            settings.setUseWideViewPort(true);
        }else if(type.equals("生活圈")){
            settings.setUseWideViewPort(true);
        }
        // 设置与Js交互的权限
        settings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缩放级别
        settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        // 支持缩放
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);//开启DOM storage API功能
        settings.setAllowFileAccess(true);
        // 将网页内容以单列显示
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);

//        settings.setAllowFileAccess(true);
//        settings.setAllowFileAccessFromFileURLs(true);
//        settings.setAllowUniversalAccessFromFileURLs(true);

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                if(request.startsWith("alipays:") || request.startsWith("alipay")) {
                    try {
                        ZixunKefuWebActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(request)));
                    } catch (Exception e) {
                    new AlertDialog.Builder(ZixunKefuWebActivity.this)
                            .setMessage("未检测到支付宝客户端，请安装后重试。")
                            .setPositiveButton("立即安装", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Uri alipayUrl = Uri.parse("https://d.alipay.com");
                                    ZixunKefuWebActivity.this.startActivity(new Intent("android.intent.action.VIEW", alipayUrl));
                                }
                            }).setNegativeButton("取消", null).show();
                }
                    return true;
                }
                else {
                    if (request.startsWith("https://wx.tenpay.com/")) {
                        Map<String, String> extraHeaders = new HashMap<>();
                        extraHeaders.put("Referer", Urls.H5weixinPay);
                        view.loadUrl(request, extraHeaders);
                        return true;
                    }

                    if (request.startsWith("weixin://wap/pay?")) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(request));
                        startActivity(intent);
                        return true;
                    }

                }

                view.loadUrl(request);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);//错误提示
//
//                Intent intent=new Intent(ZixunKefuWebActivity.this,KongActivity.class);
//                startActivity(intent);
//                finish();
//            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    mPrigressBer.setVisibility(View.GONE);//加载完网页进度条消失
                    LogUtils.LOG("ceshi", webView.getUrl(), "网..址");

                } else {
                    mPrigressBer.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mPrigressBer.setProgress(newProgress);//设置进度值
                }


            }


            //扩展浏览器上传文件
            //3.0++版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                openFileChooserImpl(uploadMsg);
            }

            //3.0--版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooserImpl(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooserImpl(uploadMsg);
            }


            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

                LogUtils.LOG("ceshi", "图片选择", "tupian");
                onenFileChooseImpleForAndroid(filePathCallback);
                return true;

            }

//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
////                Uri uri = Uri.parse(message);
////                if ( uri.getScheme().equals("ete")) {
////
////                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
////                    // 所以拦截url,下面JS开始调用Android需要的方法
////                    Log.i("ceshi",uri.getAuthority()+".....2");
////                    webView.stopLoading();
////                    if(uri.getAuthority().equals("scan")){
////                        erweima();
////                    }else {
////                        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
////                        cm.setText(uri.getAuthority());
////                        ToastUtils.showToast(MainActivity.this,"复制到剪贴板成功");
////                    }
////                    result.cancel();
////                    return true;
////                }
//                return true;
//            }
        });
    }

    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;
    public ValueCallback<Uri> mUploadMessage;

    private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
    }

    public ValueCallback<Uri[]> mUploadMessageForAndroid5;

    private void onenFileChooseImpleForAndroid(ValueCallback<Uri[]> filePathCallback) {
        mUploadMessageForAndroid5 = filePathCallback;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;

        } else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            if (null == mUploadMessageForAndroid5)
                return;
            Uri result = (data == null || resultCode != RESULT_OK) ? null : data.getData();
            if (result != null) {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
            } else {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
            }
            mUploadMessageForAndroid5 = null;
        }
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.webview);
        textview_webtitle = findViewById(R.id.textview_maintitle);
        mPrigressBer = findViewById(R.id.pb);

    }
}
