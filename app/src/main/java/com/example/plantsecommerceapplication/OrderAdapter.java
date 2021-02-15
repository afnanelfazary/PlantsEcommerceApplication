package com.example.plantsecommerceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

class OrderAdapter extends BaseAdapter {
    private static final String TAG = "OrderAdapter";
    Product product ;
    Button btn_AddToCart  ;
    ImageView iv_deleteItem;
    private List<CartItem> cartItems = Collections.emptyList();

    private final Context context;

    public OrderAdapter(Context context) {
        this.context = context;
    }

    public void updateCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public CartItem getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
          ImageView ivProductImage;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.orders_list, parent, false);

            ivProductImage = (ImageView) convertView.findViewById(R.id.ivProductImage);
             convertView.setTag(new OrderAdapter.ViewHolder(  ivProductImage));
        }
        else {
            OrderAdapter.ViewHolder viewHolder = (OrderAdapter.ViewHolder) convertView.getTag();

            ivProductImage=viewHolder.ivProductImage;
        }

        final Orders orders = OrderHelper.getCart();
        final CartItem cartItem = getItem(position);
        ivProductImage.setImageResource(cartItem.getProduct().getImageName());





        return convertView;
    }

    private static class ViewHolder {
        public  final  ImageView  ivProductImage;

        public ViewHolder(ImageView ivProductImage) {

            this.ivProductImage=ivProductImage;
        }
    }
}




