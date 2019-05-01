package com.interview.twoscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.interview.twoscreen.apis.User;
import com.interview.twoscreen.apis.UserResponse;
import com.interview.twoscreen.apis.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnUserClickListener {

    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userRecyclerView = findViewById(R.id.user_recyclerview);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        testLoadUsers();
    }

    private void testLoadUsers() {
        UserService service = UserService.Creator.create();

        service.getUsers("50").enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                List<User> users = response.body().results;

                userAdapter = new UserAdapter(users, MainActivity.this);
                userRecyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("test", "error!");
            }
        });
    }

    @Override
    public void onClickItem(User user) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
