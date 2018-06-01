package com.denzcoskun.libdenx.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.libdenx.R;
import com.denzcoskun.libdenx.interfaces.VolleyCallBack;
import com.denzcoskun.libdenx.models.BaseResponseModel;
import com.denzcoskun.libdenx.utils.NetworkUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;

/**
 * Created by Denx on 1.06.2018.
 */
abstract public class BaseFragment extends Fragment implements VolleyCallBack<BaseResponseModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public <T> void getJsonObject(String url, Class<T> responseModel, VolleyCallBack<T> callBack){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        ObjectMapper mapper = new ObjectMapper();
        queue.add(new JsonObjectRequest(Request.Method.GET, url, null, (JSONObject response) -> {
            try {
                callBack.OnSuccess(mapper.readValue(response.toString(),responseModel));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, error -> showMassage(R.string.volley_error)));
    }

    public void showMassage(String message){
        if(message != null){
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), getString(R.string.message_error), Toast.LENGTH_SHORT).show();
        }
    }

    public void showMassage(int message){
        showMassage(getString(message));
    }

    public boolean isNetworkConnected(){
        return NetworkUtils.isNetworkConnected(getActivity().getApplicationContext());
    }

    @Override
    public void OnSuccess(BaseResponseModel result) {

    }

    abstract protected int getLayoutResourceId();
}