package org.example.constants;


public class AppErrorCode {

    //BOOK ERRORS

    private static final int BOOKS_NOT_FOUND_ERROR_CODE = 101;
    private static final String BOOKS_NOT_FOUND_ERROR_MSG = "BOOKS NOT FOUND";




    private static final int BOOK_ISSUE_ERROR_CODE = 102;
    private static final String BOOK_ISSUE_ERROR_MSG = "BOOK ISSUE FAILED (NO BOOKS LEFT)";

    private static final int BOOK_ADDITION_ERROR_CODE = 103;
    private static final String BOOK_ADDITION_ERROR_MSG = "BOOK ADDITION FAILED";

    private static final int BOOK_DELETION_ERROR_CODE = 104;
    private static final String BOOK_DELETION_ERROR_MSG = "BOOK DELETION FAILED";

    private static final int BOOK_DELETION_CAUSE_ISSUED_ERROR_CODE = 105;
    private static final String BOOK_DELETION_CAUSE_ISSUED_ERROR_MSG = "BOOK DELETION FAILED (BOOK IS ISSUED)";

    private static final int BOOKS_LOADING_ERROR_CODE = 106;
    private static final String BOOKS_LOADING_ERROR_MSG = "ERROR LOADING BOOKS";


    private static final int BOOK_BY_ID_ERROR_CODE = 107;
    private static final String BOOK_BY_ID_ERROR_MSG = "COULDN'T RECIEVE BOOK BY ID HENCE PROCESS FAILED";

    private static final int BOOK_NOT_FOUND_ERROR_CODE = 108;
    private static final String BOOK_NOT_FOUND_ERROR_MSG = "BOOK DOESN'T EXIST";

    public int getNoBooksFoundErrorCode() {
        return BOOKS_NOT_FOUND_ERROR_CODE;
    }
    public String getNoBooksFoundErrorMsg() {
        return BOOKS_NOT_FOUND_ERROR_MSG;
    }
    public int getBookIssueErrorCode() {
        return BOOK_ISSUE_ERROR_CODE;
    }
    public String getBookIssueErrorMsg() {
        return BOOK_ISSUE_ERROR_MSG;
    }
    public int getBookAdditionErrorCode() {
        return BOOK_ADDITION_ERROR_CODE;
    }
    public String getBookAdditionErrorMsg() {
        return BOOK_ADDITION_ERROR_MSG;
    }
    public int getBookDeletionErrorCode() {
        return BOOK_DELETION_ERROR_CODE;
    }
    public String getBookDeletionErrorMsg() {
        return BOOK_DELETION_ERROR_MSG;
    }
    public static int getBookDeletionCauseIssuedErrorCode() {
        return BOOK_DELETION_CAUSE_ISSUED_ERROR_CODE;
    }
    public String getBookDeletionCauseIssuedErrorMsg() {
        return BOOK_DELETION_CAUSE_ISSUED_ERROR_MSG;
    }
    public int getBooksLoadingErrorCode() {
        return BOOKS_LOADING_ERROR_CODE;
    }
    public String getBooksLoadingErrorMsg() {
        return BOOKS_LOADING_ERROR_MSG;
    }
    public int getBooksByIdErrorCode() {
        return BOOK_BY_ID_ERROR_CODE;
    }
    public String getBooksByIdErrorMsg() {
        return BOOK_BY_ID_ERROR_MSG;
    }
    public int getBookNotFoundErrorCode() {
        return BOOK_NOT_FOUND_ERROR_CODE;
    }
    public String getBookNotFoundErrorMsg() {
        return BOOK_NOT_FOUND_ERROR_MSG;
    }


    //MEMBER ERRORS

    private static final int MEMBER_NOT_FOUND_ERROR_CODE = 201;
    private static final String MEMBER_NOT_FOUND_ERROR_MSG = "MEMBER NOT FOUND";

    private static final int MEMBER_ADDITION_ERROR_CODE = 202;
    private static final String MEMBER_ADDITION_ERROR_MSG = "MEMBER ADDITION FAILED";

    private static final int MEMBER_DELETION_ERROR_CODE = 203;
    private static final String MEMBER_DELETION_ERROR_MSG = "MEMBER DELETION FAILED";

    private static final int MEMBER_DETAILS_ERROR_CODE = 204;
    private static final String MEMBER_DETAILS_ERROR_MSG = "ERROR LOADING MEMBER DETAILS";

    private static final int MEMBERS_LOADING_ERROR_CODE = 205;
    private static final String MEMBERS_LOADING_ERROR_MSG = "ERROR LOADING MEMBERS";

    private static final int MEMBER_BOOKS_LOADING_ERROR_CODE = 206;
    private static final String MEMBER_BOOKS_LOADING_ERROR_MSG = "ERROR LOADING MEMBER BOOKS";



    public int getMemberNotFoundErrorCode() {
        return MEMBER_NOT_FOUND_ERROR_CODE;
    }
    public String getMemberNotFoundErrorMsg() {
        return MEMBER_NOT_FOUND_ERROR_MSG;
    }
    public int getMemberAdditionErrorCode() {
        return MEMBER_ADDITION_ERROR_CODE;
    }
    public String getMemberAdditionErrorMsg() {
        return MEMBER_ADDITION_ERROR_MSG;
    }
    public int getMemberDeletionErrorCode() {
        return MEMBER_DELETION_ERROR_CODE;
    }
    public String getMemberDeletionErrorMsg() {
        return MEMBER_DELETION_ERROR_MSG;
    }
    public int getMemberDetailsErrorCode() {
        return MEMBER_DETAILS_ERROR_CODE;
    }
    public String getMemberDetailsErrorMsg() {
        return MEMBER_DETAILS_ERROR_MSG;
    }
    public int  getMembersLoadingErrorCode() {
        return MEMBERS_LOADING_ERROR_CODE;
    }
    public String getMembersLoadingErrorMsg() {
        return MEMBERS_LOADING_ERROR_MSG;
    }

    public static int getMemberBooksLoadingErrorCode() {
        return MEMBER_BOOKS_LOADING_ERROR_CODE;
    }

    public static String getMemberBooksLoadingErrorMsg() {
        return MEMBER_BOOKS_LOADING_ERROR_MSG;
    }


    //TRANSACTION ERRORS

    private static final int TRANSACTIONS_LOADING_ERROR_CODE = 501;
    private static final String TRANSACTIONS_LOADING_ERROR_MSG = "ERROR LOADING TRANSACTIONS";

    private static final int TRANSACTION_FAILED_ERROR_CODE = 502;
    private static final String TRANSACTION_FAILED_ERROR_MSG = "TRANSACTION FAILED";



    public static int  getTransactionsLoadingErrorCode() {
        return TRANSACTIONS_LOADING_ERROR_CODE;
    }
    public String getTransactionsLoadingErrorMsg() {
        return TRANSACTIONS_LOADING_ERROR_MSG;
    }
    public int getTransactionFailedErrorCode() {
        return TRANSACTION_FAILED_ERROR_CODE;
    }
    public String getTransactionFailedErrorMsg() {
        return TRANSACTION_FAILED_ERROR_MSG;
    }
}


