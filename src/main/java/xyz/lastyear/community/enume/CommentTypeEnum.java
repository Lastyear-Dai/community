package xyz.lastyear.community.enume;

public enum  CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer code;


    public void setCode(Integer code) {
        this.code = code;
    }

    CommentTypeEnum(Integer code) {
        this.code = code;
    }
}
