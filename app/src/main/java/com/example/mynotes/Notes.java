package com.example.mynotes;

public class Notes {

    int id ;
    String title , name;
    int age;
   String note;
    String detail;
    // String password ;


    public Notes(int id , int age , String name , String title, String detail ) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.name = name;
        this.age = age;
       // this.note = note;
    }

    public Notes(String title, String note ) // String password)
    //
    {
        this.title = title;
        this.note = note;
       // this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
    public int getAge() { return age;
    }


    public void setAge(int age) {
        this.age = age;
    }



    public String getNote() { return note;
    }


    public void setNote(String note) {
        this.note = note;
    }

   /* public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  */
}
