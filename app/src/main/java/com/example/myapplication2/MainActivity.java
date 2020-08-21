package com.example.myapplication2;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication2.activities.MyProjectActivity;
import com.example.myapplication2.observer.BaseObserver;
import com.example.myapplication2.retrofit.RetrofitHandler;
import com.example.myapplication2.util.RxTransformerHelper;
import com.example.myapplication2.util.response.UniversalResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = ((EditText)findViewById(R.id.username)).getText().toString();
                final String password = ((EditText)findViewById(R.id.password)).getText().toString();
                //showTos();
                login(userName,password);
            }
        });
    }

    public void showTos(){
        Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
    }

    public void showErrTos(){
        Toast.makeText(MainActivity.this,"err",Toast.LENGTH_LONG).show();
    }

    public void login(String userName,String password) {
        Log.i(TAG,userName);
        RetrofitHandler.getInstance().getRequestApi()
                .login(userName,password)
                .compose(RxTransformerHelper.<UniversalResponseBody<String>>observableIO2Main(this))
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onSuccess(UniversalResponseBody<String> responseBody) {
                        showTos();
                        Intent next = new Intent(MainActivity.this,MyProjectActivity.class);
                        startActivity(next);
                    }

                    @Override
                    protected void onFailure(UniversalResponseBody<String> responseBody, String errMsg) {
                        showErrTos();
                    }

                });
    }
}
