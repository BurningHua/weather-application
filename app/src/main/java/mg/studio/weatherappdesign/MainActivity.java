package mg.studio.weatherappdesign;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadUpdate().execute();
    }

    public void btnClick(View view) {
        new DownloadUpdate().execute();

    }


    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = "https://cqu.andream.app/weather";
            HttpURLConnection urlConnection = null;
            BufferedReader reader;

            try {
                URL url = new URL(stringUrl);

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    Log.d("TAG", line);
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                //The temperature
                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String jsoninfo) {
            //Update the temperature displayed
            if(jsoninfo == null){
                    Toast.makeText(getApplicationContext(), "No network",Toast.LENGTH_LONG).show();}
            else {
                try {
                    JSONObject jsonObject = new JSONObject(jsoninfo);
                    Log.d("TAG", jsonObject.getString("location"));
                    JSONArray daily = jsonObject.getJSONArray("daily");
                    JSONObject forecast = daily.getJSONObject(0);
                    ((TextView) findViewById(R.id.tv_date)).setText(forecast.getString("date"));
                    ((TextView) findViewById(R.id.temperature_of_the_day)).setText(forecast.getString("high"));
                    int code_day = forecast.getInt("code_day");
                    ImageView imageView = (ImageView) findViewById(R.id.img_weather_condition);
                    switch (code_day) {
                        case 0:
                            imageView.setImageResource(R.drawable.sunny_small);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 9:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 13:
                            imageView.setImageResource(R.drawable.rainy_small);
                            break;
                        case 15:
                            imageView.setImageResource(R.drawable.rainy_up);
                            break;
                        case 32:
                            imageView.setImageResource(R.drawable.windy_small);
                            break;
                        default:
                            break;
                    }
                    String week[] = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
                    Calendar cal = Calendar.getInstance();
                    int i = cal.get(Calendar.DAY_OF_WEEK);
                    ((TextView) findViewById(R.id.date_of_week)).setText(week[i - 1]);
                    ((TextView) findViewById(R.id.next_day)).setText(week[i]);
                    ((TextView) findViewById(R.id.next_twoday)).setText(week[i + 1]);
                    ((TextView) findViewById(R.id.next_threeday)).setText(week[i + 2]);
                    ((TextView) findViewById(R.id.next_fourday)).setText(week[i + 3]);

                    forecast = daily.getJSONObject(1);
                    code_day = forecast.getInt("code_day");
                    imageView = (ImageView) findViewById(R.id.img_weather_condition1);
                    switch (code_day) {
                        case 0:
                            imageView.setImageResource(R.drawable.sunny_small);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 9:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 13:
                            imageView.setImageResource(R.drawable.rainy_small);
                            break;
                        case 15:
                            imageView.setImageResource(R.drawable.rainy_up);
                            break;
                        case 32:
                            imageView.setImageResource(R.drawable.windy_small);
                            break;
                        default:
                            break;
                    }

                    forecast = daily.getJSONObject(2);
                    code_day = forecast.getInt("code_day");
                    imageView = (ImageView) findViewById(R.id.img_weather_condition2);
                    switch (code_day) {
                        case 0:
                            imageView.setImageResource(R.drawable.sunny_small);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 9:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 13:
                            imageView.setImageResource(R.drawable.rainy_small);
                            break;
                        case 15:
                            imageView.setImageResource(R.drawable.rainy_up);
                            break;
                        case 32:
                            imageView.setImageResource(R.drawable.windy_small);
                            break;
                        default:
                            break;
                    }

                    forecast = daily.getJSONObject(3);
                    code_day = forecast.getInt("code_day");
                    imageView = (ImageView) findViewById(R.id.img_weather_condition3);
                    switch (code_day) {
                        case 0:
                            imageView.setImageResource(R.drawable.sunny_small);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 9:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 13:
                            imageView.setImageResource(R.drawable.rainy_small);
                            break;
                        case 15:
                            imageView.setImageResource(R.drawable.rainy_up);
                            break;
                        case 32:
                            imageView.setImageResource(R.drawable.windy_small);
                            break;
                        default:
                            break;
                    }

                    forecast = daily.getJSONObject(4);
                    code_day = forecast.getInt("code_day");
                    imageView = (ImageView) findViewById(R.id.img_weather_condition4);
                    switch (code_day) {
                        case 0:
                            imageView.setImageResource(R.drawable.sunny_small);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 9:
                            imageView.setImageResource(R.drawable.partly_sunny_small);
                            break;
                        case 13:
                            imageView.setImageResource(R.drawable.rainy_small);
                            break;
                        case 15:
                            imageView.setImageResource(R.drawable.rainy_up);
                            break;
                        case 32:
                            imageView.setImageResource(R.drawable.windy_small);
                            break;
                        default:
                            break;
                    }
                    Toast.makeText(getApplicationContext(), "Refresh successfully",Toast.LENGTH_LONG).show();


                    //for (int i = 0; i < 5; i++) {
                    //JSONObject forweather = forecast.getJSONObject(i);
                    //Log.d("TAG", forweather.getString("high"));
                    //System.out.println("姓名：" + forweather.getString("name"));
                    //System.out.println("性别：" + forweather.getString("gender"));
                    //System.out.println("年龄：" + forweather.getInt("age") + "\n");
                    //}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //((TextView) findViewById(R.id.temperature_of_the_day)).setText(jsoninfo);
            //((ImageView)findViewById((R.id.img_weather_condition)).setBackground(sunny_small));_
        }
    }


}
