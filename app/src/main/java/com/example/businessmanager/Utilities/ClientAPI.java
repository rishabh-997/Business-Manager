package com.example.businessmanager.Utilities;

import com.example.businessmanager.ClientRegistration.model.ResponseBody;
import com.example.businessmanager.HomeActivity.model.ResponseClient;

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
}
