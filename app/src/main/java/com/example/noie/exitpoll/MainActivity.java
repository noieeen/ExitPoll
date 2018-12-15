package com.example.noie.exitpoll;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.noie.exitpoll.db.DatabaseHelper;
import com.example.noie.exitpoll.model.VoteItem;
import com.example.noie.exitpoll.db.DatabaseHelper;
import com.example.noie.exitpoll.model.VoteItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.noie.exitpoll.db.DatabaseHelper.COL_ID;
import static com.example.noie.exitpoll.db.DatabaseHelper.COL_IMAGE;
import static com.example.noie.exitpoll.db.DatabaseHelper.COL_NAME;
import static com.example.noie.exitpoll.db.DatabaseHelper.COL_SCORE;
import static com.example.noie.exitpoll.db.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<VoteItem> mVoteItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        Button vote_one = findViewById(R.id.chose_one);
        Button vote_two = findViewById(R.id.chose_two);
        Button vote_three = findViewById(R.id.chose_three);
        Button vote_no = findViewById(R.id.chose_no);
        Button score_view = findViewById(R.id.score_view);

        loadPhoneData();

        vote_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                VoteItem item = mVoteItemList.get(0);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "no");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );
                loadPhoneData();


            }
        });

        vote_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                VoteItem item = mVoteItemList.get(1);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "1");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"one.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );
                loadPhoneData();

            }
        });

        vote_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                VoteItem item = mVoteItemList.get(2);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "2");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );
                loadPhoneData();

            }
        });

        vote_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                VoteItem item = mVoteItemList.get(3);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "3");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );
                loadPhoneData();

            }
        });



        score_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });



        //todo


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

}


