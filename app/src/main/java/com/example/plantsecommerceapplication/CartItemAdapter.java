package com.example.plantsecommerceapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CartItemAdapter extends BaseAdapter {
    private static final String TAG = "CartItemAdapter";
    ImageView iv_deleteItem  ;
    boolean isValid = true;

    private List<CartItem> cartItems = Collections.emptyList();
     private final Context context;

    public CartItemAdapter(Context context) {
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
        TextView tvQuantity;
        TextView tvPrice;
        ImageView ivProductImage;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.mycart_list, parent, false);

            tvName = (TextView) convertView.findViewById(R.id.person_name);
            tvUnitPrice = (TextView) convertView.findViewById(R.id.tv_productPrice);
            tvQuantity = (TextView) convertView.findViewById(R.id.tv_productQuantity);
            //tvPrice = (TextView) convertView.findViewById(R.id.tv_TotalPrice);
            ivProductImage = (ImageView) convertView.findViewById(R.id.person_photo);
            iv_deleteItem = (ImageView)  convertView.findViewById(R.id.iv_deleteItem);
            convertView.setTag(new ViewHolder(tvName, tvUnitPrice, tvQuantity, ivProductImage));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvCartItemName;
            tvUnitPrice = viewHolder.tvCartItemUnitPrice;
            tvQuantity = viewHolder.tvCartItemQuantity;
         //   tvPrice = viewHolder.tvCartItemPrice;
            ivProductImage=viewHolder.ivProductImage;
        }

        final Cart cart = CartHelper.getCart();
        final CartItem cartItem = getItem(position);
        tvName.setText(cartItem.getProduct().getName());
      tvUnitPrice.setText(Constant.CURRENCY+ String.valueOf(cartItem.getProduct().getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
        tvQuantity.setText(String.valueOf(cartItem.getQuantity()));
      //tvPrice.setText(Constant.CURRENCY+ String.valueOf(cart.getCost(cartItem.getProduct()).setScale(2, BigDecimal.ROUND_HALF_UP)));
         ivProductImage.setImageResource(cartItem.getProduct().getImageName());

//        iv_deleteItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(context)
//                        .setTitle(context.getResources().getString(R.string.delete_item))
//                        .setMessage(context.getResources().getString(R.string.delete_item_message))
//                        .setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                List<CartItem> cartItems = getCartItems(cart);
//                                cart.remove(cartItems.get(position - 1).getProduct());
//                                cartItems.remove(position - 1);
//                                updateCartItems(cartItems);
//                                notifyDataSetChanged();
//                                ShoppingCartActivity.tvTotalPrice.setText(Constant.CURRENCY + String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
//                            }
//                        })
//                        .setNegativeButton(context.getResources().getString(R.string.no), null)
//                        .show();
//                    isValid =  false;
//            }
//        });





        return convertView;

    }

    private static class ViewHolder {
        public final TextView tvCartItemName;
        public final TextView tvCartItemUnitPrice;
        public final TextView tvCartItemQuantity;
    //    public final TextView tvCartItemPrice;
        public  final  ImageView  ivProductImage;

        public ViewHolder(TextView tvCartItemName, TextView tvCartItemUnitPrice, TextView tvCartItemQuantity, ImageView ivProductImage) {
            this.tvCartItemName = tvCartItemName;
            this.tvCartItemUnitPrice = tvCartItemUnitPrice;
            this.tvCartItemQuantity = tvCartItemQuantity;
     //       this.tvCartItemPrice = tvCartItemPrice;
            this.ivProductImage=ivProductImage;
        }
    }
    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

        for (Map.Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }

}
