package com.tootcat.ceghostel;

public class Details
{
   public String name,rollNo,dob,department,address,email,messPreference,feePaymentStatus;

    public Details()
    {

    }

   public Details(String name,String rollNo,String dob,String department,String address,String email,String messPreference,String feePaymentStatus)
    {
        this.name=name;
        this.rollNo=rollNo;
        this.dob=dob;
        this.department=department;
        this.address=address;
        this.email=email;
        this.messPreference=messPreference;
        this.feePaymentStatus=feePaymentStatus;
    }

}
