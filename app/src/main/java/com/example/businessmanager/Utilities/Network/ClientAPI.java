package com.example.businessmanager.Utilities.Network;

import com.example.businessmanager.CheckOut.Model.PlaceOrderResponse;
import com.example.businessmanager.ClientRegistration.model.ResponseBody;
import com.example.businessmanager.History.Model.HistoryDetailResponse;
import com.example.businessmanager.History.Model.HistoryResponse;
import com.example.businessmanager.HomeActivity.model.ResponseClient;
import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.Cart.Model.CartResponse_CUD;
import com.example.businessmanager.Login.Model.LogInResponse;
import com.example.businessmanager.Model_common.UnitResponse;
import com.example.businessmanager.ProductList.model.Comapny_response;
import com.example.businessmanager.ProductList.model.Product_Response;
import com.example.businessmanager.ProductList.model.SpecResponse;
import com.example.businessmanager.ProductList.model.SubCat_response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ClientAPI
{
    @POST("Search/")
    @FormUrlEncoded
    Call<ResponseClient> search(
            @Field("RegType") String regtype,
            @Field("query") String query
    );

    @POST("admin_login/")
    @FormUrlEncoded
    Call<LogInResponse> login(
            @Field("mobile")String mobile,
            @Field("password")String password,
            @Field("fcm")String fcm
    );

    @POST("new_registrations/")
    @FormUrlEncoded
    Call<ResponseBody> createClient(
            @Field("Name")String name,
            @Field("Mobile")String mobile,
            @Field("Phone")String phone,
            @Field("Email")String email,
            @Field("Pan")String pan,
            @Field("BillTo")String billto,
            @Field("ShipTo")String shipto,
            @Field("GSTNo")String gst,
            @Field("BankName")String bankname,
            @Field("IFSCNo")String ifsc,
            @Field("ISCCode")String isc,
            @Field("BankPhone")String bankphone,
            @Field("AccountNo")String acccountno,
            @Field("MSMENo")String msme,
            @Field("TransportNo")String transport,
            @Field("RegType")String reg    );

    @POST("DataBaseRetrival/")
    @FormUrlEncoded
    Call<ResponseClient> getClientList(
            @Field("RegType") String regtype
    );

    @POST("ProductList/")
    @FormUrlEncoded
    Call<Product_Response> getProductList(
            @Field("UserType") String usertype,
            @Field("SubCatergory") String subcat,
            @Field("Company") String company
    );

    @POST("CompanyList/")
    @FormUrlEncoded
    Call<Comapny_response> getCompany(
            @Field("Mob") String mob
    );

    @POST("SubCateogry/")
    @FormUrlEncoded
    Call<SubCat_response> getSubCat(
            @Field("Company") String company
    );

    @POST("SpecsDetails/")
    @FormUrlEncoded
    Call<SpecResponse> getSpecs(
            @Field("ProductName") String prodname
    );

    @POST("AddCart/")
    @FormUrlEncoded
    Call<CartResponse_CUD> addProduct(
            @Field("MobileNumber") String mobile,
            @Field("PID") String productid,
            @Field("Size") String size,
            @Field("Unit") String unit,
            @Field("Cost") String cost
    );

    @POST("UpdateCart/")
    @FormUrlEncoded
    Call<CartResponse_CUD> updateProduct(
            @Field("MobileNumber") String mobile,
            @Field("PID") String productid,
            @Field("Size") String size,
            @Field("Unit") String unit,
            @Field("Cost") String cost
    );
    @POST("DeleteCart/")
    @FormUrlEncoded
    Call<CartResponse_CUD> deleteProduct(
            @Field("ID") String productid
    );

    @POST("DeleteAllCart/")
    @FormUrlEncoded
    Call<CartResponse_CUD> deleteallProduct(
            @Field("MobileNumber") String mobile,
            @Field("Company") String company
    );

    @POST("CartDetails/")
    @FormUrlEncoded
    Call<CartResponse> getCart(
            @Field("MobileNumber") String mobile,
            @Field("Company") String company
    );

    @POST("GetUnitData/")
    @FormUrlEncoded
    Call<UnitResponse> getUnits(
            @Field("mob") String mob
    );

    @POST("PlaceOrder/")
    @FormUrlEncoded
    Call<PlaceOrderResponse> placeorder(
            @Field("Contact_Type") String contacttype,
            @Field("Name") String name,
            @Field("MobileNumber") String mobile ,
            @Field("Terms") String terms,
            @Field("Comment") String comment,
            @Field("Company") String company,
            @Field("access_token") String token
    );

    @POST("GetOrderHistory/")
    @FormUrlEncoded
    Call<HistoryResponse> getOrderHistory(
            @Field("MobileNumber") String mob,
            @Field("Contact_Type") String type,
            @Field("Company") String company
    );

    @POST("GetOrderDetailsHistory/")
    @FormUrlEncoded
    Call<HistoryDetailResponse> getOrderDetailsHistory(
            @Field("OrderId") String id
    );
}
