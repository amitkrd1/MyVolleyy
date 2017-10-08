package com.example.amit.myvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    ArrayList<DepartmentSetGet> arr=null;
 //   RequestQueue queue;
    RecyclerView rv;


    String username, pwd, msg;

    String url = "http://220.225.80.177/drbookingapp/bookingapp.asmx/GetDepartment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.rv);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(lm);
      //  queue = Volley.newRequestQueue(this);
        fetchJsonResponse();









    }


    public void fetchJsonResponse() {

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                arr=new ArrayList<DepartmentSetGet>();


                try {
                    JSONArray deplist=response.getJSONArray("DepartmentDetails");

                    for(int i=0;i<deplist.length();i++){

                        JSONObject depdetail=deplist.getJSONObject(i);
                        String depid=depdetail.getString("departmentid");
                        Log.v("depid",depid);
                        String depName=depdetail.getString("departmentname");
                        DepartmentSetGet dsg=new DepartmentSetGet();
                        dsg.setDepartmentId(depid);
                        dsg.setDepartmentName(depName);
                        arr.add(dsg);
                        MyAdapter adapter=new MyAdapter(arr);
                        rv.setAdapter(adapter);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(req);
    }
}