package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Suggest")
public class Suggest {
    @ApiModelProperty(value="suggestId建议ID")
    private Integer suggestId;

    @ApiModelProperty(value="nodeId当前节点ID")
    private Integer nodeId;

    @ApiModelProperty(value="userId谁提的建议")
    private Integer userId;

    @ApiModelProperty(value="choice提了那几个方面的建议(以#分割)")
    private String choice;

    @ApiModelProperty(value="question问题")
    private String question;

    @ApiModelProperty(value="extend扩展")
    private String extend;

    @ApiModelProperty(value="previousQuestion前置节点ID(以#分割)")
    private String previousQuestion;

    @ApiModelProperty(value="nextQuestion后置节点ID(以#分割)")
    private String nextQuestion;

    public Integer getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Integer suggestId) {
        this.suggestId = suggestId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice == null ? null : choice.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend == null ? null : extend.trim();
    }

    public String getPreviousQuestion() {
        return previousQuestion;
    }

    public void setPreviousQuestion(String previousQuestion) {
        this.previousQuestion = previousQuestion == null ? null : previousQuestion.trim();
    }

    public String getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(String nextQuestion) {
        this.nextQuestion = nextQuestion == null ? null : nextQuestion.trim();
    }
}