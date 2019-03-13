package com.digital4africa.alvin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.digital4africa.alvin.Adapters.Post_Adapter;
import com.digital4africa.alvin.Fetchers.Post_Fetcher;
import com.digital4africa.alvin.Network.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {


    private String data_url="https://digital4africa.com/api/get_recent_posts/";

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        recyclerView=(RecyclerView)findViewById(R.id.postrecycler);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);




        getdata();


    }

    private void getdata() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, data_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.INVISIBLE);


                ArrayList<String> postid=new ArrayList<>();

                ArrayList<String> posttitle=new ArrayList<>();


                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("posts");

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject object=jsonArray.getJSONObject(i);


                        String id=object.getString("id");

                        String title=object.getString("title");

                        postid.add(id);

                        posttitle.add(title);



                    }


                    ArrayList data=prepare_data(postid,posttitle);

                    Post_Adapter post_adapter=new Post_Adapter(getApplicationContext(),data);

                    recyclerView.setAdapter(post_adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private ArrayList prepare_data(ArrayList<String> postid, ArrayList<String> posttitle) {


        ArrayList data=new ArrayList();

        for (int i=0;i<postid.size();i++){

            Post_Fetcher post_fetcher=new Post_Fetcher();

            post_fetcher.setPostid(postid.get(i));

            post_fetcher.setPost_title(posttitle.get(i));

            data.add(post_fetcher);
        }

        return  data;
    }

}
