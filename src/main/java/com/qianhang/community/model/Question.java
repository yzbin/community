package com.qianhang.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String descriptition;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
