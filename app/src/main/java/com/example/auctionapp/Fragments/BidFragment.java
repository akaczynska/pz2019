package com.example.auctionapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.auctionapp.Adapters.ProductAdapter;
import com.example.auctionapp.Model.ProductInformation;
import com.example.auctionapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BidFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ArrayList<ProductInformation> listProducts;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bid, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listProducts = new ArrayList<>();

        readProducts();

        return view;
    }

   private void readProducts(){
       ProductInformation p1 = new ProductInformation();
       ProductInformation p2 = new ProductInformation();
       ProductInformation p3 = new ProductInformation();

        p1.setProductName("Chocolate");
        p1.setProductPrice("2.5$");
        p1.setProductTime("3 days left");
        p2.setProductName("Apple");
        p2.setProductPrice("2$");
        p2.setProductTime("4 days left");
        p3.setProductName("Milk");
        p3.setProductPrice("4$");
        p3.setProductTime("6 days left");

        listProducts.add(p1);
        listProducts.add(p2);
        listProducts.add(p3);

        productAdapter = new ProductAdapter(getContext(),listProducts);
        recyclerView.setAdapter(productAdapter);
    }
}
