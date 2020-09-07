package com.example.realmsecure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.realmsecure.Models.Student;

import java.security.SecureRandom;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {
    Button insert;
    private Realm realm;
    RecyclerView rv;
    MyRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(getApplicationContext());

        insert = findViewById(R.id.insertBtn);
        rv = findViewById(R.id.rv);

        byte[] key = "TechWithVPTechWithVPTechWithVPTechWithVPTechWithVPTechWithVPTech".getBytes();
        System.out.println("Key: " + Util.bytesToHex(key));
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("myrealmsecure.realm").encryptionKey(key).build();
        realm = Realm.getInstance(config);
        updateRV();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Number num = realm.where(Student.class).max("std_id");
                        int nextID;
                        if (num == null) {
                            nextID = 1;
                        } else {
                            nextID = num.intValue() + 1;
                        }
                        realm.copyToRealm(new Student(nextID, "TechWithVP " + String.valueOf(nextID), 23 + nextID));
                    }
                });
            }
        });
    }

    public List<Student> getData() {
        RealmResults<Student> result = realm.where(Student.class).sort("std_id", Sort.DESCENDING).findAll();
        return result;
    }

    public void updateRV() {
        adapter = new MyRVAdapter(getData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}