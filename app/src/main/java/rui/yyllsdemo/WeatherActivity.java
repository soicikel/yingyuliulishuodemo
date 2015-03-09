package rui.yyllsdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import rui.yyllsdemo.beans.WeatherBean;
import rui.yyllsdemo.netrequest.GsonRequest;

/**
 * Created by rui on 15/3/9.
 */
public class WeatherActivity extends ActionBarActivity {

    private static final String TEST_URL = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=101010100&imei=529e2dd3d767bdd3595eec30dd481050&device=pisces&miuiVersion=JXCCNBD20.0&modDevice=&source=miuiWeatherApp";

    private TextView mCityTextView;

    private TextView mWeatherTextView;

    private RequestQueue mRequestQueue;

    private GsonRequest<WeatherBean> mWeatherRequest;

    private SharedPreferences mSharedPreferences;

    private Response.Listener<WeatherBean> mWeatherRespListener = new Response.Listener<WeatherBean>() {
        @Override
        public void onResponse(WeatherBean response) {
            if (null != response) {
                String city = null;
                String weather = null;
                if (null != response.forecast && !TextUtils.isEmpty(response.forecast.city)) {
                    city = response.forecast.city;
                    mCityTextView.setText(city);
                }
                if (null != response.realtime && !TextUtils.isEmpty(response.realtime.weather)) {
                    weather = response.realtime.weather;
                    mWeatherTextView.setText(weather);
                }

                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("city", city);
                editor.putString("weather", weather);
                editor.apply();
            }

            mWeatherRequest = null;
        }
    };

    private Response.ErrorListener mWeatherRespErrListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(WeatherActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            mWeatherRequest = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化volley队列
        mRequestQueue = Volley.newRequestQueue(this);
        mSharedPreferences = getSharedPreferences("weather", Activity.MODE_PRIVATE);

        setContentView(R.layout.activity_weather);
        mCityTextView = (TextView) findViewById(R.id.city_text_view);
        mWeatherTextView = (TextView) findViewById(R.id.weather_text_view);

        findViewById(R.id.check_weather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWeather();
            }
        });
    }

    private void checkWeather() {
        if (null != mWeatherRequest) {
            return;
        }
        mWeatherRequest = new GsonRequest<WeatherBean>(Request.Method.GET,
                TEST_URL, mWeatherRespErrListener, WeatherBean.class,
                mWeatherRespListener);
        mRequestQueue.add(mWeatherRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRequestQueue.cancelAll(this);
    }
}
