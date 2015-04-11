package com.djzass.medipoint;

import android.provider.BaseColumns;

public class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DbContract() {}

    /* Inner class that defines the table contents */
    public static abstract class AccountEntry implements BaseColumns {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_ACCOUNT_ID = "accountId";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NRIC = "nric";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_CONTACTNO = "contactNo";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_DOB = "dob";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_MARITAL_STATUS = "maritalStatus";
        public static final String COLUMN_NAME_CITIZENSHIP = "citizenship";
        public static final String COLUMN_NAME_COUNTRY_OF_RESIDENCE = "countryOfResidence";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NOTIFY_SMS = "notifySms";
        public static final String COLUMN_NAME_NOTIFY_EMAIL = "notifyEmail";

    }

    public static abstract class AppointmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "appointment";
        public static final String COLUMN_NAME_APPOINTMENT_ID = "appointmentId";
        public static final String COLUMN_NAME_CLINIC_ID = "clinicId";
        public static final String COLUMN_NAME_PATIENT_ID = "patientId";
        public static final String COLUMN_NAME_DOCTOR_ID = "doctorId";
        public static final String COLUMN_NAME_REFERRER_ID = "referrerId";
        public static final String COLUMN_NAME_DATE_TIME = "dateTime";
        public static final String COLUMN_NAME_SERVICE_ID = "service";
        public static final String COLUMN_NAME_SPECIALTY_ID = "specialty";
        public static final String COLUMN_NAME_PREAPPOINTMENT_ACTIONS = "preAppointmentActions";
        public static final String COLUMN_NAME_START_TIME = "startTime";
        public static final String COLUMN_NAME_END_TIME = "endTime";
    }

    public static abstract class ClinicEntry implements BaseColumns {
        public static final String TABLE_NAME = "clinic";
        public static final String COLUMN_NAME_CLINIC_ID = "clinicId";
        public static final String COLUMN_NAME_CLINIC_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_ZIPCODE = "zipCode";
        public static final String COLUMN_NAME_TEL_NUMBER = "telNumber";
        public static final String COLUMN_NAME_FAX_NUMBER = "faxNumber";
        public static final String COLUMN_NAME_EMAIL = "email";
    }

    public static abstract class SpecialtyEntry implements BaseColumns{
        public static final String TABLE_NAME = "specialty";
        public static final String COLUMN_NAME_SPECIALTY_ID = "specialtyId";
        public static final String COLUMN_NAME_SPECIALTY_NAME = "specialtyName";
    }

    public static abstract class DoctorEntry implements BaseColumns{
        public static final String TABLE_NAME = "doctor";
        public static final String COLUMN_NAME_DOCTOR_ID = "doctorId";
        public static final String COLUMN_NAME_DOCTOR_NAME = "name";
        public static final String COLUMN_NAME_SPECIALIZATION_ID = "specializationId";
        public static final String COLUMN_NAME_PRACTICE_DURATION = "practiceDuration";
        public static final String COLUMN_NAME_CLINIC_ID = "clinicId";
    }

    public static abstract class DoctorScheduleEntry implements BaseColumns{
        public static final String TABLE_NAME = "doctorSchedule";
        public static final String COLUMN_NAME_DOCTOR_SCHEDULE_ID = "doctorScheduleId";
        public static final String COLUMN_NAME_DOCTOR_ID = "doctorId";
        public static final String COLUMN_NAME_CLINIC_ID = "clinicId";
        public static final String COLUMN_NAME_DAY = "day";
        public static final String COLUMN_NAME_START_TIME = "startTime";
        public static final String COLUMN_NAME_END_TIME = "endTime";
    }

    public static abstract class ServiceEntry implements BaseColumns{
        public static final String TABLE_NAME = "service";
        public static final String COLUMN_NAME_SERVICE_ID = "serviceId";
        public static final String COLUMN_NAME_SERVICE_NAME = "specialtyName";
        public static final String COLUMN_NAME_SPECIALTY_ID = "specialtyId";
        public static final String COLUMN_NAME_SERVICE_DURATION = "serviceDuration";
        public static final String COLUMN_NAME_PREAPPOINTMENT_ACTIONS = "preAppointmentActions";
    }

    public static abstract class PatientEntry implements BaseColumns{
        public static final String TABLE_NAME = "patient";
        public static final String COLUMN_NAME_PATIENT_ID = "patientId";
        public static final String COLUMN_NAME_DOB = "dob";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_MEDICAL_HISTORY = "medicalHistory";
        public static final String COLUMN_NAME_ALLERGIES = "allergies";
        public static final String COLUMN_NAME_TREATMENTS = "treatments";
        public static final String COLUMN_NAME_MEDICATIONS = "medications";

    }

}
