package fun.billon.forum.api.model;

import fun.billon.common.model.PaginationModel;
import lombok.Data;

/**
 * 帖子数量统计
 *
 * @author billon
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ForumPostExtendModel extends PaginationModel {

    /**
     * 帖子id-字段
     */
    private Integer postId;
    /**
     * 阅读数-字段
     */
    private Integer readNum;
    /**
     * 点赞数-字段
     */
    private Integer praiseNum;
    /**
     * 分享数-字段
     */
    private Integer sharedNum;
    /**
     * 评论数-字段
     */
    private Integer replyNum;
    /**
     * 购买数-字段
     */
    private Integer payNum;

    public ForumPostExtendModel() {
    }

    public ForumPostExtendModel(Integer postId) {
        this(postId, 0, 0, 0, 0, 0);
    }

    public ForumPostExtendModel(Integer postId, Integer readNum, Integer praiseNum, Integer sharedNum, Integer replyNum, Integer payNum) {
        this.postId = postId;
        this.readNum = readNum;
        this.praiseNum = praiseNum;
        this.sharedNum = sharedNum;
        this.replyNum = replyNum;
        this.payNum = payNum;
    }

}
