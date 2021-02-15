package com.example.plantsecommerceapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class MyWishlistRecycleViewAdapter extends BaseAdapter {
    private static final String TAG = "MyWishlistRecycleViewAdapter";
  Product product ;
    Button btn_AddToCart  ;
     ImageView iv_deleteItem;
    private List<CartItem> cartItems = Collections.emptyList();

    private final Context context;

    public MyWishlistRecycleViewAdapter(Context context) {
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
        TextView tvName;
        TextView tvUnitPrice;
         ImageView ivProductImage;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.mywishlist_list, parent, false);
            tvName = (TextView) convertView.findViewById(R.id.tvName);
            tvUnitPrice = (TextView) convertView.findViewById(R.id.tvUnitPrice);
            //    tvQuantity = (TextView) convertView.findViewById(R.id.tv_productQuantity);
            //         tvPrice = (TextView) convertView.findViewById(R.id.tv_TotalPrice);
            ivProductImage = (ImageView) convertView.findViewById(R.id.ivProductImage);
            btn_AddToCart = (Button) convertView.findViewById(R.id.btn_AddToCart);
            iv_deleteItem = (ImageView) convertView.findViewById(R.id.iv_deleteItem);
            convertView.setTag(new ViewHolder( tvName, tvUnitPrice, ivProductImage));
        }
        else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvCartItemName;
            tvUnitPrice = viewHolder.tvCartItemUnitPrice;
            //       tvQuantity = viewHolder.tvCartItemQuantity;
            //         tvPrice = viewHolder.tvCartItemPrice;
            ivProductImage=viewHolder.ivProductImage;
         }

        final WishList wishlist = WishListHelper.getCart();
        final CartItem cartItem = getItem(position);
        tvName.setText(cartItem.getProduct().getName());
//        tvUnitPrice.setText(Constant.CURRENCY+  Integer.parseInt(String.valueOf(cartItem.getProduct().getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)))  * cartItem.getQuantity());

        tvUnitPrice.setText(Constant.CURRENCY+  String.valueOf(cartItem.getProduct().getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
        //   tvQuantity.setText(String.valueOf(cartItem.getQuantity()));
        //tvPrice.setText(Constant.CURRENCY+ String.valueOf(cart.getCost(cartItem.getProduct()).setScale(2, BigDecimal.ROUND_HALF_UP)));
        ivProductImage.setImageResource(cartItem.getProduct().getImageName());
//        btn_AddToCart.setOnClickListener (new View.OnClickListener () {
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onClick( View view  ) {
//                Intent intent = new Intent(view.getContext(), ProductActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("product", product);
//                Log.d(TAG, "View product: " + product.getName());
//                intent.putExtras(bundle);
//                view.getContext().startActivity(intent);
//            }
//        });
//        iv_deleteItem.setOnClickListener (new View.OnClickListener () {
//             @Override
//            public void onClick( View view   ) {
//                 final WishList cart = WishListHelper.getCart();
//
//                 List<CartItem> cartItems = getCartItems(cart);
//                 cart.remove(cartItems.get(position-1).getProduct());
//                 cartItems.remove(position-1);
//                 CartItemAdapter.updateCartItems(cartItems);
//                 cartItemAdapter.notifyDataSetChanged();
//                 tvTotalPrice.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
//
//
//        }
//        });




        return convertView;
    }

    private static class ViewHolder {
        public final TextView tvCartItemName;
        public final TextView tvCartItemUnitPrice;
        //      public final TextView tvCartItemQuantity;
        //      public final TextView tvCartItemPrice;
        public  final  ImageView  ivProductImage;

        public ViewHolder(TextView tvCartItemName, TextView tvCartItemUnitPrice, ImageView ivProductImage) {
            this.tvCartItemName = tvCartItemName;
            this.tvCartItemUnitPrice = tvCartItemUnitPrice;
//            this.tvCartItemQuantity = tvCartItemQuantity;
            //       this.tvCartItemPrice = tvCartItemPrice;
            this.ivProductImage=ivProductImage;
        }
    }
}



