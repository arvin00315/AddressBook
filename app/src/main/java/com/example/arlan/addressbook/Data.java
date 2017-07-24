package com.example.arlan.addressbook;

/**
 * Created by arlan on 7/16/2017.
 */



public class Data {

    private String mName;
    private int mMobile;
    private String mEmail;


    /*==============================================
    *  Setter of data in ArrayList so that when we
    *  call the get functions eg.[getName()] it will
    *  return the data that was set on a specified
    *  position
    *===============================================*/
    public Data(String name, int mobile, String email){
        mName = name;
        mMobile = mobile;
        mEmail = email;
    }


    /*==============================================
    *  Methods below are the getter when we want to
    *  fetch data on a specified position.
    *
    *  these will be used in the RecyclerAdapter class
    *===============================================*/
    public String getName(){
        return mName;
    }

    public int getMobile(){
        return mMobile;
    }

    public String getEmail(){
        return mEmail;
    }


}
