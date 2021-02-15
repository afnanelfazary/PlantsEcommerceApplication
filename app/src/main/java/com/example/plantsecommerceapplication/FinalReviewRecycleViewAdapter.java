package com.example.plantsecommerceapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class FinalReviewRecycleViewAdapter extends BaseAdapter {
    private static final String TAG = "CartItemAdapter";

    private List<CartItem> cartItems = Collections.emptyList();

    private final Context context;

    public FinalReviewRecycleViewAdapter(Context context) {
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
        //TextView tvQuantity;
      //  TextView tvPrice;
        ImageView ivProductImage;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.finalreview_list, parent, false);
            tvName = (TextView) convertView.findViewById(R.id.person_name);
            tvUnitPrice = (TextView) convertView.findViewById(R.id.tv_productPrice);
        //    tvQuantity = (TextView) convertView.findViewById(R.id.tv_productQuantity);
   //         tvPrice = (TextView) convertView.findViewById(R.id.tv_TotalPrice);
            ivProductImage = (ImageView) convertView.findViewById(R.id.person_photo);

            convertView.setTag(new ViewHolder( tvName, tvUnitPrice, ivProductImage));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvCartItemName;
            tvUnitPrice = viewHolder.tvCartItemUnitPrice;
     //       tvQuantity = viewHolder.tvCartItemQuantity;
      //         tvPrice = viewHolder.tvCartItemPrice;
            ivProductImage=viewHolder.ivProductImage;
        }

        final Cart cart = CartHelper.getCart();
        final CartItem cartItem = getItem(position);
        tvName.setText(cartItem.getProduct().getName());
//        tvUnitPrice.setText(Constant.CURRENCY+  Integer.parseInt(String.valueOf(cartItem.getProduct().getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)))  * cartItem.getQuantity());

        tvUnitPrice.setText(Constant.CURRENCY+  String.valueOf(cartItem.getProduct().getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
     //   tvQuantity.setText(String.valueOf(cartItem.getQuantity()));
        //tvPrice.setText(Constant.CURRENCY+ String.valueOf(cart.getCost(cartItem.getProduct()).setScale(2, BigDecimal.ROUND_HALF_UP)));
        ivProductImage.setImageResource(cartItem.getProduct().getImageName());
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