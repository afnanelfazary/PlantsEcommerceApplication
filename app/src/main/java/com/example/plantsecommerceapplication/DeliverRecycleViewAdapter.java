package com.example.plantsecommerceapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DeliverRecycleViewAdapter extends RecyclerView.Adapter<DeliverRecycleViewAdapter.ProductViewHolder> {



    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout cv;
        TextView tv_Address;
        ImageView iv_AddressICon;
        String addNewAddress;
        EditText et_addNewAddress;
        ProductViewHolder(View itemView) {
            super(itemView);
            cv = (RelativeLayout) itemView.findViewById(R.id.cv);
            tv_Address = (TextView) itemView.findViewById(R.id.tv_Address);
            iv_AddressICon = (ImageView) itemView.findViewById(R.id.iv_AddressICon);
//              et_addNewAddress = (EditText) itemView.findViewById(R.id.et_addNewAddress);
//              addNewAddress = et_addNewAddress.getText().toString();

        }


    }

    List<Product>products;

    DeliverRecycleViewAdapter(List<Product> products){
        this.products = products;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DeliverRecycleViewAdapter.ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.deliver_list, viewGroup, false);
        DeliverRecycleViewAdapter.ProductViewHolder pvh = new DeliverRecycleViewAdapter.ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DeliverRecycleViewAdapter.ProductViewHolder personViewHolder, int i) {
 //         personViewHolder.tv_Address.getText();
         personViewHolder.tv_Address.setText(products.get(i).getAddress());
       // String ss = "jhjug";
     //   personViewHolder.tv_Address.getText(products.get(i).setAddress(ss));

//        personViewHolder.tv_Address.setText(products.get(i).getProduct().setAddress(add));
  //      personViewHolder.iv_AddressICon.setImageResource(products.get(i).getAddressIcon());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}






