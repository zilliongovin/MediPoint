package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.String;
import java.util.Calendar;
import java.util.Calendar;

public class Account implements Parcelable {
    private long id;
	private String username;
	private String password;
	private String name;
	private String nric;
	private String email;
    private String phoneNumber;
	private String gender;
	private String address;
	private String maritalStatus;
	private Calendar dob;
	private String citizenship;
	private String countryOfResidence;
    private int notifyEmail;
    private int notifySMS;

    public Account(){

    }

    public Account(String username, String password, String name, String nric,
                   String email, String phoneNumber, String gender, String address,
                   String maritalStatus, Calendar dob, String citizenship,
                   String countryOfResidence, int notifyEmail, int notifySMS) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nric = nric;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.dob = dob;
        this.citizenship = citizenship;
        this.countryOfResidence = countryOfResidence;
        this.notifyEmail = notifyEmail;
        this.notifySMS = notifySMS;
    }

    public Account(long id, String username, String password, String name, String nric,
                   String email, String phoneNumber, String gender, String address,
                   String maritalStatus, Calendar dob, String citizenship,
                   String countryOfResidence, int notifyEmail, int notifySMS) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.nric = nric;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.dob = dob;
        this.citizenship = citizenship;
        this.countryOfResidence = countryOfResidence;
        this.notifySMS = notifySMS;
        this.notifyEmail = notifyEmail;
    }

    /*public Account(String name, String nric, String email, String phoneNumber, String address) {

        this.name = name;
        this.nric = nric;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }*/

    public String print(){
        String temp = "";
        temp+= id + " ";
        temp+= username + " ";
        temp+= password + " ";
        temp+= name + " ";
        temp+= nric + " ";
        temp+= email + " ";
        temp+= phoneNumber + " ";
        temp+= gender + " ";
        temp+= address + " ";
        temp+= maritalStatus + " ";
        temp+= String.valueOf(dob) + " ";
        temp+= citizenship + " ";
        temp+= countryOfResidence + " ";
        temp+= notifyEmail + " ";
        temp+= notifySMS + " ";
        return temp;
    }

	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(int notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public int getNotifySMS() {
        return notifySMS;
    }

    public void setNotifySMS(int notifySMS) {
        this.notifySMS = notifySMS;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parc, int flags) {
        parc.writeLong(this.id);
        parc.writeString(this.username);
        parc.writeString(this.password);
        parc.writeString(this.name);
        parc.writeString(this.nric);
        parc.writeString(this.email);
        parc.writeString(this.phoneNumber);
        parc.writeString(this.gender);
        parc.writeString(this.address);
        parc.writeString(this.maritalStatus);
        parc.writeString(this.citizenship);
        parc.writeString(this.countryOfResidence);
        parc.writeInt(this.notifyEmail);
        parc.writeInt(this.notifySMS);
    }

    public static final Parcelable.Creator<Appointment> CREATOR
            = new Parcelable.Creator<Appointment>() {
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    public void readFromParcel(Parcel in2) {
        this.id = in2.readLong();
        this.username = in2.readString();
        this.password = in2.readString();
        this.name = in2.readString();
        this.nric = in2.readString();
        this.email = in2.readString();
        this.phoneNumber = in2.readString();
        this.gender = in2.readString();
        this.address = in2.readString();
        this.maritalStatus = in2.readString();
        this.citizenship = in2.readString();
        this.countryOfResidence = in2.readString();
        this.notifyEmail= in2.readInt();
        this.notifySMS = in2.readInt();
    }
}
