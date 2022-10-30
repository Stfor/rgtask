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
 * @since 2022-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class University implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String schoolName;

    private String schoolCode;

    private String competentDarptment;

    private String location;

    private String level;

    private String type;


}
