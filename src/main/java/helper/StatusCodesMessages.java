package helper;

public enum StatusCodesMessages{
    SUCCESS(200,"Request succeeded"),
    CREATED(201,"Create Succeeded"),
    DELETED(204,"Delete Succeeded"),
    FAILED(400,"Bad Request - Failed By Syntactical error"),
    FAILEDAUTHENTICATION(401, "Unauthorized -Incorrect Credentials"),
    FAILEDAUTHORISATION(403, "Forbidden - No Permission/Privilege"),
    FAILEDLOCATINGENTITY(404,"Entity Not Found"),
    FAILEDMETHOD(405,"Method Not Allowed For The Request"),
    FAILEDMEDIATYPE(415,"Unsupported Media Type - Content type not supported"),
    FAILEDENTITYVALIDATION(422,"Unprocessable Entity - Entity Value Invalid - Semantical Error"),
    SERVERFAILURE(500,"Server Error");

    private final int statusCodes;
    private final String message;

    StatusCodesMessages(int statusCodes, String message){
        this.statusCodes = statusCodes;
        this.message = message;
    }
    public int getStatusCodes() {
        return statusCodes;
    }
    public String getMessage() {
        return message;
    }
}

