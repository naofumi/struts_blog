package struts_blog.services;

import java.util.Map;

/*
 * The Flash is a feature that allows you to send a message to the
 * next screen that will be shown. It is a convenient way to show that
 * the user was logged out or the login was successful or that the
 * user is currently not logged it. It can be used in any situation
 * where the user is redirected to another page, and you want to tell
 * the user why they were redirected and under what situation.
 *
 * This is particularly useful in combination with the Post/Redirect/Get pattern,
 * since you can easily notify the user whether the result of the Post action
 * was successful or not ("Was the object successfully updated?")
 *
 * The way it works is
 * 1. The first action sets a value in the session. The value will typically be
 *    the message that you want to show on the next screen. It could be
 *    something like "Login was successful". We will use the FLASH_KEY as the
 *    key.
 * 2. When the request for the next screen is received, we will take the value
 *    out of the session and store it in an Action instance variable. This way,
 *    the value will be available to anywhere in the Action, and if you define
 *    getters, it will also be available to the JSP file. At the same time, we
 *    delete the key-value from the session, so that the flash message is one-time only.
 *
 * To use the FlashService
 * 1. When you need to store something into the Flash, call `FlashService.setFlash`.
 *    This will store the value in the session.
 * 2. To retrieve the value in the next page,
 *    within the `withSession()` method (part of the `SessionAware` interface), call
 *    `FlashService#prepareFlash`. Store this in an instance variable. This will ensure
 *    that the flash message is transferred from the session to the instance variable
 *    before the action method is called and after Struts has injected the session
 *    into the Action.
 *
 * See struts_blog.actions.BaseAction for an example.
 * */
public class FlashService {
    final private String FLASH_KEY = "flash";

    public void setFlash(String message, Map<String, Object> sessionMap) {
        sessionMap.put(FLASH_KEY, message);
    }

    // Note that this will remove the flash message from the session
    public String prepareFlash(Map<String, Object> sessionMap) {
        String result = null;
        String value = (String) sessionMap.get(FLASH_KEY);
        if ((value != null) && (!value.isEmpty())) {
            result = value;
        }
        sessionMap.remove(FLASH_KEY);
        return result;
    }
}
