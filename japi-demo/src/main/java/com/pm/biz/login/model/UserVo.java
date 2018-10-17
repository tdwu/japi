package com.pm.biz.login.model;

import javax.naming.ldap.HasControls;
import java.util.List;
import java.util.Map;

public class UserVo {
    private String id;
    private String name;
    private List<String> score;
    private Map<String,Object> user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getScore() {
        return score;
    }

    public void setScore(List<String> score) {
        this.score = score;
    }

    public Map<String, Object> getUser() {
        return user;
    }

    public void setUser(Map<String, Object> user) {
        this.user = user;
    }
}
