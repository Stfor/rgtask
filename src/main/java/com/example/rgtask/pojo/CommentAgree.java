package com.example.rgtask.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xa
 * @since 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentAgree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 命令编码
     */
    private String commentId;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 删除标记
     */
    private String delFlag;

    public CommentAgree(String commentId, String userId) {
        this.commentId = commentId;
        this.userId = userId;
    }

    public CommentAgree(){}
}
