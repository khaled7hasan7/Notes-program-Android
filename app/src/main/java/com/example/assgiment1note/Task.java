package com.example.assgiment1note;


import java.io.Serializable;

public class Task implements Serializable {



    private String name ;

    public Task(){
        super();
    }

    public Task( String name) {
        super();




        this.name = name;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  ", name=" + name + "]";
    }




}
