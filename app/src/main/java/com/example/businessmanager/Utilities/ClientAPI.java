package com.example.businessmanager.Utilities;

import com.example.businessmanager.ClientRegistration.model.ResponseBody;
import com.example.businessmanager.HomeActivity.model.ResponseClient;
import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.Cart.Model.CartResponse_CUD;
import com.example.businessmanager.ProductList.model.Product_Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ClientAPI
{
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
            @Field("UserType") String usertype
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
            @Field("MobileNumber") String mobile,
            @Field("PID") String productid
    );

    @POST("DeleteAllCart/")
    @FormUrlEncoded
    Call<CartResponse_CUD> deleteallProduct(
            @Field("MobileNumber") String mobile
    );

    @POST("CartDetails/")
    @FormUrlEncoded
    Call<CartResponse> getCart(
            @Field("MobileNumber") String mobile
    );

}
