package com.algonquin.cookbook.util;

import java.util.UUID;

public class LinkUtil {

    public String generateLink(String userEmail) {
        return "http://localhost:8080/cookbook/VerifyAccount?email = " + userEmail + "&uniqueString=" + UUID.randomUUID();
    }
}
