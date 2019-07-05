package com.example.businessmanager.ProductList.MVP;

import com.example.businessmanager.Model_common.UnitResponse;
import com.example.businessmanager.ProductList.model.Comapny_response;
import com.example.businessmanager.ProductList.model.Product_Response;
import com.example.businessmanager.ProductList.model.SpecResponse;
import com.example.businessmanager.ProductList.model.SubCat_response;

public class ProductListContract
{
    interface view
    {
        void showtaost(String s);

        void showdata(Product_Response body);

        void setList(UnitResponse body);

        void showCompanies(Comapny_response body);

        void showSubCategories(SubCat_response body);

        void showSpecs(SpecResponse body);

        void eraseSheet();
    }
    interface presenter
    {
        void getCompany();
        void getSubCategory(String company);
        void getList(String subcat,String company);
        void getSpecs(String category);
        void addCart(String mobile,String pid, String size, String cost, String unit, String nvm, String product_name);

        void getUnit();
    }
}
