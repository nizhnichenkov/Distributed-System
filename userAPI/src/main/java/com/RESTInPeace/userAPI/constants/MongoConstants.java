package com.RESTInPeace.userAPI.constants;

public class MongoConstants {
    public static final String DATABASE_URL_FORMAT = "mongodb+srv://%s:%s@%s.pkzn4.mongodb.net/%s?retryWrites=true&w=majority";

    public static final String DATABASE_PASSWORD = "***********";
    public static final String DATABASE_USER = "***********";
    public static final String DATABASE_NAME = "REST_in_peace";
    public static final String CLUSTER_NAME = "cluster0";
    public static final String COLLECTION_NAME = "users";

    // not a DB parameter - but an object
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String ID = "_id";
}
