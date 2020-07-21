package com.sample;

import com.sample.dao.user.UserDaoImpl;
import com.sample.model.UserInfo;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersTest {

    private UserDaoImpl userDao = new UserDaoImpl();
    private Logger log = LoggerFactory.getLogger(getClass());

    @BeforeAll
    public static void addData() {
        Logger log = LoggerFactory.getLogger(UsersTest.class);
        try {
            UserDaoImpl userDao2 = new UserDaoImpl();
            UserInfo user1 = new UserInfo("testingUsername1", "testingPassword1", "Test Full Name 1");
            UserInfo user2 = new UserInfo("testingUsername2", "testingPassword2", "Test Full Name 2");
            userDao2.save(user1);
            userDao2.save(user2);
        } catch (SQLException e) {
            log.error("Error Connection To Datasource. Please Check Datasource Connection");

        } catch (NullPointerException e) {
            log.error("Error Connection To Datasource. Please Check Datasource Connection");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    @Test
    @DisplayName("Test Get Users")
    public void testIfUsersWereAdded() {
        assertEquals(5, userDao.getUsers(null).size());
    }

    @Test
    public void testGetUser() {
        UserInfo user = userDao.findByUsername("testingUsername1");
        assertNotNull(user);
        assertEquals("Test Full Name 1", user.getFullname(), "User name:" + user.getFullname() + " incorrect");
    }

    @Test
    public void testClassicAssertions() {
        UserInfo user1 = userDao.findByUsername("testingUsername1");
        UserInfo user2 = userDao.findByUsername("wronguser");
        assertNotNull(user1);
        assertNull(user2);

    }

    @Test
    public void testGetUsers() {
        UserInfo user = userDao.findByUsername("testingUsername1");
        assertAll("user", () -> assertEquals("Test Full Name 1", user.getFullname()));
    }

    @Test
    public void testThrows() {
        UserInfo user = null;
        Exception exception = assertThrows(NullPointerException.class, () -> user.getFullname());
        log.info(exception.getMessage());
    }

    @Test
    public void testAssumptions() {
        List<UserInfo> users = userDao.getUsers(null);
        assumeFalse(users == null);
        assumeTrue(users.size() > 0);

        UserInfo user1 = new UserInfo("testingUsername1", "testingPassword1", "Test Full Name 1");
        assumingThat(users.contains(user1), () -> assertTrue(users.size() > 1));
    }

    @Test
    public void addFindAndRemoveUser() throws Exception {
        UserInfo user = new UserInfo("testingUsername3", "testingPassword3", "Test Full Name 3");
        userDao.save(user);
        assertNotNull(userDao.findByUsername("testingUsername3"));

        userDao.delete(userDao.findByUsername("testingUsername3"));
        assertNull(userDao.findByUsername("testingUsername3"));
    }

    @AfterAll
    public static void removeData() throws Exception {
        UserDaoImpl userDao2 = new UserDaoImpl();
        userDao2.delete(userDao2.findByUsername("testingUsername1"));
        userDao2.delete(userDao2.findByUsername("testingUsername2"));
    }
}
