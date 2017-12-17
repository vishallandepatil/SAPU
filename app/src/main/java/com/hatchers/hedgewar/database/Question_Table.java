package com.hatchers.hedgewar.database;

/**
 * Created by Ashwin on 16-Dec-17.
 */

public class Question_Table
{
    public static final String QUESTION_TABLE = "question_table";

    public static final String QUESTION_ID = "question_id",SR_NO="Sr_No",
            QUESTION_MARATHI = "Question_Marathi", QUESTION_ENGLISH="Question_English",
            TYPE = "Type", CATEGORY_MARATHI = "Category_Marathi",
            UPLOAD_STATUS = "upload_status",CATEGORY_ENGLISH ="Category_English";

    private String QUESTION_ID_VALUE ,SR_NO_VALUE, QUESTION_MARATHI_VALUE, QUESTION_ENGLISH_VALUE,
            TYPE_VALUE , CATEGORY_MARATHI_VALUE ,
            UPLOAD_STATUS_VALUE ,CATEGORY_ENGLISH_VALUE ;

    public static final String CATEGORY_HEALTH="H",CATEGORY_EVENT="E",CATEGORY_FEEDBACK="F";


    public Question_Table(String QUESTION_ID_VALUE, String SR_NO_VALUE, String QUESTION_MARATHI_VALUE,
                          String QUESTION_ENGLISH_VALUE, String TYPE_VALUE, String CATEGORY_MARATHI_VALUE,
                          String UPLOAD_STATUS_VALUE, String CATEGORY_ENGLISH_VALUE)
    {
        this.QUESTION_ID_VALUE = QUESTION_ID_VALUE;
        this.SR_NO_VALUE = SR_NO_VALUE;
        this.QUESTION_MARATHI_VALUE = QUESTION_MARATHI_VALUE;
        this.QUESTION_ENGLISH_VALUE = QUESTION_ENGLISH_VALUE;
        this.TYPE_VALUE = TYPE_VALUE;
        this.CATEGORY_MARATHI_VALUE = CATEGORY_MARATHI_VALUE;
        this.UPLOAD_STATUS_VALUE = UPLOAD_STATUS_VALUE;
        this.CATEGORY_ENGLISH_VALUE = CATEGORY_ENGLISH_VALUE;
    }

    public Question_Table() {

    }

    public String getQUESTION_ID_VALUE() {
        return QUESTION_ID_VALUE;
    }

    public void setQUESTION_ID_VALUE(String QUESTION_ID_VALUE) {
        this.QUESTION_ID_VALUE = QUESTION_ID_VALUE;
    }

    public String getSR_NO_VALUE() {
        return SR_NO_VALUE;
    }

    public void setSR_NO_VALUE(String SR_NO_VALUE) {
        this.SR_NO_VALUE = SR_NO_VALUE;
    }

    public String getQUESTION_MARATHI_VALUE() {
        return QUESTION_MARATHI_VALUE;
    }

    public void setQUESTION_MARATHI_VALUE(String QUESTION_MARATHI_VALUE) {
        this.QUESTION_MARATHI_VALUE = QUESTION_MARATHI_VALUE;
    }

    public String getQUESTION_ENGLISH_VALUE() {
        return QUESTION_ENGLISH_VALUE;
    }

    public void setQUESTION_ENGLISH_VALUE(String QUESTION_ENGLISH_VALUE) {
        this.QUESTION_ENGLISH_VALUE = QUESTION_ENGLISH_VALUE;
    }

    public String getTYPE_VALUE() {
        return TYPE_VALUE;
    }

    public void setTYPE_VALUE(String TYPE_VALUE) {
        this.TYPE_VALUE = TYPE_VALUE;
    }

    public String getCATEGORY_MARATHI_VALUE() {
        return CATEGORY_MARATHI_VALUE;
    }

    public void setCATEGORY_MARATHI_VALUE(String CATEGORY_MARATHI_VALUE) {
        this.CATEGORY_MARATHI_VALUE = CATEGORY_MARATHI_VALUE;
    }

    public String getUPLOAD_STATUS_VALUE() {
        return UPLOAD_STATUS_VALUE;
    }

    public void setUPLOAD_STATUS_VALUE(String UPLOAD_STATUS_VALUE) {
        this.UPLOAD_STATUS_VALUE = UPLOAD_STATUS_VALUE;
    }

    public String getCATEGORY_ENGLISH_VALUE() {
        return CATEGORY_ENGLISH_VALUE;
    }

    public void setCATEGORY_ENGLISH_VALUE(String CATEGORY_ENGLISH_VALUE) {
        this.CATEGORY_ENGLISH_VALUE = CATEGORY_ENGLISH_VALUE;
    }
}
