package com.xiaohe66.web.text.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.common.util.HtmlUtils;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.sys.service.SysCfgService;
import com.xiaohe66.web.text.dao.MessageBoardDao;
import com.xiaohe66.web.text.dto.MessageBoardDto;
import com.xiaohe66.web.text.param.MessageBoardParam;
import com.xiaohe66.web.text.po.MessageBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xh
 * @date 18-04-01 001
 */
@Service
public class MessageBoardService extends AbstractService<MessageBoard>{

    @Autowired
    private SysCfgService sysCfgService;

    @Autowired
    private UsrService usrService;

    public MessageBoardService() {
    }

    @Autowired
    public MessageBoardService(MessageBoardDao messageBoardDao) {
        super(messageBoardDao);
    }

    /**
     * 方法弃用，请使用add(?,?,?);
     * @param po 插入的实体
     * @param currentUsrId 当前操作者id
     */
    @Deprecated
    @Override
    public void add(MessageBoard po, Long currentUsrId) {
        throw new XhException(CodeEnum.DISABLE_FUNCTION,"invoke add(?,?,?) pls");
    }

    /**
     * 增加一条留言方法
     * @param msg       留言
     * @param usrId     被留言的用户
     * @param currentUsrId  当前登录用户
     */
    public MessageBoard add(String msg,Long usrId, Long currentUsrId){
        msg = HtmlUtils.delHtmlTag(msg);
        Check.notEmptyCheck(msg,usrId,currentUsrId);
        MessageBoard messageBoard = new MessageBoard(usrId,msg);
        super.add(messageBoard,currentUsrId);
        return messageBoard;
    }

    /**
     * 根据被留言的用户id查询
     * @param usrId 被留言的用户id，默认为站长
     * @return  List<MessageBoardDto>
     */
    public List<MessageBoardDto> findByUsrId(Long usrId){
        if(usrId == null){
            String usrIdStr = sysCfgService.findValByKey(StrEnum.CFG_KEY_XIAO_HE_USR_ID.data());
            usrId = StrUtils.toLong(usrIdStr);
        }

        List<MessageBoard> messageBoardList = super.findByParam(new MessageBoardParam(usrId));

        return ClassUtils.convertList(MessageBoardDto.class,messageBoardList,(messageBoardDto,messageBoard)->{
            Usr usr = usrService.findById(messageBoard.getCreateId());
            messageBoardDto.setUsrName(usr.getUsrName());
            messageBoardDto.setImgFileId(usr.getImgFileId());
        });
    }

}
