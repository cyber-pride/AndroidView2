package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class LiveDataHelper{
    private LiveDataHelper(){}
    private static LiveDataHelper liveDataHelper;
    private MediatorLiveData<Integer> downloadPercent = new MediatorLiveData<>();

    synchronized public static LiveDataHelper getInstance(){
        if(liveDataHelper == null)
            liveDataHelper = new LiveDataHelper();
        return liveDataHelper;
    }

    public void updateDownloadPer(int percentage){
        downloadPercent.postValue(percentage);
    }

    LiveData<Integer> observePercentage(){
        return downloadPercent;
    }

}