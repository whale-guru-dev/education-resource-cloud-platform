package com.tianwen.springcloud.microservice.question.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.microservice.question.constant.IQuestionMicroConstants;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by Enoc on 2018/11/7.
 */
@Table(name = "t_e_label_property")
public class LabelProperty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('" + IQuestionMicroConstants.prefix + "', 'seq_t_e_label_property_labelid')")
    @Column(name = "labelid")
    @ApiModelProperty("labelid")
    private String labelid;

    @Column(name = "type")
    @ApiModelProperty(value = "type", required = true)
    private String type;

    @Column(name = "typename")
    @ApiModelProperty(value = "typename", required = true)
    private String typename;

    @Column(name = "sequenceno")
    @ApiModelProperty(value = "sequenceno", required = true)
    private Integer sequenceno;

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getSequenceno() {
        return sequenceno;
    }

    public void setSequenceno(Integer sequenceno) {
        this.sequenceno = sequenceno;
    }
}
