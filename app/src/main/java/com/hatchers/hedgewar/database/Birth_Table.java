package com.hatchers.hedgewar.database;


public class Birth_Table  {



    public static final String BIRTH_TABLE = "Birth_Table";


    //{"status":"success","count":1,"type":"addNewAns",
    // "result":[{"birth_id":"35","name_of_mother":"voiajaya",
    // "age":"25","delivery_count":"1","month_of_registration":"1",
    // "blood_urine_test":"Y","delivery_date":"2017-12-22","place":"abad",
    // "gender":"M","birth_weight":"2","date_of_period":"2017-12-22","user_id":"1",
    // "village_id":"1","upload_status":null,"sonography_test":"Y",
    // "iron_calcium":"Y","child_death":"Y","mother_death":"Y","critical_condition":"Y",
    // "pregnancy_death":"Y"}],"message":"Birth added successfully"}

    public static final String BIRTH_ID = "birth_id", NAME_OF_MOTHER = "name_of_mother", AGE = "age",
            DELIVERY_COUNT = "delivery_count",MONTH_OF_REGISTRATION = "month_of_registration",BLOOD_URINE_TEST ="blood_urine_test",
            DELIVERY_DATE = "delivery_date",PLACE = "place",GENDER = "gender", BIRTH_WEIGHT = "birth_weight",
            DATE_OF_PERIOD = "date_of_period",USER_ID="user_id",VILLAGE_ID="village_id",UPLOAD_STATUS="upload_status",
            SONOGRAPHY_TEST="sonography_test",IRON_CALCIUM="iron_calcium",CHILD_DEATH="child_death",
            MOTHER_DEATH="mother_death", CRITICAL_CONDITION="critical_condition" ,PREGNANCY_DEATH="pregnancy_death";

    private String birthIdValue,nameOfMotherValue,ageValue,deliveryCountValue,monthOfRegistrationValue,
            bloodUrineTestValue,deliveryDateValue,placeValue,genderValue,birthWeightValue,dateOfPeriod,userIdValue,
            villageIdValue,uploadStatusValue,sonographyTestValue,ironCalciumValue,childDeathValue,motherDeathValue,
            criticalConditionValue,pregnancyDeathValue;


    public Birth_Table() {
    }

    public Birth_Table(String birthIdValue, String nameOfMotherValue, String ageValue, String deliveryCountValue, String monthOfRegistrationValue, String bloodUrineTestValue, String deliveryDateValue, String placeValue, String genderValue, String birthWeightValue, String dateOfPeriod, String userIdValue, String villageIdValue, String uploadStatusValue, String sonographyTestValue, String ironCalciumValue, String childDeathValue, String motherDeathValue, String criticalConditionValue, String pregnancyDeathValue) {
        this.birthIdValue = birthIdValue;
        this.nameOfMotherValue = nameOfMotherValue;
        this.ageValue = ageValue;
        this.deliveryCountValue = deliveryCountValue;
        this.monthOfRegistrationValue = monthOfRegistrationValue;
        this.bloodUrineTestValue = bloodUrineTestValue;
        this.deliveryDateValue = deliveryDateValue;
        this.placeValue = placeValue;
        this.genderValue = genderValue;
        this.birthWeightValue = birthWeightValue;
        this.dateOfPeriod = dateOfPeriod;
        this.userIdValue = userIdValue;
        this.villageIdValue = villageIdValue;
        this.uploadStatusValue = uploadStatusValue;
        this.sonographyTestValue = sonographyTestValue;
        this.ironCalciumValue = ironCalciumValue;
        this.childDeathValue = childDeathValue;
        this.motherDeathValue = motherDeathValue;
        this.criticalConditionValue = criticalConditionValue;
        this.pregnancyDeathValue = pregnancyDeathValue;
    }




    public String getBirthIdValue() {
        return birthIdValue;
    }

    public void setBirthIdValue(String birthIdValue) {
        this.birthIdValue = birthIdValue;
    }

    public String getNameOfMotherValue() {
        return nameOfMotherValue;
    }

    public void setNameOfMotherValue(String nameOfMotherValue) {
        this.nameOfMotherValue = nameOfMotherValue;
    }

    public String getAgeValue() {
        return ageValue;
    }

    public void setAgeValue(String ageValue) {
        this.ageValue = ageValue;
    }

    public String getDeliveryCountValue() {
        return deliveryCountValue;
    }

    public void setDeliveryCountValue(String deliveryCountValue) {
        this.deliveryCountValue = deliveryCountValue;
    }

    public String getMonthOfRegistrationValue() {
        return monthOfRegistrationValue;
    }

    public void setMonthOfRegistrationValue(String monthOfRegistrationValue) {
        this.monthOfRegistrationValue = monthOfRegistrationValue;
    }

    public String getBloodUrineTestValue() {
        return bloodUrineTestValue;
    }

    public void setBloodUrineTestValue(String bloodUrineTestValue) {
        this.bloodUrineTestValue = bloodUrineTestValue;
    }

    public String getDeliveryDateValue() {
        return deliveryDateValue;
    }

    public void setDeliveryDateValue(String deliveryDateValue) {
        this.deliveryDateValue = deliveryDateValue;
    }

    public String getPlaceValue() {
        return placeValue;
    }

    public void setPlaceValue(String placeValue) {
        this.placeValue = placeValue;
    }

    public String getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }

    public String getBirthWeightValue() {
        return birthWeightValue;
    }

    public void setBirthWeightValue(String birthWeightValue) {
        this.birthWeightValue = birthWeightValue;
    }

    public String getDateOfPeriod() {
        return dateOfPeriod;
    }

    public void setDateOfPeriod(String dateOfPeriod) {
        this.dateOfPeriod = dateOfPeriod;
    }

    public String getUserIdValue() {
        return userIdValue;
    }

    public void setUserIdValue(String userIdValue) {
        this.userIdValue = userIdValue;
    }

    public String getVillageIdValue() {
        return villageIdValue;
    }

    public void setVillageIdValue(String villageIdValue) {
        this.villageIdValue = villageIdValue;
    }

    public String getUploadStatusValue() {
        return uploadStatusValue;
    }

    public void setUploadStatusValue(String uploadStatusValue) {
        this.uploadStatusValue = uploadStatusValue;
    }

    public String getSonographyTestValue() {
        return sonographyTestValue;
    }

    public void setSonographyTestValue(String sonographyTestValue) {
        this.sonographyTestValue = sonographyTestValue;
    }

    public String getIronCalciumValue() {
        return ironCalciumValue;
    }

    public void setIronCalciumValue(String ironCalciumValue) {
        this.ironCalciumValue = ironCalciumValue;
    }

    public String getChildDeathValue() {
        return childDeathValue;
    }

    public void setChildDeathValue(String childDeathValue) {
        this.childDeathValue = childDeathValue;
    }

    public String getMotherDeathValue() {
        return motherDeathValue;
    }

    public void setMotherDeathValue(String motherDeathValue) {
        this.motherDeathValue = motherDeathValue;
    }

    public String getCriticalConditionValue() {
        return criticalConditionValue;
    }

    public void setCriticalConditionValue(String criticalConditionValue) {
        this.criticalConditionValue = criticalConditionValue;
    }

    public String getPregnancyDeathValue() {
        return pregnancyDeathValue;
    }

    public void setPregnancyDeathValue(String pregnancyDeathValue) {
        this.pregnancyDeathValue = pregnancyDeathValue;
    }
}
