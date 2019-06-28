package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;

import java.util.concurrent.TimeUnit;

public class WorkManagerAndJobScheduleActivity13 extends AppCompatActivity {
    private Button pickpicture;
    private Button upload;
    private String imagePathUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule13);
        pickpicture = (Button) findViewById(R.id.pickpicture);
        upload = (Button) findViewById(R.id.upload);
        pickpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/png image/jpeg image/gif");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void startWork(){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManager13.class)
                .setInputData(createInputData(imagePathUri))
                .setInitialDelay(2, TimeUnit.SECONDS).build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
    }

    //result is been return here
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case(1): {
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    Uri uri = data.getData();
                    try {
                        String pathToStoredMedia = getRealPathFromURIPath(uri, WorkManagerAndJobScheduleActivity13.this);
                        imagePathUri = pathToStoredMedia;
                        Toast.makeText(WorkManagerAndJobScheduleActivity13.this, "Image pick successfully", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    private Data createInputData(String imagePath){
        Data data = new Data.Builder()
                .putString("imagePath", imagePath)
                .build();
        return data;
    }

    //geting real path from gallery
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

}
