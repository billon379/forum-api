package fun.billon.forum.api.model;

import fun.billon.common.model.PaginationModel;
import lombok.Data;

import java.util.Date;

/**
 * 帖子相关的媒体文件
 *
 * @author billon
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ForumMediaModel extends PaginationModel {

    /**
     * 文件类型:图片
     */
    public static final int TYPE_PIC = 1;
    /**
     * 文件类型:视频
     */
    public static final int TYPE_VIDEO = 2;

    /**
     * 主键-字段
     */
    private Integer id;
    /**
     * 帖子id-字段
     */
    private Integer postId;
    /**
     * 文件类型(1:图片;2:视频)-字段
     */
    private Integer type;
    /**
     * 视频封面-字段
     */
    private String cover;
    /**
     * 文件地址-字段
     */
    private String url;
    /**
     * 描述-字段
     */
    private String desc;
    /**
     * 排序-字段
     */
    private Integer sort;
    /**
     * 是否可见(1:可见;2:不可见)-字段
     */
    private Integer visiable;
    /**
     * 创建时间-字段
     */
    private Date createTime;
    /**
     * 删除时间-字段
     */
    private Date deleteTime;

    public ForumMediaModel() {
    }

    public ForumMediaModel(int postId) {
        this.postId = postId;
    }

}