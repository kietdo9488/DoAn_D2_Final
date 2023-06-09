package com.example.banhanggiadung.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhanggiadung.R;
import com.example.banhanggiadung.adapter.CategoryAdapter;
import com.example.banhanggiadung.adapter.ProductAdapter;
import com.example.banhanggiadung.model.Category;
import com.example.banhanggiadung.model.Product;

import java.util.ArrayList;

public class ProductListFragment extends AbstractFragment {
    private ProductAdapter productAdapter;

    private CategoryAdapter categoryAdapter;

    private int selectedRow = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.product_list_layout, container, false);



        ////
        //
        //Product List Adapter
        ArrayList<Product> arrProduct = new ArrayList<>();
        arrProduct.add(new Product(1, "Tu Lanh", 1000, R.mipmap.tu_lanh, "abc", 1));
        arrProduct.add(new Product(2, "May Giat", 1000, R.mipmap.may_giat, "abc", 2));
        arrProduct.add(new Product(3, "Cay lau nha 1", 1000, R.mipmap.cay_lau_nha, "abc", 3));
        arrProduct.add(new Product(4, "Cay lau nha 2", 1000, R.mipmap.cay_lau_nha, "abc", 4));

        ArrayList<Category> arrCategory = new ArrayList<>();
        arrCategory.add(0, new Category(1, "Tu lanh", R.mipmap.tu_lanh));
        arrCategory.add(1, new Category(2, "May giat", R.mipmap.may_giat));
        arrCategory.add(2, new Category(3, "Gia dung", R.mipmap.cay_lau_nha));


        RecyclerView recyclerViewProductList = fragmentLayout.findViewById(R.id.list_product);
        RecyclerView recyclerViewCategoryList = fragmentLayout.findViewById(R.id.list_category);

        //Create Layout manager for category
        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(fragmentLayout.getContext());
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewCategoryList.setLayoutManager(layoutManagerCategory);
        GridLayoutManager gridLayoutManagerCAtegory = new GridLayoutManager(fragmentLayout.getContext(), 5);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManagerCAtegory);
        categoryAdapter = new CategoryAdapter((Activity) fragmentLayout.getContext(), R.layout.item_categories, arrCategory);
        recyclerViewCategoryList.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        categoryAdapter.setOnrecycleViewItemClickListener(new CategoryAdapter.OnrecycleViewItemClickListener() {
            @Override
            public void onItemClickListener(int position, View Card) {
                if (selectedRow == -1) {
                    selectedRow = position;
                    setPersonToLayout(listMembers.get(position));

                    LinearLayout bgrItem = CardView.findViewById(R.id.bgrListItem);
                    // Save the original color of back ground
                    backColor = bgrItem.getSolidColor();
                    bgrItem.setBackgroundColor(getResources().getColor(R.color.selectedRow, getTheme()));
                    previousItemGround = bgrItem;
                }
            }
        });

        //Create Layout manager for product
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(fragmentLayout.getContext());
        layoutManagerProduct.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewProductList.setLayoutManager(layoutManagerProduct);
        GridLayoutManager gridLayoutManagerProduct = new GridLayoutManager(fragmentLayout.getContext(), 2);
        recyclerViewProductList.setLayoutManager(gridLayoutManagerProduct);
        productAdapter = new ProductAdapter((Activity) fragmentLayout.getContext(), R.layout.item_products, arrProduct);
        recyclerViewProductList.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();

        return fragmentLayout;

    }

    private void setCategoryToLayout(Category category) {
        edtName.setText(person.getName());
        if (person.getDegree().equalsIgnoreCase(DAIHOC)) {
            radUniversity.setChecked(true);
        } else if (person.getDegree().equalsIgnoreCase(CAODANG)) {
            radCollege.setChecked(true);
        } else if (person.getDegree().equalsIgnoreCase(TRUNGCAP)) {
            radTraing.setChecked(true);
        }

        StringTokenizer tokenizer = new StringTokenizer(person.getHobbies());
        String other = "";
        if (person.getHobbies().contains(chkRead.getText().toString())) {
            chkRead.setChecked(true);
            tokenizer.nextToken(";");
        }
        if (person.getHobbies().contains(chkTravel.getText().toString())) {
            chkTravel.setChecked(true);
            tokenizer.nextToken(";");
        }
        if (tokenizer.hasMoreTokens()) {
            other = tokenizer.nextToken(";");
            edtHoppies.setText(other);
        }

    }
}
