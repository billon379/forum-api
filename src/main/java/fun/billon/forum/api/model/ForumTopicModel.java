package fun.billon.forum.api.model;

import fun.billon.common.model.PaginationModel;
import lombok.Data;

import java.util.Date;

/**
 * 论坛话题
 *
 * @author billon
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ForumTopicModel extends PaginationModel {

    /**
     * 主键-字段
     */
    private Integer id;
    /**
     * 话题Title-字段
     */
    private String title;
    /**
     * 创建时间-字段
     */
    private Date createTime;
    /**
     * 删除时间-字段
     */
    private Date deleteTime;

}
