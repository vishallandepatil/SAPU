package com.hatchers.hedgewar.database;


public class Birth_Table  {



    public static final String BIRTH_TABLE = "Birth_Table";

    public static final String BIRTH_ID = "birth_id",VILLAGE_ID="village_id",
            NAME_OF_MOTHER = "name_of_mother", AGE = "age", DELIVERY_COUNT = "delivery_count",
            MONTH_OF_REGISTRATION = "month_of_registration",BLOOD_URINE_TEST ="blood_urine_test",
            DELIVERY_DATE = "delivery_date", PLACE = "place", GENDER = "gender",
            BIRTH_WEIGHT = "birth_weight", DATE_OF_PERIOD = "date_of_period",USER_ID="user_id",
            UPLOAD_STATUS="upload_status";

    private String name_of_motherValue,blood_urine_testValue,placeValue,genderValue,
            birth_idValue,ageValue,delivery_countValue,birth_weightValue,village_idValue,
            month_of_registrationValue,date_of_period,delivery_dateValue,userIdValue,
            uploadStatusValue;


    public Birth_Table() {

    }

    public Birth_Table(String name_of_motherValue, String blood_urine_testValue,
                       String placeValue, String genderValue, String birth_idValue,
                       String ageValue, String delivery_countValue,
                       String birth_weightValue, String village_idValue,
                       String month_of_registrationValue, String date_of_period,
                       String delivery_dateValue)
    {
        this.name_of_motherValue = name_of_motherValue;
        this.blood_urine_testValue = blood_urine_testValue;
        this.placeValue = placeValue;
        this.genderValue = genderValue;
        this.birth_idValue = birth_idValue;
        this.ageValue = ageValue;
        this.delivery_countValue = delivery_countValue;
        this.birth_weightValue = birth_weightValue;
        this.village_idValue = village_idValue;
        this.month_of_registrationValue = month_of_registrationValue;
        this.date_of_period = date_of_period;
        this.delivery_dateValue = delivery_dateValue;
    }

    public String getName_of_motherValue() {
        return name_of_motherValue;
    }

    public void setName_of_motherValue(String name_of_motherValue) {
        this.name_of_motherValue = name_of_motherValue;

    }

    public String getBlood_urine_testValue() {
        return blood_urine_testValue;
    }

    public void setBlood_urine_testValue(String blood_urine_testValue) {
        this.blood_urine_testValue = blood_urine_testValue;
    }

    public String getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }

    public String getPlaceValue() {
        return placeValue;
    }

    public void setPlaceValue(String placeValue) {
        this.placeValue = placeValue;
    }

    public String getAgeValue() {
        return ageValue;
    }

    public void setAgeValue(String ageValue) {
        this.ageValue = ageValue;
    }

    public String getBirth_idValue() {
        return birth_idValue;
    }

    public void setBirth_idValue(String birth_idValue) {
        this.birth_idValue = birth_idValue;
    }

    public String getdelivery_countValue() {
        return delivery_countValue;
    }

    public void setdelivery_countValue(String delivery_countValue) {
        this.delivery_countValue = delivery_countValue;
    }

    public String getBirth_weightValue() {
        return birth_weightValue;
    }

    public void setBirth_weightValue(String birth_weightValue) {
        this.birth_weightValue = birth_weightValue;
    }

    public String getMonth_of_registrationValue() {
        return month_of_registrationValue;
    }

    public void setMonth_of_registrationValue(String month_of_registrationValue) {
        this.month_of_registrationValue = month_of_registrationValue;
    }

    public String getDate_of_period() {
        return date_of_period;
    }

    public void setDate_of_period(String date_of_period) {
        this.date_of_period = date_of_period;
    }

    public String getDelivery_dateValue() {
        return delivery_dateValue;
    }

    public void setDelivery_dateValue(String delivery_dateValue) {
        this.delivery_dateValue = delivery_dateValue;
    }

    public String getVillage_idValue() {
        return village_idValue;
    }

    public void setVillage_idValue(String village_idValue) {
        this.village_idValue = village_idValue;
    }

    public String getUserIdValue() {
        return userIdValue;
    }

    public void setUserIdValue(String userIdValue) {
        this.userIdValue = userIdValue;
    }

    public String getUploadStatusValue() {
        return uploadStatusValue;
    }

    public void setUploadStatusValue(String uploadStatusValue) {
        this.uploadStatusValue = uploadStatusValue;
    }
}
