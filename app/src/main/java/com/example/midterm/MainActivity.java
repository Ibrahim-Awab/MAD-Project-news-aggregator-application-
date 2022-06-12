package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    List<NewsF>itemList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.data_list);
        Adapter customAdapter = new Adapter(getBaseContext(), R.layout.list,itemList);
        listView.setAdapter(customAdapter);
        OkHttpClient client = new OkHttpClient();

        String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if (response.isSuccessful())
                {
                    final String myResponse = response.body().string();
                    try
                    {
                        JSONObject jsonObject=new JSONObject(myResponse);
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            int id=jsonArray.getJSONObject(i).getInt("id");
                            String heading=jsonArray.getJSONObject(i).getString("heading");
                            String desc=jsonArray.getJSONObject(i).getString("description");
                            String url=jsonArray.getJSONObject(i).getString("url");
                            NewsF item=new NewsF(id,desc,url,heading);
                            itemList.add(item);
                        }

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                customAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,itemList.get(i).getUrl());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });




    }
}