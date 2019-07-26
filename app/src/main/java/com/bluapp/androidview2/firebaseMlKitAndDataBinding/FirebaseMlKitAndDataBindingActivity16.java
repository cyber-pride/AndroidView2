package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluapp.androidview2.BR;
import com.bluapp.androidview2.R;
import com.bluapp.androidview2.databinding.ActivityFirebaseMlKitAndDataBinding16Binding;
import com.bluapp.androidview2.databinding.ItemDatabind16RowBinding;

import java.util.ArrayList;
import java.util.List;

public class FirebaseMlKitAndDataBindingActivity16 extends AppCompatActivity {
    private ActivityFirebaseMlKitAndDataBinding16Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_ml_kit_and_data_binding16);
        populateData();
    }

    private void populateData(){
        List<CountryModel> countryModelList = new ArrayList<>();
        countryModelList.add(new CountryModel("Nigeria"));
        countryModelList.add(new CountryModel("India"));
        countryModelList.add(new CountryModel("Sweden"));
        countryModelList.add(new CountryModel("Usa"));
        countryModelList.add(new CountryModel("Ghana"));
        countryModelList.add(new CountryModel("South Africa"));

        CountryAdapter countryAdapter = new CountryAdapter(countryModelList, this);
        binding.setCountryAdapter(countryAdapter);
    }

    public class CountryModel{
        private String countryName;
        public CountryModel(String countryName){
            this.countryName = countryName;
        }

        public String getCountryName(){
           return countryName;
        }
    }

    public interface CustomClickListner{
        void cardClicked(CountryModel countryModel);
    }

    public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> implements CustomClickListner{
        private List<CountryModel> countryModelList;
        private Context context;

        public CountryAdapter(List<CountryModel> countryModelList, Context context){
            this.countryModelList = countryModelList;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            ItemDatabind16RowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_databind16_row, parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position){
            CountryModel countryModel = countryModelList.get(position);
            holder.bind(countryModel);
            holder.itemDatabind16RowBinding.setItemClickListner(this);
        }

        @Override
        public int getItemCount(){
            return countryModelList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder{
            public ItemDatabind16RowBinding itemDatabind16RowBinding;

            public ViewHolder(ItemDatabind16RowBinding itemDatabind16RowBinding){
                super(itemDatabind16RowBinding.getRoot());
                this.itemDatabind16RowBinding = itemDatabind16RowBinding;
            }

            public void bind(Object object){
                itemDatabind16RowBinding.setVariable(BR.model, object);
                itemDatabind16RowBinding.executePendingBindings();
            }
        }

        public void cardClicked(CountryModel countryModel){
            Toast.makeText(context, "Item Click "+countryModel.countryName, Toast.LENGTH_LONG).show();
        }
    }
}
