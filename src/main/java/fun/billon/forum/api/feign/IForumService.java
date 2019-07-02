package fun.billon.forum.api.feign;

import fun.billon.common.model.ResultModel;
import fun.billon.forum.api.hystrix.ForumServiceImplHystrix;
import fun.billon.forum.api.model.ForumPostModel;
import fun.billon.forum.api.model.ForumReplyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * forum服务接口
 *
 * @author billon
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(value = "forum", fallback = ForumServiceImplHystrix.class)
public interface IForumService {

    /**
     * 发表帖子
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param paramMap       帖子信息
     * @return
     */
    @PostMapping(value = "/post")
    ResultModel<Integer> addPost(@RequestHeader(value = "authentication") String authentication,
                                 @RequestHeader(value = "sid") String sid,
                                 @RequestParam Map<String, String> paramMap);

    /**
     * 帖子评论
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param paramMap       评论信息
     * @return
     */
    @PostMapping(value = "/post/{postId}/reply")
    ResultModel<Integer> addReply(@RequestHeader(value = "authentication") String authentication,
                                  @RequestHeader(value = "sid") String sid,
                                  @PathVariable(value = "postId") Integer postId,
                                  @RequestParam Map<String, String> paramMap);

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
    @PostMapping(value = "/post/{postId}/unlock")
    ResultModel unlockPost(@RequestHeader(value = "authentication") String authentication,
                           @RequestHeader(value = "sid") String sid,
                           @PathVariable(value = "postId") Integer postId,
                           @RequestParam Integer uid,
                           @RequestParam Integer unlockMethod);

    /**
     * 删除帖子
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param uid            用户id
     * @return
     */
    @DeleteMapping(value = "/post/{postId}")
    ResultModel deletePost(@RequestHeader(value = "authentication") String authentication,
                           @RequestHeader(value = "sid") String sid,
                           @PathVariable(value = "postId") Integer postId,
                           @RequestParam(value = "uid", required = false) Integer uid);

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
    @DeleteMapping(value = "/post/{postId}/reply/{replyId}")
    ResultModel deleteReply(@RequestHeader(value = "authentication") String authentication,
                            @RequestHeader(value = "sid") String sid,
                            @PathVariable(value = "postId") Integer postId,
                            @PathVariable(value = "replyId") Integer replyId,
                            @RequestParam(required = false) Integer uid);

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
    @PutMapping(value = "/post/{postId}")
    ResultModel updatePost(@RequestHeader(value = "authentication") String authentication,
                           @RequestHeader(value = "sid") String sid,
                           @PathVariable(value = "postId") Integer postId,
                           @RequestParam Map<String, String> paramMap);

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
    @PutMapping(value = "/post/{postId}/media/{mediaId}")
    ResultModel updateMedia(@RequestHeader(value = "authentication") String authentication,
                            @RequestHeader(value = "sid") String sid,
                            @PathVariable(value = "postId") Integer postId,
                            @PathVariable(value = "mediaId") Integer mediaId,
                            @RequestParam Map<String, String> paramMap);

    /**
     * 根据条件帖子数量
     *
     * @param paramMap paramMap.topicId     话题id          选填
     *                 paramMap.uid         要查看的用户id    选填
     * @return
     */
    @GetMapping(value = "/post/count")
    ResultModel<Integer> postCount(@RequestHeader(value = "authentication") String authentication,
                                   @RequestHeader(value = "sid") String sid,
                                   @RequestParam Map<String, String> paramMap);

    /**
     * 获取帖子列表
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param paramMap       查询条件
     * @return
     */
    @GetMapping(value = "/post")
    ResultModel<List<ForumPostModel>> posts(@RequestHeader(value = "authentication") String authentication,
                                            @RequestHeader(value = "sid") String sid,
                                            @RequestParam Map<String, String> paramMap);

    /**
     * 获取帖子详情
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param paramMap       查询条件
     * @return
     */
    @GetMapping(value = "/post/{postId}")
    ResultModel<ForumPostModel> postDetail(@RequestHeader(value = "authentication") String authentication,
                                           @RequestHeader(value = "sid") String sid,
                                           @PathVariable(value = "postId") Integer postId,
                                           @RequestParam Map<String, String> paramMap);

    /**
     * 获取评论列表
     *
     * @param authentication 请求头中的鉴权信息
     * @param sid            内部服务id
     * @param postId         帖子id
     * @param paramMap       查询条件
     * @return
     */
    @GetMapping(value = "/post/{postId}/reply")
    ResultModel<List<ForumReplyModel>> replies(@RequestHeader(value = "authentication") String authentication,
                                               @RequestHeader(value = "sid") String sid,
                                               @PathVariable(value = "postId") Integer postId,
                                               @RequestParam Map<String, String> paramMap);

}