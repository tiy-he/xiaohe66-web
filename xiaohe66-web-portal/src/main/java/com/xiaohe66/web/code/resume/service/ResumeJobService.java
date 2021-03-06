package com.xiaohe66.web.code.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.file.service.UserFileService;
import com.xiaohe66.web.code.resume.mapper.ResumeJobMapper;
import com.xiaohe66.web.code.resume.dto.ResumeJobDto;
import com.xiaohe66.web.code.resume.po.ResumeJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xh
 * @date 18-10-11 011
 */
@Service
public class ResumeJobService extends AbstractService<ResumeJobMapper,ResumeJob>{

    private static final Logger LOG = LoggerFactory.getLogger(ResumeJobService.class);

    @Autowired
    private UserFileService usrFileService;

    public List<ResumeJobDto> findDtoByResumeId(Integer resumeId){
        Check.notEmpty(resumeId);
        List<ResumeJob> resumeJobList = baseMapper.findByResumeId(resumeId);

        return ClassUtils.convert(ResumeJobDto.class,resumeJobList,(dto, po)->{
            try {
                dto.setImgFileId(usrFileService.getById(po.getLogo()).getFileId());
            }catch (Exception e){
                LOG.error("取得commonFileId出现问题",e);
            }
        });
    }
}
