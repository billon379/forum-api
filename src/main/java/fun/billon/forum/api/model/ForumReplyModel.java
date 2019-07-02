package fun.billon.forum.api.model;

import fun.billon.common.model.PaginationModel;
import fun.billon.member.api.model.UserModel;
import lombok.Data;

import java.util.Date;

/**
 * 论坛回复记录
 *
 * @author billon
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ForumReplyModel extends PaginationModel {

    /**
     * 主键-字段
     */
    private Integer id;
    /**
     * 帖子id-字段
     */
    private Integer postId;
    /**
     * 评论者id-字段
     */
    private Integer uid;
    /**
     * 被引用评论id-字段
     */
    private Integer refId;
    /**
     * 帖子评论内容-字段
     */
    private String content;
    /**
     * 创建时间-字段
     */
    private Date createTime;
    /**
     * 删除时间-字段
     */
    private Date deleteTime;

    /**
     * 引用关联-扩展属性
     */
    private ForumReplyModel replyRef;
    /**
     * 用户-扩展属性
     */
    private UserModel user;

    public ForumReplyModel() {
    }

    public ForumReplyModel(int postId) {
        this.postId = postId;
    }
}
