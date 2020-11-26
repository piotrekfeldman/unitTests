package pl.testing.account;


import java.util.function.Predicate;

public class Account {
    private boolean active;
    private Address defaultDeliveryAddress;
    private String email;

    public Account() {
        this.active = false;
    }


    public void activate() {
        this.active = true;
    }
    public void disactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if(defaultDeliveryAddress != null) {
            activate();
        } else {
            disactivate();
        }

    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }

    public void setEmail(String email) {



        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);

        if(email.matches(ePattern))
        {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Wrong email");
        }
    }



    }

