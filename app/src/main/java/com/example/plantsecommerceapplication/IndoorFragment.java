package com.example.plantsecommerceapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IndoorFragment extends Fragment {
    // Add RecyclerView member
    private List<Product> products;

    private RecyclerView recyclerView;

    public IndoorFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_indoor, container, false);
        //
        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        initializeData();
        initializeAdapter();
        return view;
    }
    private void initializeData(){
        products = new ArrayList<>();
        products.add(new Product(11,"Vibrant Lily", "Indoor Plant",R.drawable.indoor_1," Vibrant Lily Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus. ",BigDecimal.valueOf(500),"Calvin Reyold \n91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(13,"Dracaena", "Indoor Plant", R.drawable.indoor_3," Dracaena Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.", BigDecimal.valueOf(199),"Calvin Reyold \n99 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(14,"The Sill ", "Indoor Plant", R.drawable.indoor10," The Sill Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(200),"Calvin Reyold \n 98 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(17,"  Red  Plant", "Indoor Plant", R.drawable.indoorlast," Bird’s Nest Fern Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(50),"Calvin Reyold \n92 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(15,"Plant Nest", "Indoor Plant", R.drawable.indoor8," Plant Nest Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(25),"Calvin Reyold  \n97 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(16,"Rubber Tree", "Indoor Plant",R.drawable.indoor4," Rubber TreeUllamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(155),"Calvin Reyold \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139"));
        products.add(new Product(18,"Bird’s Nest Fern", "Indoor Plant", R.drawable.indoor77," Bird’s Nest Fern Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(50),"Calvin Reyold \n92 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(12,"Bonsai", "Indoor Plant", R.drawable.indoor6,"Bonsai Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(100),"Calvin Reyold \n90 Bellevue\n Dr.Gkebdale Heights, IL 60139"));


    }

    private void initializeAdapter(){
        ProductsRecycleViewAdapter adapter = new ProductsRecycleViewAdapter(products);
        recyclerView.setAdapter(adapter);
    }}