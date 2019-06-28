package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.bluapp.androidview2.R;
import com.google.gson.annotations.SerializedName;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class WorkManager13 extends Worker {
    public WorkManager13(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String imagePath = getInputData().getString("imagePath");
        //Defining retrofit api service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.48/test/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileupload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        ApiService service = retrofit.create(ApiService.class);
        Call<PostResponse> call = service.postData(fileupload, filename);
        //calling the api
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if(response.isSuccessful()){
                    showNotification("Inducesmile", "Image uploaded successfully");
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Result.failure();
            }
        });
        return Result.success();
    }

    private void showNotification(String title, String task){
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("inducesmile", "inducesmile", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(),"inducesmile")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);
        notificationManager.notify(1, notification.build());
    }

    private interface ApiService {
        @Multipart
        @POST("index.php")
        Call<PostResponse> postData(
                @Part MultipartBody.Part file,
                @Part("name") RequestBody name);
    }

    private class PostResponse{
        @SerializedName("success")
        private String success;

        public void setSuccess(String success){
            this.success = success;
        }
        public String getSuccess(){
            return success;
        }
    }

}
