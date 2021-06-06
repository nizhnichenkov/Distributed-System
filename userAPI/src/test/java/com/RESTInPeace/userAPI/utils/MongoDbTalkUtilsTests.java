package com.RESTInPeace.userAPI.utils;

import com.RESTInPeace.models.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MongoDbTalkUtilsTests {
    @Mock
    private MongoCollection<Document> collection;
    private MongoDbTalkUtils mongoDbTalkUtils;
    private User testUser = User.builder()
            .email("sveto@ucdconnect.ie")
            .name("svetos")
            .build();

    @Before
    public void setUp() {
        mongoDbTalkUtils = new MongoDbTalkUtils();
        mongoDbTalkUtils.collection = this.collection;
    }

    @Test
    public void createUserTest() {
        assertTrue(mongoDbTalkUtils.createUser(testUser));
    }

    @Test(expected = NoSuchFieldException.class)
    public void getUserDoesntExistTest() throws NoSuchFieldException {
        final User user = mongoDbTalkUtils.getUser("sveto@ucdconnect.ie");
        assertNotNull(user);
    }
}