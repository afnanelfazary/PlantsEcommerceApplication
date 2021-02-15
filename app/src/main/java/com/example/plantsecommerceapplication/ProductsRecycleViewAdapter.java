package com.example.plantsecommerceapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
public class ProductsRecycleViewAdapter extends RecyclerView.Adapter<ProductsRecycleViewAdapter.ProductViewHolder> {
    public static final String CURRENCY = "$";
    private static final String TAG = "ProductsRecycleViewAdapter";
    private Context context;

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        Button btn_AddToCart;
        TextView ProductPrice;
        ImageView iv_favourite_star;
        Spinner spQuantity;

        ProductViewHolder(View itemView) {
            super(itemView);
            cv = (RelativeLayout) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.tvProductName);
            personAge = (TextView) itemView.findViewById(R.id.TV_Plant_Type);
            personPhoto = (ImageView) itemView.findViewById(R.id.ivProductImage);
            ProductPrice=(TextView) itemView.findViewById(R.id.TV_Plant_Price);
            iv_favourite_star = (ImageView) itemView.findViewById(R.id.iv_favourite_star);
            btn_AddToCart=(Button)itemView.findViewById(R.id.btn_AddToCart);


        }


    }

    List<Product> products;

    ProductsRecycleViewAdapter(List<Product> products){
        this.products = products;

    }
    public ProductsRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_items, viewGroup, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(ProductViewHolder personViewHolder, int position) {
        final Product product = getItem(position);

        personViewHolder.personName.setText(product.getName());
        personViewHolder.personPhoto.setImageResource(product.getImageName());
        personViewHolder.ProductPrice.setText(CURRENCY+String.valueOf(product.getPrice()));
        personViewHolder.personAge.setText(product.getProductType());
        personViewHolder.iv_favourite_star.setOnClickListener (new View.OnClickListener () {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick( View view  ) {
                personViewHolder.iv_favourite_star.setImageResource(R.drawable.ic_baseline_star_rate_24);
                WishList wishList = WishListHelper.getCart();
                Log.d(TAG, "Adding product: " + product.getName());
                 wishList.add(product,1);
                Intent intent = new Intent(view.getContext(), MyWishlistActivity.class);

          //     view.getContext().startActivity(intent);

             }
        });


         personViewHolder.btn_AddToCart.setOnClickListener (new View.OnClickListener () {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick( View view  ) {

                Intent intent = new Intent(view.getContext(), ProductActivity.class);
               Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);
               Log.d(TAG, "View product: " + product.getName());

                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}


