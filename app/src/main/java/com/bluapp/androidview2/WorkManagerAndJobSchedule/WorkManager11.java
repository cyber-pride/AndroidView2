package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class WorkManager11 extends Worker {
    private DataDatabase dataDatabase;

    public WorkManager11(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        dataDatabase = DataDatabase.getInstance(getApplicationContext());
    }

    @NonNull
    @Override
    public Result doWork() {
        String title = getInputData().getString("title");
        String message = getInputData().getString("message");
        String data = "Title: "+title+" Message: "+message;
        saveData(data);
        return Result.success();
    }

    private void saveData(final String data){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                WorkData11 workdata = new WorkData11(data);
                dataDatabase.dataDAO().insert(workdata);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

}
