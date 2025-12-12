package org.example.constants;

public class AppSuccessCode {

    private static final int BOOK_ADDITION_SUCCESSFUL_CODE = 301;
    private static final String BOOK_ADDITION_SUCCESSFUL_MSG = "BOOK ADDED SUCCESSFULLY";

    private static final int BOOK_DELETION_SUCCESSFUL_CODE = 302;
    private static final String BOOK_DELETION_SUCCESSFUL_MSG = "BOOK DELETED SUCCESSFULLY";

    public static int getBookAdditionSuccessfulCode() {
        return BOOK_ADDITION_SUCCESSFUL_CODE;
    }
    public static String getBookAdditionSuccessfulMsg() {
        return BOOK_ADDITION_SUCCESSFUL_MSG;
    }
    public static int getBookDeletionSuccessfulCode() {
        return BOOK_DELETION_SUCCESSFUL_CODE;
    }
    public static String getBookDeletionSuccessfulMsg() {
        return BOOK_DELETION_SUCCESSFUL_MSG;
    }


    private static final int MEMBER_ADDITION_SUCCESSFUL_CODE = 401;
    private static final String MEMBER_ADDITION_SUCCESSFUL_MSG = "MEMBER ADDED SUCCESSFULLY";

    private static final int MEMBER_DELETION_SUCCESSFUL_CODE = 402;
    private static final String MEMBER_DELETION_SUCCESSFUL_MSG = "MEMBER DELETED SUCCESSFULLY";

    public static int getMemberAdditionSuccessfulCode() {
        return MEMBER_ADDITION_SUCCESSFUL_CODE;
    }
    public static String getMemberAdditionSuccessfulMsg() {
        return MEMBER_ADDITION_SUCCESSFUL_MSG;
    }
    public static int getMemberDeletionSuccessfulCode() {
        return MEMBER_DELETION_SUCCESSFUL_CODE;
    }
    public static String getMemberDeletionSuccessfulMsg() {
        return MEMBER_DELETION_SUCCESSFUL_MSG;
    }


    private static final int TRANSACTION_SUCCESSFUL_CODE = 601;
    private static final String TRANSACTION_SUCCESSFUL_MSG = "TRANSACTION SUCCESSFULL";

    public static int getTransactionSuccessfulCode(){
        return TRANSACTION_SUCCESSFUL_CODE;
    }
    public static String getTransactionSuccessfulMsg(){
        return TRANSACTION_SUCCESSFUL_MSG;
    }



}
