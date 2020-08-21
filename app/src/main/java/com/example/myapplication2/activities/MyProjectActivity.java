package com.example.myapplication2.activities;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication2.R;
import com.example.myapplication2.model.Project;
import com.example.myapplication2.observer.BaseObserver;
import com.example.myapplication2.retrofit.RetrofitHandler;
import com.example.myapplication2.util.RxTransformerHelper;
import com.example.myapplication2.util.response.UniversalResponseBody;

import java.util.List;

public class MyProjectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProjectAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);
        initUi();
    }

    private void initUi() {
        recyclerView = (RecyclerView)findViewById(R.id.recycle_view_project);
        adapter = new ProjectAdapter();
        getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpaceItemDecoration(24));
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        RetrofitHandler.getInstance().getRequestApi()
                .getMyProject()
                .compose(RxTransformerHelper.<UniversalResponseBody<List<Project>>>observableIO2Main(this))
                .subscribe(new BaseObserver<List<Project>>() {
                    @Override
                    protected void onSuccess(UniversalResponseBody<List<Project>> responseBody) {
                        adapter.setLoadState(ProjectAdapter.LOADING);
                        adapter.addNewDataForComponent(responseBody.getData());
                        adapter.setLoadState(ProjectAdapter.LOADING_COMPLETE);
                    }

                    @Override
                    protected void onFailure(UniversalResponseBody<List<Project>> responseBody, String errMsg) {
                        Log.w("err","err");
                    }
                });
    }
    
}
