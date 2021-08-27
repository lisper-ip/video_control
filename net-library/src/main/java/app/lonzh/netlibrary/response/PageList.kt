package app.lonzh.netlibrary.response

/**
 *
 * @ProjectName:    lisper
 * @Description:    描述
 * @Author:         Lisper
 * @CreateDate:     5/21/21 4:44 PM
 * @UpdateUser:     Lisper：
 * @UpdateDate:     5/21/21 4:44 PM
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
data class PageList<T>(
    var datas: List<T>,
    var over: Boolean
)