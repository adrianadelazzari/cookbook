package com.algonquin.cookbook.entity;

import com.algonquin.cookbook.enums.LinkType;

public class Link {

    private String userEmail;
    private String uniqueString;
    private LinkType linkType;
    private Boolean isLinkUsed;

    public Link(String userEmail, String uniqueString, LinkType linkType, Boolean isLinkUsed) {
        this.userEmail = userEmail;
        this.uniqueString = uniqueString;
        this.linkType = linkType;
        this.isLinkUsed = isLinkUsed;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUniqueString() {
        return uniqueString;
    }

    public void setUniqueString(String uniqueString) {
        this.uniqueString = uniqueString;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public Boolean getLinkUsed() {
        return isLinkUsed;
    }

    public void setLinkUsed(Boolean linkUsed) {
        isLinkUsed = linkUsed;
    }
}
