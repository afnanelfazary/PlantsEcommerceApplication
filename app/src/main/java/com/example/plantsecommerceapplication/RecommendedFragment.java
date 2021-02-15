package com.example.plantsecommerceapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class RecommendedFragment extends Fragment {
    // Add RecyclerView member
    private List<Product> products;

    private RecyclerView recyclerView;
     public RecommendedFragment() {
        // Required empty public constructor
    }

        @Override
      //  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
        public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState)

        {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_recommended, container, false);
        //
        // Add the following lines to create RecyclerView
         recyclerView = view.findViewById(R.id.rv);
         //change font
        //   TextView plantName=(TextView) view.findViewById(R.id.person_name);
          //  Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LexendDeca-Regular.ttf");
           // plantName.setTypeface(font);


            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(llm);
            recyclerView.setHasFixedSize(true);
            initializeData();
            initializeAdapter();
            return view;


        }
    private void initializeData(){
        products = new ArrayList<>();
        products.add(new Product(2,"Abutilon", "Outdoor Plant",R.drawable.outdoor_3,"Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(788),"Calvin Reyold  \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(11,"Vibrant Lily", "Indoor Plant",R.drawable.indoor_1," Vibrant Lily Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus. ",BigDecimal.valueOf(500),"Calvin Reyold \n91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(4,"Castor Bean", "Outdoor Plant", R.drawable.outdoor55," Castor Bean Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(120),"Calvin Reyold  \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139"));
        products.add(new Product(13,"Dracaena", "Indoor Plant", R.drawable.indoor_3," Dracaena Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.", BigDecimal.valueOf(199),"Calvin Reyold \n99 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(7,"Abutilon", "Outdoor Plant",R.drawable.outdoor88," AbutilonUllamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(500),"Calvin Reyold  \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(14,"The Sill ", "Indoor Plant", R.drawable.indoor10," The Sill Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(200),"Calvin Reyold \n 98 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));
        products.add(new Product(6,"Geraniums ", "Outdoor Plant", R.drawable.outdoor6," Geraniums Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(312),"Calvin Reyold \n 91 Bellevue\n Dr.Gkebdale Heights, IL 60139"));
        products.add(new Product(17,"  Red  Plant", "Indoor Plant", R.drawable.indoorlast," Bird’s Nest Fern Ullamcorper cras primis dignissim maecenas consectetur nam condimentum eros a justo a mollis curae justo pharetra nisl scelerisque a aliquet justo ipsum a ante lobortis sodales varius nibh scelerisque. Mus est nec condimentum hac scelerisque parturient sociosqu a lobortis a porttitor risus dis parturient aliquam vel suscipit vel mus.",BigDecimal.valueOf(50),"Calvin Reyold \n92 Bellevue\n Dr.Gkebdale Heights, IL 60139" ));

    }

    private void initializeAdapter(){
        ProductsRecycleViewAdapter adapter = new ProductsRecycleViewAdapter(products);
        recyclerView.setAdapter(adapter);
    }

}



