package com.hofo.oknet;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hofo.oknetlib.OkNet;
import com.hofo.oknetlib.callback.JSONObjectDialogCallBack;
import com.hofo.oknetlib.response.Response;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkNet.getInstance().init(getApplication());
    }

    public void Get(View view) {
        String url = "http://47.101.33.180:8090/api/PdaV2/QueryPartition?UserId=39";
        OkNet.get(url)
                .params("a", "a")
                .params("c", "zx")
                .params("wfzxc", "asdfwe")
                .params("zxcw", "zxcvw")
                .execute(new JSONObjectDialogCallBack(this) {
                    @Override
                    public void onSuccess(Response<JSONObject> response) {
                        super.onSuccess(response);
                        Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
