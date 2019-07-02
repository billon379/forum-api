package fun.billon.forum.api.model;

import com.alibaba.fastjson.annotation.JSONField;
import fun.billon.common.model.PaginationModel;
import fun.billon.member.api.model.UserModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 论坛帖子
 *
 * @author billon
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ForumPostModel extends PaginationModel {

    /**
     * 帖子解锁状态(0):未解锁
     */
    public static final int LOCKED = 0;
    /**
     * 帖子解锁状态(1):已解锁
     */
    public static final int UNLOCKED = 1;

    /**
     * 帖子审核状态(1):审核中
     */
    public static final int STATUS_VERIFY_WAITING = 1;
    /**
     * 帖子审核状态(2):审核通过
     */
    public static final int STATUS_VERIFIED = 2;
    /**
     * 帖子审核状态(3):审核未通过
     */
    public static final int STATUS_VERIFY_FAILED = 3;

    /**
     * 帖子限制条件(1):免费观看
     */
    public static final int LIMIT_FREE = 1;
    /**
     * 帖子限制条件(2):分享后可见
     */
    public static final int LIMIT_SHARE = 2;
    /**
     * 帖子限制条件(3):付费后可见
     */
    public static final int LIMIT_PAY = 3;

    /**
     * 帖子支付方式(1):金币
     */
    public static final int PAYMENT_POINTS = 1;
    /**
     * 帖子支付方式(2):现金
     */
    public static final int PAYMENT_CASH = 2;

    /**
     * 主键-字段
     */
    private Integer id;
    /**
     * 话题id-字段
     */
    private Integer topicId;
    /**
     * 帖子标题-字段
     */
    private String title;
    /**
     * 帖子内容-字段
     */
    private String content;
    /**
     * 发帖人id-字段
     */
    private Integer uid;
    /**
     * 经度-字段
     */
    private Double lat;
    /**
     * 纬度-字段
     */
    private Double lng;
    /**
     * 定位地址-字段
     */
    private String address;
    /**
     * 发布状态(1:审核中;2:审核通过;3:审核未通过)-字段
     */
    private Integer status;
    /**
     * 限制(1:免费观看;2:分享后可见;3:付费后可见)-字段
     */
    private Integer limit;
    /**
     * 价格
     */
    private Float price;
    /**
     * 过期时间
     */
    private Date expiredTime;
    /**
     * 支付方式(1:金币;2:现金)
     */
    private Integer payment;
    /**
     * 创建时间-字段
     */
    private Date createTime;
    /**
     * 删除时间-字段
     */
    @JSONField(serialize = false)
    private Date deleteTime;

    /**
     * 需要附件信息-扩展属性
     */
    @JSONField(serialize = false)
    private boolean requireMedias = true;
    /**
     * 需要扩展信息-扩展属性
     */
    @JSONField(serialize = false)
    private boolean requireExtend = true;
    /**
     * 需要评论信息-扩展属性
     */
    @JSONField(serialize = false)
    private boolean requireReplies = false;
    /**
     * 需要解锁用户信息-扩展属性
     */
    @JSONField(serialize = false)
    private boolean requireUnlocks = false;
    /**
     * 附件信息-扩展属性
     */
    private List<ForumMediaModel> mediaList;
    /**
     * 扩展信息-扩展属性
     */
    private ForumPostExtendModel extend;
    /**
     * 评论信息-扩展属性
     */
    private List<ForumReplyModel> replyList;
    /**
     * 解锁用户信息-扩展属性
     */
    private List<UserModel> unlockList;
    /**
     * 用户信息-扩展属性
     */
    private UserModel user;
    /**
     * 当前用户是否解锁(0:未解锁;1:已解锁)-扩展属性
     */
    private int unlock = 0;

    /**
     * 当前登录用户id-扩展属性
     */
    @JSONField(serialize = false)
    private Integer currentUid;

    public ForumPostModel() {
    }

    public ForumPostModel(Integer id) {
        this.id = id;
    }

}
