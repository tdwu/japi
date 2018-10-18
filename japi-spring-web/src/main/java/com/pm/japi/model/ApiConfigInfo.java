package com.pm.japi.model;

import org.springframework.beans.factory.annotation.Value;

public class ApiConfigInfo {
    @Value("${japi.title:系统接口文档}")
    private String title;//html title
    @Value("${japi.company:重庆品目网络}")
    private String company;//公司名称
    @Value("${japi.base-path:}")
    private String basePath = "";//接口地址
    @Value("${japi.web-site:http://www.pmwangluo.com}")
    private String webSite;//公司站点
    @Value("${japi.read-me:tdwu.md}")
    private String readMe;
    @Value("${japi.mark-down:1}")
    private int markDown = 1;//0 不显示mark down 1 只显示mark down， 2 markdown和接口描述都显示

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getReadMe() {
        return readMe;
    }

    public void setReadMe(String readMe) {
        this.readMe = readMe;
    }

    public int getMarkDown() {
        return markDown;
    }

    public void setMarkDown(int markDown) {
        this.markDown = markDown;
    }
}
