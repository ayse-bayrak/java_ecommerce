package e_commerce_app;

import java.util.List;
import java.util.UUID;

public class Customer {
    //In the requirement there are two different way create Customer..
    private UUID id; // primary key
    private String userName;
    private String email;
    private List<Address> addresses;
    // we need to List because with preferable way --> Interface abc = new Class() - >  we need to apply polymorhism
    // private Address address; // only one address, wrong, it is not wrong syntax?
    // it is wrong with my business logic
    // multiple addresses can be used
    // more than one terminology meaning this is a List
    // My customer object has String userName
    // My customer object has String email
    // My customer object has object of address

    // Which type have we learned?
    // primitive and non primitive
    // non-primitive, what is means, collection is a class, String is a Class, object is coming from one Class
    // so actually you can classes as well as a type.
    // type name;
    // String name;
    // Class name ,
    // Address address;
    // because address is individually has certain fields..

    // {}  curly brace represents objects. This one of the object name
    // {ayse, ayse@cydeo.com, {7925, abcstreet, 22066}} Customer object
    // This is how your object is gonna look like
    // This is very comman structure in the development environment

    // ArrayList<Address> addresses; // it is ok, but it is not preferable structure
    // in the future maybe there might be requirement, we don't know
    // the ruler is always put interface option, not class

    //ArrayList<Address> addresses = new ArrayList<>();
    // Class abc = new Class // not preferable
    // Interface abc = new Class //preferable
    // this give flexibility because if you put List right now, in the future if you want to use besides something else, not ArrayList
    //You can convert your project very easily


//    public Customer(String userName, String email) {
//        this.userName = userName;
//        this.email = email;
//    }

    //we need to 2 constructor -- what we call this constructors? Overloaded constructors
    // IQ what is overloading constructor? in real life perspective-- constructor overloading is giving option for create object
    public Customer(UUID id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Customer(UUID id, String userName, String email, List<Address> addresses) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.addresses = addresses;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public String toString() {
        return "ozzy_e_commerce_lab.Customer{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
/*
some customer ask me create an e-Commerce Application

-Design DB
-Create menu items
    -Buy product

 Write Business Logic for these menu items
 ------------
 Create Customer
 -with username, email
 -with username, email, address
 */

