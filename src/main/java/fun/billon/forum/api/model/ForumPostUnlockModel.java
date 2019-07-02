package fun.billon.forum.api.model;

import fun.billon.common.model.PaginationModel;
import lombok.Data;

import java.util.Date;

/**
 * 帖子解锁记录
 *
 * @author billon
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ForumPostUnlockModel extends PaginationModel {

    /**
     * 解锁方式:分享
     */
    public static final int UNLOCK_METHOD_SHARE = 1;
    /**
     * 解锁方式:付费
     */
    public static final int UNLOCK_METHOD_PAY = 2;

    /**
     * 主键-字段
     */
    private Integer id;
    /**
     * 帖子id-字段
     */
    private Integer postId;
    /**
     * 用户id-字段
     */
    private Integer uid;
    /**
     * 解锁方式(1:分享;2:付费)-字段
     */
    private Integer unlockMethod;
    /**
     * 创建时间-字段
     */
    private Date createTime;
    /**
     * 删除时间-字段
     */
    private Date deleteTime;

    public ForumPostUnlockModel() {
    }

    public ForumPostUnlockModel(int postId) {
        this.postId = postId;
    }

    public ForumPostUnlockModel(int postId, int uid) {
        this(postId);
        this.uid = uid;
    }

    public ForumPostUnlockModel(int postId, int uid, int unlockMethod) {
        this(postId, uid);
        this.unlockMethod = unlockMethod;
    }

}
