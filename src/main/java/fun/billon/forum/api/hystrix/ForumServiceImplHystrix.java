package fun.billon.forum.api.hystrix;

import fun.billon.common.constant.CommonStatusCode;
import fun.billon.common.model.ResultModel;
import fun.billon.forum.api.feign.IForumService;
import fun.billon.forum.api.model.ForumPostModel;
import fun.billon.forum.api.model.ForumReplyModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 授权服务(AUTH)hystrix断路器
 *
 * @author billon
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class ForumServiceImplHystrix implements IForumService {

    /**
     * 发表帖子
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param paramMap       帖子信息
     * @return
     */
    @Override
    @PostMapping(value = "/post")
    public ResultModel<Integer> addPost(@RequestHeader(value = "authentication") String authentication,
                                        @RequestHeader(value = "sid") String sid,
                                        @RequestParam Map<String, String> paramMap) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:POST /post"
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]" + "-D [paramMap:" + paramMap + "]");
        return resultModel;
    }

    /**
     * 帖子评论
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param paramMap       评论信息
     * @return
     */
    @Override
    @PostMapping(value = "/post/{postId}/reply")
    public ResultModel<Integer> addReply(@RequestHeader(value = "authentication") String authentication,
                                         @RequestHeader(value = "sid") String sid,
                                         @PathVariable(value = "postId") Integer postId,
                                         @RequestParam Map<String, String> paramMap) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:POST /post/" + postId + "/reply"
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]" + "-D [paramMap:" + paramMap + "]");
        return resultModel;
    }

    /**
     * 解锁帖子
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param uid            用户id
     * @param unlockMethod   解锁方式(1:分享;2:付费)
     * @return
     */
    @Override
    @PostMapping(value = "/post/{postId}/unlock")
    public ResultModel unlockPost(@RequestHeader(value = "authentication") String authentication,
                                  @RequestHeader(value = "sid") String sid,
                                  @PathVariable(value = "postId") Integer postId,
                                  @RequestParam Integer uid,
                                  @RequestParam Integer unlockMethod) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:POST /post/" + postId + "/unlock"
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]");
        return resultModel;
    }

    /**
     * 删除帖子
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param uid            用户id
     * @return
     */
    @Override
    @DeleteMapping(value = "/post/{postId}")
    public ResultModel deletePost(@RequestHeader(value = "authentication") String authentication,
                                  @RequestHeader(value = "sid") String sid,
                                  @PathVariable(value = "postId") Integer postId,
                                  @RequestParam(value = "uid", required = false) Integer uid) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:DELETE /post/" + postId
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]");
        return resultModel;
    }

    /**
     * 删除帖子评论
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param replyId        评论id
     * @param uid            用户id
     * @return
     */
    @Override
    @DeleteMapping(value = "/post/{postId}/reply/{replyId}")
    public ResultModel deleteReply(@RequestHeader(value = "authentication") String authentication,
                                   @RequestHeader(value = "sid") String sid,
                                   @PathVariable(value = "postId") Integer postId,
                                   @PathVariable(value = "replyId") Integer replyId,
                                   @RequestParam(required = false) Integer uid) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:DELETE /post/" + postId + "/reply/" + replyId
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]");
        return resultModel;
    }

    /**
     * 更新帖子
     *
     * @param postId   帖子id
     * @param paramMap paramMap.expiredTime     过期时间                                   选填
     *                 paramMap.title           标题                                      选填
     *                 paramMap.content         内容                                      选填
     *                 paramMap.statue          状态(1:审核中;2:审核通过;3:审核未通过)        选填
     *                 paramMap.limit           限制(1:免费观看;2:分享后可见;3:付费后可见)     选填
     *                 paramMap.price           价格                                      选填
     *                 paramMap.payment         支付方式(1:金币;2:现金)                     选填
     * @return
     */
    @Override
    @PutMapping(value = "/post/{postId}")
    public ResultModel updatePost(@RequestHeader(value = "authentication") String authentication,
                                  @RequestHeader(value = "sid") String sid,
                                  @PathVariable(value = "postId") Integer postId,
                                  @RequestParam Map<String, String> paramMap) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:PUT /post/" + postId
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]" + "-D [paramMap:" + paramMap + "]");
        return resultModel;
    }

    /**
     * 更新媒体文件
     *
     * @param postId   帖子id
     * @param mediaId  附件id
     * @param paramMap paramMap.type           文件类型(1:图片;2:视频)                     选填
     *                 paramMap.cover          视频封面                                   选填
     *                 paramMap.url            文件地址                                   选填
     *                 paramMap.desc           描述                                      选填
     *                 paramMap.sort           排序                                      选填
     *                 paramMap.visiable       是否可见(1:可见;2:不可见)                   选填
     * @return
     */
    @Override
    @PutMapping(value = "/post/{postId}/media/{mediaId}")
    public ResultModel updateMedia(@RequestHeader(value = "authentication") String authentication,
                                   @RequestHeader(value = "sid") String sid,
                                   @PathVariable(value = "postId") Integer postId,
                                   @PathVariable(value = "mediaId") Integer mediaId,
                                   @RequestParam Map<String, String> paramMap) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:PUT /post/" + postId + "/media/" + mediaId
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]" + "-D [paramMap:" + paramMap + "]");
        return resultModel;
    }

    /**
     * 根据条件帖子数量
     *
     * @param paramMap paramMap.topicId     话题id          选填
     *                 paramMap.uid         要查看的用户id    选填
     * @return
     */
    @Override
    @GetMapping(value = "/post/count")
    public ResultModel<Integer> postCount(@RequestHeader(value = "authentication") String authentication,
                                          @RequestHeader(value = "sid") String sid,
                                          @RequestParam Map<String, String> paramMap) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:GET /post/count"
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]" + "-D [paramMap:" + paramMap + "]");
        return resultModel;
    }

    /**
     * 获取帖子列表
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param paramMap       查询条件
     * @return
     */
    @Override
    @GetMapping(value = "/post")
    public ResultModel<List<ForumPostModel>> posts(@RequestHeader(value = "authentication") String authentication,
                                                   @RequestHeader(value = "sid") String sid,
                                                   @RequestParam Map<String, String> paramMap) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:GET /post"
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]" + "-D [paramMap:" + paramMap + "]");
        return resultModel;
    }

    /**
     * 获取帖子详情
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param paramMap       查询条件
     * @return
     */
    @Override
    @GetMapping(value = "/post/{postId}")
    public ResultModel<ForumPostModel> postDetail(@RequestHeader(value = "authentication") String authentication,
                                                  @RequestHeader(value = "sid") String sid,
                                                  @PathVariable(value = "postId") Integer postId,
                                                  @RequestParam Map<String, String> paramMap) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:GET /post/" + postId
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]" + "-D [paramMap:" + paramMap + "]");
        return resultModel;
    }

    /**
     * 获取评论列表
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param paramMap       查询条件
     * @return
     */
    @Override
    @GetMapping(value = "/post/{postId}/reply")
    public ResultModel<List<ForumReplyModel>> replies(@RequestHeader(value = "authentication") String authentication,
                                                      @RequestHeader(value = "sid") String sid,
                                                      @PathVariable(value = "postId") Integer postId,
                                                      @RequestParam Map<String, String> paramMap) {
        ResultModel resultModel = new ResultModel();
        resultModel.setFailed(CommonStatusCode.HYSTRIX_FALLBACK, "FORUM:HYSTRIX:FALLBACK:GET /post/" + postId + "/reply"
                + " -H [Authentication:" + authentication + ",sid:" + sid + "]" + "-D [paramMap:" + paramMap + "]");
        return resultModel;
    }

}