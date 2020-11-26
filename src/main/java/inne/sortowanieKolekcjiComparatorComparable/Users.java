package inne.sortowanieKolekcjiComparatorComparable;

public  class Users implements Comparable<Users> {

    //interfejs comparable możemy implementować do klas, które nie są final

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(Users user) {

        //interfejs compareTO porównuje pierwszą wartość this.username i jej położenie względem wartosci drugiej
        // czyli w tym przypadku wartości user.getUsername;
        return this.username.compareTo(user.getUsername());
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
