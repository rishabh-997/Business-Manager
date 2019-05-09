package com.example.businessmanager;

import java.util.Comparator;

public class Contact_model
{
    String name,contact;

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public Contact_model(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public static Comparator<Contact_model> NameCompare=new Comparator<Contact_model>() {
        @Override
        public int compare(Contact_model o1, Contact_model o2) {
            String name1=o1.getName().toUpperCase();
            String name2=o2.getName().toUpperCase();
            return  name1.compareTo(name2);
        }
    };
}
