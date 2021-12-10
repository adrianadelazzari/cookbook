package com.algonquin.cookbook.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtil {

    public String hashPassword(String password) {
        return DigestUtils.sha1Hex(password);
    }
}
