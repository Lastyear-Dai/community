package xyz.lastyear.community.enume;

public enum  CommentTypeEnum {
    //评论
    QUESTION(1),
    //回复评论
    COMMENT(2);
    private Integer code;

    public Integer getCode() {
        return code;
    }
//判断评论类型是否正确或存在
    public static boolean compare(Integer type) {
        for(CommentTypeEnum commentTypeEnum:CommentTypeEnum.values()){
            if(commentTypeEnum.getCode()==type){
                return true;
            }
        }
        return false;

    }


    public void setCode(Integer code) {
        this.code = code;
    }

    CommentTypeEnum(Integer code) {
        this.code = code;
    }
}
