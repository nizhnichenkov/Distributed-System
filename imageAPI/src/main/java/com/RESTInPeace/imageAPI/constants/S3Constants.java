package com.RESTInPeace.imageAPI.constants;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;

public class S3Constants {
    public static final AWSCredentials S3_CREDENTIALS = new BasicAWSCredentials(
            "***********************",
            "******************************************"
    );

    public static final Regions REGION = Regions.US_EAST_1;
    public static final String BUCKET_NAME = "****************************";
}
