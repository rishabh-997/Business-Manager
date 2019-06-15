package com.example.businessmanager.ProductList.model;

import com.google.gson.annotations.SerializedName;

public class SpecList
{
    @SerializedName("Solid")
    String solid;
    @SerializedName("Solvent")
    String solvent;
    @SerializedName("TypeOfOil")
    String typeofoil;
    @SerializedName("Type_Of_polyol")
    String typeofpolyol;
    @SerializedName("Oil")
    String oil;
    @SerializedName("AcidValue")
    String acid;
    @SerializedName("Viscosity")
    String visc;
    @SerializedName("ColorGardner")
    String color;

    public String getSolid() {
        return solid;
    }

    public String getSolvent() {
        return solvent;
    }

    public String getTypeofoil() {
        return typeofoil;
    }

    public String getTypeofpolyol() {
        return typeofpolyol;
    }

    public String getOil() {
        return oil;
    }

    public String getAcid() {
        return acid;
    }

    public String getVisc() {
        return visc;
    }

    public String getColor() {
        return color;
    }
}
