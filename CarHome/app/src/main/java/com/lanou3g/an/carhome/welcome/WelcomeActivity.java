package com.lanou3g.an.carhome.welcome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.main.MainActivity;
import com.lanou3g.an.carhome.beas.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anfeng on 16/5/9.
 */
public class WelcomeActivity extends BaseActivity {

    private TextView welcomeTv;
    private ImageView welcomeIV;
    private CountDownTimer time;
    private InputStream is;

    @Override
    protected int getLayout() {
        return R.layout.activity_welocome;
    }

    @Override
    protected void initView() {
        welcomeIV = bindView(R.id.welocme_iv);
        welcomeTv = bindView(R.id.welocme_tv);
    }

    @Override
    protected void initData() {
        welcomeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });

        time = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                welcomeTv.setText("点击跳过" + millisUntilFinished / 1000 + "s");
                //millisUntilFinished:剩下的毫秒数
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }

    //后台不运行
    @Override
    protected void onPause() {
        super.onPause();
        time.cancel();
    }

    //重新启动
    @Override
    protected void onRestart() {
        super.onRestart();
        time.start();
    }

    class welcomeBipmap extends AsyncTask<String, Void, Bitmap> {

        private HttpURLConnection connection;

        @Override
        protected Bitmap doInBackground(String... params) {

            String imageUrl = params[0];
            try {
                URL url = new URL(imageUrl);
                connection = (HttpURLConnection) url.openConnection();
                is = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            welcomeIV.setImageBitmap(bitmap);
        }
    }

}
