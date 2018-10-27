package com.xiaohe66.web.text.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.CollectionUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.text.dao.ArticleLogDao;
import com.xiaohe66.web.text.po.ArticleLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xh
 * @date 18-04-16 016
 */
@Service
public class ArticleLogService extends AbstractService<ArticleLog>{

    private static final Logger LOG = LoggerFactory.getLogger(ArticleLogService.class);

    private ArticleLogDao articleLogDao;

    public ArticleLogService() {
    }

    @Autowired
    public ArticleLogService(ArticleLogDao articleLogDao) {
        super(articleLogDao);
        this.articleLogDao = articleLogDao;
    }


    /**
     * 方法弃用，请使用 add()
     * @param po 插入的实体
     * @param currentUsrId 当前操作者id
     */
    @Override
    @Deprecated
    public void add(ArticleLog po, Long currentUsrId) {
        throw new XhException(CodeEnum.NOT_IMPLEMENTED);
    }

    public void addPrepare(Long articleId){
        Check.notEmptyCheck(articleId);
        WebUtils.setSessionAttr(Final.Str.ARTICLE_LOG_ADD_PREPARE,articleId);
    }

    /**
     * 定义查看数量的规则
     * 每个session只能增加一次访问量
     */
    public void add() {

        Long articleId = WebUtils.getSessionAttr(Final.Str.ARTICLE_LOG_ADD_PREPARE);
        Long currentUsrId = UsrHelper.getCurrentUsrIdNotEx();

        //查看自己的文章不加查看量
        if(Check.eq(articleId,currentUsrId)){
            LOG.debug("look oneself article");
            return;
        }

        Set<Long> articleIdSet = WebUtils.getSessionAttr(Final.Str.ARTICLE_LOG_CACHE);
        if(CollectionUtils.isNull(articleIdSet)){
            articleIdSet = new HashSet<>(4);
            WebUtils.setSessionAttr(Final.Str.ARTICLE_LOG_CACHE,articleIdSet);
        }

        String ip = WebUtils.getRequestIP();
        LOG.debug("ip："+ip);

        if(!articleIdSet.contains(articleId)){
            ArticleLog articleLog = new ArticleLog(articleId);
            articleLog.setIp(ip);
            articleLog.setCreateId(currentUsrId);
            articleLog.setCreateTime(new Date());
            super.add(articleLog,currentUsrId);

            articleIdSet.add(articleId);
        }
    }

    /**
     * 月查看数量
     * 传入的用户id不为null时，统计该用户的文章月被查看数量
     * 传入的用户id为null时，统计所有用户的文章月被查看数量
     * @param usrId 用户id
     * @return List<Map<String,Long>>
     *          id:文章id
     *          count:被阅读数量
     */
    public List<Map<String,Long>> countDownloadOfMonth(Long usrId){
        return articleLogDao.countDownloadOfMonth(usrId);
    }

    public Long countByArticleId(Long articleId){
        Check.notEmptyCheck(articleId);
        return articleLogDao.countByArticleId(articleId);
    }
}
