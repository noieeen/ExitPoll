package com.example.noie.exitpoll;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.noie.exitpoll.adapter.VoteListAdapter;
import com.example.noie.exitpoll.db.DatabaseHelper;
import com.example.noie.exitpoll.model.VoteItem;
import com.example.noie.exitpoll.adapter.VoteListAdapter;
import com.example.noie.exitpoll.db.DatabaseHelper;
import com.example.noie.exitpoll.model.VoteItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.noie.exitpoll.db.DatabaseHelper.COL_ID;
import static com.example.noie.exitpoll.db.DatabaseHelper.COL_IMAGE;
import static com.example.noie.exitpoll.db.DatabaseHelper.COL_NAME;
import static com.example.noie.exitpoll.db.DatabaseHelper.COL_SCORE;
import static com.example.noie.exitpoll.db.DatabaseHelper.TABLE_NAME;

public class ScoreActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<VoteItem> mVoteItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mHelper = new DatabaseHelper(ScoreActivity.this);
        mDb = mHelper.getWritableDatabase();

        loadPhoneData();
        setupListView();

        Button deleteScore = findViewById(R.id.delete_button);
        deleteScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv0 = new ContentValues();
                cv0.put(COL_NAME, "no");
                cv0.put(COL_SCORE,"0");
                cv0.put(COL_IMAGE,"vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv0,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );

                ContentValues cv1 = new ContentValues();
                cv1.put(COL_NAME, "1");
                cv1.put(COL_SCORE,"0");
                cv1.put(COL_IMAGE,"one.png");


                mDb.update(
                        TABLE_NAME,
                        cv1,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );

                ContentValues cv2 = new ContentValues();
                cv2.put(COL_NAME, "2");
                cv2.put(COL_SCORE,"0");
                cv2.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv2,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );

                ContentValues cv3 = new ContentValues();
                cv3.put(COL_NAME, "3");
                cv3.put(COL_SCORE,"0");
                cv3.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv3,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );

                loadPhoneData();
                setupListView();
            }
        });

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPhoneData();
        setupListView();
    }

    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mVoteItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String name = c.getString(c.getColumnIndex(COL_NAME));
            String score = c.getString(c.getColumnIndex(COL_SCORE));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            VoteItem item = new VoteItem(id, name, score,image);
            mVoteItemList.add(item);
        }
        c.close();
    }
    private void setupListView() {
        VoteListAdapter adapter = new VoteListAdapter(
                ScoreActivity.this,
                R.layout.item_vote,
                mVoteItemList
        );
        ListView lv = findViewById(R.id.result_list_view);
        lv.setAdapter(adapter);
    }
}
