package app.mappers.dto;

import app.domain.model.Client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @author Rui Rocha
 */
public class ClientDto {

    //region Attributes
    /**
     * Name.
     */
    private String name;

    /**
     * Date of birth.
     */
    private Date birthDate;
    /**
     * Sex.
     */
    private ClientDto.Sex sex;
    /**
     * Citizen Card Number.
     */
    private String ccn;
    /**
     * National Health Service Number.
     */
    private String nhsNumber;
    /**
     * Tax Identification Number.
     */
    private String tin;
    /**
     * Phone Number.
     */
    private String phoneNumber;
    /**
     * E-mail.
     */
    private String email;

    /**
     * Constructs an instance of ClientDto, receiving the Name, Citizen Card Number,
     * NHS Number, Birth Date, Sex, Tax Identification Number, Phone Number and Email.
     *
     * @param name        client's name.
     * @param birthDate   client's birth date.
     * @param sex         client's sex..
     * @param ccn         client's citizen card number.
     * @param nhsNumber   client's NHS number.
     * @param tin         client's Tax Identification Number.
     * @param phoneNumber client's phone number.
     * @param email       client's email.
     */
    public ClientDto(String name, Date birthDate, ClientDto.Sex sex, String ccn,
                     String nhsNumber, String tin, String phoneNumber, String email) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.ccn = ccn;
        this.nhsNumber = nhsNumber;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    //endregion

    /**
     * Returns the client's name.
     *
     * @return client's name.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the client's birth date.
     *
     * @return client's birth date.
     */
    public Date getBirthDate() {
        return (Date) birthDate.clone();
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Returns the client's sex.
     *
     * @return client's sex.
     */
    public Client.Sex getSex() {
        return Client.Sex.valueOf(this.sex.name());
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * Returns the client's citizen card number.
     *
     * @return client's citizen card number.
     */
    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    /**
     * Returns the client's NHS number.
     *
     * @return client's NHS number.
     */
    public String getNhsNumber() {
        return nhsNumber;
    }
//region Getters

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    /**
     * Returns the client's Tax Identification Number.
     *
     * @return client's Tax Identification Number.
     */
    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    /**
     * Returns the client's phone number.
     *
     * @return client's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the client's email.
     *
     * @return client's email.
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the client's age.
     *
     * @return client's age.
     */
    public int getAge() {
        Calendar today = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (age > 0 && (birth.get(Calendar.MONTH) > today.get(Calendar.MONTH))
                || (birth.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                (birth.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }

    /**
     * Sexes.
     */
    public enum Sex {
        MALE(1) {
            @Override
            public String toString() {
                return "Male";
            }
        },
        FEMALE(2) {
            @Override
            public String toString() {
                return "Female";
            }
        },
        OTHER(3) {
            @Override
            public String toString() {
                return "Other";
            }
        },
        NONE(4) {
            @Override
            public String toString() {
                return "None";
            }
        };

        int op;

        private Sex(int op) {
            this.op = op;
        }

        public static Stream<ClientDto.Sex> stream() {
            return Stream.of(ClientDto.Sex.values());
        }

        public int showOp() {
            return op;
        }
    }

    //endregion

    @Override
    public String toString() {
        return String.format("%s [%s]", name, tin);
//        return String.format("CLIENT %nName: %s %nBirth Date: %s (%d years old) %n"
//                        + "Sex: %s %nCitizen Card Number: %s %n" +
//                        "NHS Number: %s %nTax Identification Number: %s %n" +
//                        "Phone Number: %s %nEmail: %s%n",
//                name, new SimpleDateFormat("dd-MM-yyyy").format(birthDate),
//                getAge(), sex.toString(), ccn, nhsNumber, tin, phoneNumber, email);
    }
}
