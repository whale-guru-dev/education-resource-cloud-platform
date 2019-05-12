package com.tianwen.springcloud.microservice.question.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.microservice.question.constant.IQuestionMicroConstants;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Enoc on 2018/11/7.
 */
@Table(name = "t_e_paper_parameter")
public class PaperParam extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('" + IQuestionMicroConstants.prefix + "', 'seq_t_e_paper_parameter_paramid')")
    @Column(name = "paramid")
    @ApiModelProperty("paramid")
    private String paramid;

    @Column(name = "type")
    @ApiModelProperty(value = "type", required = true)
    private String type;

    @Column(name = "typename")
    @ApiModelProperty(value = "typename", required = true)
    private String typename;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    @Column(name = "sequenceno")
    @ApiModelProperty(value = "sequenceno", required = true)
    private Integer sequenceno;

    List<String> typenamelist;

    public String getParamid() {
        return paramid;
    }

    public void setParamid(String paramid) {
        this.paramid = paramid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSequenceno() {
        return sequenceno;
    }

    public void setSequenceno(Integer sequenceno) {
        this.sequenceno = sequenceno;
    }

    public List<String> getTypenamelist() {
        return typenamelist;
    }

    public void setTypenamelist(List<String> typenamelist) {
        this.typenamelist = typenamelist;
    }
}
