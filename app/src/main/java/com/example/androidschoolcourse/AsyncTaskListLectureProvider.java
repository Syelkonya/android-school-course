package com.example.androidschoolcourse;

import android.os.AsyncTask;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidschoolcourse.adapters.LecturesAdapter;
import com.example.androidschoolcourse.models.Lecture;


import java.util.Date;

public class AsyncTaskListLectureProvider extends AsyncTask<Void, Void, Void> {


    private LecturesAdapter mLecturesAdapter;

    @Override
    protected Void doInBackground(Void... voids) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mLecturesAdapter = new LecturesAdapter(getResources());
        mLecturesAdapter.setLectures(mLearningProgramProvider.provideLectures());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mLecturesAdapter);
        if (isFirstCreate) {
            Lecture nextLecture = mLearningProgramProvider.getLectureNextTo(new Date());
            int positionOfNextLecture = mLecturesAdapter.getPositionOf(nextLecture);
            if (positionOfNextLecture != -1) {
                recyclerView.scrollToPosition(positionOfNextLecture);
            }
        }
        return null;
    }
}
