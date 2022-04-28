package com.example.pw_task;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Teacher_Details_Fragment extends Fragment {
   public RecyclerView recyclerView_teacher_details;
    Teacher_Details_Adapter teacher_details_adapter;
    ProgressBar progressBar_teacher_details;
    TextView textView_no_internet_msg_teahcer_details;
    List<Teacher_Detail_Model_Class> teacher_detail_model_classes;
    String response, responseTrack;
    boolean isConnected;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_techer_details, container, false);
       recyclerView_teacher_details= view.findViewById(R.id.RecyclerView_Teacher_Details);
        progressBar_teacher_details=view.findViewById(R.id.progressBar_Teacher_details);
        teacher_detail_model_classes=new ArrayList<>();
        teacher_details_adapter=new Teacher_Details_Adapter(teacher_detail_model_classes,getActivity());

        //Recyclerview
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_teacher_details.setLayoutManager(linearLayoutManager);
        recyclerView_teacher_details.setAdapter(teacher_details_adapter);

        //Check Internet Connection
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        getData();
        recyclerView_teacher_details.getAdapter().notifyDataSetChanged();







        return view;
    }
    public void getData() {
        super.onPause();
        //Program for fetching data
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024); // 1MB cap
// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
// Instantiate the RequestQueue with the cache and network.
        RequestQueue requestQueue = new RequestQueue(cache, network);

// Start the queue
        if(isConnected){
            requestQueue.start();
            StringRequest request=new StringRequest(Request.Method.GET,getString(R.string.API_Link),new Response.Listener<String>(){

                @Override
                public void onResponse(String response) {
                    try {
                        //Parsing of response
                        JSONArray jsonArray = new JSONArray(response);

                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            Gson gson=new Gson();
                            ArrayList<String> subject_list,qualification_list;
                            subject_list = gson.fromJson(String.valueOf(jsonObject.get("subjects")), (Type) ArrayList.class);
                            qualification_list = gson.fromJson(String.valueOf(jsonObject.get("qualification")), (Type) ArrayList.class);

                            Teacher_Detail_Model_Class teacher_detail_model_class = new Teacher_Detail_Model_Class();
                            teacher_detail_model_class.setId(jsonObject.getInt("id"));
                            teacher_detail_model_class.setProfileImage(jsonObject.getString("profileImage"));
                            teacher_detail_model_class.setName(jsonObject.getString("name"));
                            teacher_detail_model_class.setSubjects(getConvertedString(subject_list));
                            teacher_detail_model_class.setQualification(getConvertedString(qualification_list));

                            teacher_detail_model_classes.add(teacher_detail_model_class);

                        }

                        recyclerView_teacher_details.getAdapter().notifyDataSetChanged();

                        progressBar_teacher_details.setVisibility(View.INVISIBLE);
                    } catch (JSONException e) {

                        Toast.makeText(getActivity(), getString(R.string.Data_Conversion_Error),Toast.LENGTH_SHORT).show();
                        e.printStackTrace();

                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error
                            Toast.makeText(getActivity(), getString(R.string.Data_Conversion_Error),Toast.LENGTH_SHORT).show();

                        }

                    });
            requestQueue.add(request);



        }
        else{
            //Message fo no internet connection
            Toast.makeText(getActivity(), getString(R.string.Internet_Warning),Toast.LENGTH_SHORT).show();

        }

    }

    private String getConvertedString(ArrayList<String> subject_list) {
    //Code to convert String array to list
            if(subject_list.size()==1){
            return subject_list.get(0);
        }

        else {

            if(subject_list.size()<1){
                return "";
            }

            else {
                int i;
                String result="";

                for(i=0;i<subject_list.size();i++){
                    result=result+subject_list.get(i)+" ";
                    if (i==subject_list.size()-1){
                        break;
                    }
                    result=result+"• ";
                }
                return result;
            }
        }
    }


}