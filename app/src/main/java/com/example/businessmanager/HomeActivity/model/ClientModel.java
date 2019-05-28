package com.example.businessmanager.HomeActivity.model;

import java.util.Comparator;

public class ClientModel
{
    private String Name, Mobile,Phone,Email,Pan,BillTo,ShipTo,GSTNo,BankName,IFSCNo,ISCCode,BankPhone,AccountNo,MSMENo,TransportNo,RegType;

    public ClientModel(String name, String mobile, String phone, String email, String pan, String billTo, String shipTo, String GSTNo, String bankName, String IFSCNo, String ISCCode, String bankPhone, String accountNo, String MSMENo, String transportNo, String regType) {
        Name = name;
        Mobile = mobile;
        Phone = phone;
        Email = email;
        Pan = pan;
        BillTo = billTo;
        ShipTo = shipTo;
        this.GSTNo = GSTNo;
        BankName = bankName;
        this.IFSCNo = IFSCNo;
        this.ISCCode = ISCCode;
        BankPhone = bankPhone;
        AccountNo = accountNo;
        this.MSMENo = MSMENo;
        TransportNo = transportNo;
        RegType = regType;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPan(String pan) {
        Pan = pan;
    }

    public void setBillTo(String billTo) {
        BillTo = billTo;
    }

    public void setShipTo(String shipTo) {
        ShipTo = shipTo;
    }

    public void setGSTNo(String GSTNo) {
        this.GSTNo = GSTNo;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public void setIFSCNo(String IFSCNo) {
        this.IFSCNo = IFSCNo;
    }

    public void setISCCode(String ISCCode) {
        this.ISCCode = ISCCode;
    }

    public void setBankPhone(String bankPhone) {
        BankPhone = bankPhone;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public void setMSMENo(String MSMENo) {
        this.MSMENo = MSMENo;
    }

    public void setTransportNo(String transportNo) {
        TransportNo = transportNo;
    }

    public void setRegType(String regType) {
        RegType = regType;
    }

    public String getName() {
        return Name;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getPan() {
        return Pan;
    }

    public String getBillTo() {
        return BillTo;
    }

    public String getShipTo() {
        return ShipTo;
    }

    public String getGSTNo() {
        return GSTNo;
    }

    public String getBankName() {
        return BankName;
    }

    public String getIFSCNo() {
        return IFSCNo;
    }

    public String getISCCode() {
        return ISCCode;
    }

    public String getBankPhone() {
        return BankPhone;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public String getMSMENo() {
        return MSMENo;
    }

    public String getTransportNo() {
        return TransportNo;
    }

    public String getRegType() {
        return RegType;
    }

    public static Comparator<ClientModel> NameCompare=new Comparator<ClientModel>() {
        @Override
        public int compare(ClientModel o1, ClientModel o2) {
            String name1=o1.getName().toUpperCase();
            String name2=o2.getName().toUpperCase();
            return  name1.compareTo(name2);
        }
    };

}
