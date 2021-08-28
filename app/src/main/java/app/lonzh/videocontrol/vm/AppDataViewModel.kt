package app.lonzh.videocontrol.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.lonzh.videocontrol.data.bean.UserInfo

/**
 *
 * @ProjectName:    lisper
 * @Description:    描述
 * @Author:         Lisper
 * @CreateDate:     2021/6/8 3:05 下午
 * @UpdateUser:     Lisper：
 * @UpdateDate:     2021/6/8 3:05 下午
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class AppDataViewModel : ViewModel(){
    /**
     * 用户信息
     */
    val userInfo = MutableLiveData<UserInfo?>(null)

}