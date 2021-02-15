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


public class OutdoorFragment extends Fragment {
    // Add RecyclerView member
    private List<Product> products;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_outdoor, container, false);
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
        products.add(new Product(1,"  Barrenwort", "Outdoor Plant",R.drawable.outdoor_1,"Barrenwort Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(199),"Calvin Reyold  \n91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(2,"Abutilon", "Outdoor Plant",R.drawable.outdoor_3,"Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(788),"Calvin Reyold  \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(3,"Calathea", "Outdoor Plant",R.drawable.outdoor_2," Calathea Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(38),"Calvin Reyold  \n91 Bellevue\n Dr.Gkebdale Heights, IL 60139"  ));
        products.add(new Product(4,"Castor Bean", "Outdoor Plant", R.drawable.outdoor55," Castor Bean Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(120),"Calvin Reyold  \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139"));
        products.add(new Product(5," Fuchsia", "Outdoor Plant", R.drawable.outdoor5," Fuchsia Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus." ,BigDecimal.valueOf(250),"Calvin Reyold \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(6,"Geraniums ", "Outdoor Plant", R.drawable.outdoor6," Geraniums Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(312),"Calvin Reyold \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139"));
        products.add(new Product(7,"Abutilon", "Outdoor Plant",R.drawable.outdoor88," AbutilonUllamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(500),"Calvin Reyold  \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(8,"Tiny Tree", "Outdoor Plant",R.drawable.outdooor," Tiny Tree Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(50),"Calvin Reyold  \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(9,"Bombo Tree", "Outdoor Plant",R.drawable.outdoor,"Bombo Tree Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(60),"Calvin Reyold  \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));

    }

    private void initializeAdapter(){
        ProductsRecycleViewAdapter adapter = new ProductsRecycleViewAdapter( products);
        recyclerView.setAdapter(adapter);

    }
}