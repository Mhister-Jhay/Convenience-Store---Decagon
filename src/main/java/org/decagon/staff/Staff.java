package org.decagon.staff;

import lombok.Data;

@Data
public abstract class Staff {
    private String name;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String email;

    protected Staff(String name, Integer age, String gender, String phoneNumber, String email){
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }

   protected Staff(){

    }
}
