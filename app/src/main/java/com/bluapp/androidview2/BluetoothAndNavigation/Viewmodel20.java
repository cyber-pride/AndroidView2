package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Viewmodel20 extends ViewModel {
    private MutableLiveData<String> itemname = new MutableLiveData<>();

    public void updateName(String name){
        itemname.setValue(name);
    }

    LiveData<String> observeItemname(){
        return itemname;
    }


}
