package com.back.endTest.web.common;

public enum AttachFileType {
//  F010301("첨부파일"),
//  F010302("이미지파일");

    F0101("고민게시판 이미지");

    private String description;

    AttachFileType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
