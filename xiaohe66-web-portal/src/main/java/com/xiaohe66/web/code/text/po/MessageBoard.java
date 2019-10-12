package com.xiaohe66.web.code.text.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xh
 * @date 18-04-01 001
 */
@TableName("xiaohe66_web_text_message_board")
public class MessageBoard extends BasePoDetailed{
    protected Integer usrId;
    protected String msg;

    /**
     * 匿名留言的用户名称
     */
    protected String anonymity;

    public MessageBoard(){}

    public MessageBoard(Integer usrId, String msg) {
        this.usrId = usrId;
        this.msg = msg;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"usrId\":\"" + usrId + "\""
                + ",\"createTime\":" + createTime
                + ",\"msg\":\"" + msg + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"anonymity\":\"" + anonymity + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
