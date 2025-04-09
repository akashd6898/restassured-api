package pojo;

public class AuthorDetailsPojo {
    private String firstName;
    private String lastName;
    private int id;
    private int idBook;
    //public AuthorDetailsPojo() {}
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }
    public int getIdBook() {
        return idBook;
    }

}
