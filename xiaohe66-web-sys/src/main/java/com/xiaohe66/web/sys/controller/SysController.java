package com.xiaohe66.web.sys.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhException;

/**
 *
 * @author xiaohe
 * @time 17-11-11 011
 */
@XhController( "/sys")
public class SysController {

    @Get("/notLoggedIn")
    public void notLoggedIn(){
        throw new XhException(CodeEnum.NOT_LOGGED_IN);
    }

    @Get("/notPermission")
    public void notPermission(){
        throw new XhException(CodeEnum.NOT_PERMISSION);
    }

}
