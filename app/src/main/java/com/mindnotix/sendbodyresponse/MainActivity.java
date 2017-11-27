package com.mindnotix.sendbodyresponse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button bt_send;
    List<CheckList> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringList = new ArrayList<>();


        bt_send = findViewById(R.id.bt_send);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<SendCheckOutResponse> sendCheckOutResponses = new ArrayList<>();


                for (int i = 0; i < stringList.size(); i++) {
                    sendCheckOutResponses.add(new SendCheckOutResponse(" ",
                            stringList.get(i).getPrice(),
                            " ",
                            stringList.get(i).getProduct_name()));
                }

                BasicSend basicSend = new BasicSend(sendCheckOutResponses);


                checkOut(basicSend);


            }
        });
    }


    Call<BasicResponse> call;


    private void checkOut(BasicSend update) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        call = apiService.updateSkills(update);

        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.body().getSuccess() == 1) {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        if (call != null) {
            call.cancel();
        }
        super.onDestroy();
    }
}
