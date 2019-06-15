package com.example.businessmanager.ProductList.MVP;

import com.example.businessmanager.Model_common.UnitResponse;
import com.example.businessmanager.ProductList.model.Comapny_response;
import com.example.businessmanager.ProductList.model.Product_Response;
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
    }
    interface presenter
    {
        void getCompany();
        void getSubCategory(String company);
        void getList(String subcat);
        void addCart(String mobile,String pid, String size, String cost, String unit);

        void getUnit();
    }
}
