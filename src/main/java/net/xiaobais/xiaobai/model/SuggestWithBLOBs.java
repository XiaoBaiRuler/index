package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.SuggestWithBLOBs")
public class SuggestWithBLOBs extends Suggest {
    @ApiModelProperty(value="suggest问题建议")
    private String suggest;

    @ApiModelProperty(value="extendSuggest扩展建议")
    private String extendSuggest;

    @ApiModelProperty(value="previousSuggest前置节点建议")
    private String previousSuggest;

    @ApiModelProperty(value="nextSuggest后置节点建议")
    private String nextSuggest;

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest == null ? null : suggest.trim();
    }

    public String getExtendSuggest() {
        return extendSuggest;
    }

    public void setExtendSuggest(String extendSuggest) {
        this.extendSuggest = extendSuggest == null ? null : extendSuggest.trim();
    }

    public String getPreviousSuggest() {
        return previousSuggest;
    }

    public void setPreviousSuggest(String previousSuggest) {
        this.previousSuggest = previousSuggest == null ? null : previousSuggest.trim();
    }

    public String getNextSuggest() {
        return nextSuggest;
    }

    public void setNextSuggest(String nextSuggest) {
        this.nextSuggest = nextSuggest == null ? null : nextSuggest.trim();
    }
}