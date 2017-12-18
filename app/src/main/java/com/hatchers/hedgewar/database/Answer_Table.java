package com.hatchers.hedgewar.database;


public class Answer_Table {

    public static final String ANSWER_TABLE = "Answer_Table";


    public static final String ANS_ID = "ans_id",QUESTION_ID="question_id",
            ANSWER_COUNT="answer_count",ANS_DATE="ans_date",
            LOCAL_SERVEY_ID ="local_servey_id",PROGRAM_TOPIC="program_topic",
            USER_ID="user_id",VILLAGE_ID="village_id",CATEGORY="category",
            PROGRAM_HOLDER="program_holder",CURRENT_DATETIME="current_datetime",
            UPLOAD_STATUS="upload_status";

    private String ans_idValue,question_idValue,answer_countValue,ans_dateValue,
            local_servey_idValue,program_topicValue,user_idValue,village_idValue,
            categoryValue,program_holderValue,current_datetimeValue,upload_statusValue;

    public Answer_Table() {
    }

    public Answer_Table(String ans_idValue, String question_idValue, String answer_countValue,
                        String ans_dateValue, String local_servey_idValue, String program_topicValue,
                        String user_idValue, String village_idValue, String categoryValue,
                        String program_holderValue, String current_datetimeValue,
                        String upload_statusValue)
    {
        this.ans_idValue = ans_idValue;
        this.question_idValue = question_idValue;
        this.answer_countValue = answer_countValue;
        this.ans_dateValue = ans_dateValue;
        this.local_servey_idValue = local_servey_idValue;
        this.program_topicValue = program_topicValue;
        this.user_idValue = user_idValue;
        this.village_idValue = village_idValue;
        this.categoryValue = categoryValue;
        this.program_holderValue = program_holderValue;
        this.current_datetimeValue = current_datetimeValue;
        this.upload_statusValue = upload_statusValue;
    }


    public String getAns_idValue() {
        return ans_idValue;
    }

    public void setAns_idValue(String ans_idValue) {
        this.ans_idValue = ans_idValue;
    }

    public String getQuestion_idValue() {
        return question_idValue;
    }

    public void setQuestion_idValue(String question_idValue) {
        this.question_idValue = question_idValue;
    }

    public String getAnswer_countValue() {
        return answer_countValue;
    }

    public void setAnswer_countValue(String answer_countValue) {
        this.answer_countValue = answer_countValue;
    }

    public String getAns_dateValue() {
        return ans_dateValue;
    }

    public void setAns_dateValue(String ans_dateValue) {
        this.ans_dateValue = ans_dateValue;
    }

    public String getLocal_servey_idValue() {
        return local_servey_idValue;
    }

    public void setLocal_servey_idValue(String local_servey_idValue) {
        this.local_servey_idValue = local_servey_idValue;
    }

    public String getProgram_topicValue() {
        return program_topicValue;
    }

    public void setProgram_topicValue(String program_topicValue) {
        this.program_topicValue = program_topicValue;
    }

    public String getUser_idValue() {
        return user_idValue;
    }

    public void setUser_idValue(String user_idValue) {
        this.user_idValue = user_idValue;
    }

    public String getVillage_idValue() {
        return village_idValue;
    }

    public void setVillage_idValue(String village_idValue) {
        this.village_idValue = village_idValue;
    }

    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public String getProgram_holderValue() {
        return program_holderValue;
    }

    public void setProgram_holderValue(String program_holderValue) {
        this.program_holderValue = program_holderValue;
    }

    public String getCurrent_datetimeValue() {
        return current_datetimeValue;
    }

    public void setCurrent_datetimeValue(String current_datetimeValue) {
        this.current_datetimeValue = current_datetimeValue;
    }

    public String getUpload_statusValue() {
        return upload_statusValue;
    }

    public void setUpload_statusValue(String upload_statusValue) {
        this.upload_statusValue = upload_statusValue;
    }
}
