package net.xiaobais.xiaobai.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="net.xiaobais.xiaobai.model.Map")
public class Map {
    @ApiModelProperty(value="mapId导图ID")
    private Integer mapId;

    @ApiModelProperty(value="mapName导图名称")
    private String mapName;

    @ApiModelProperty(value="mapAuthor导图作者")
    private String mapAuthor;

    @ApiModelProperty(value="mapVersion版本信息")
    private String mapVersion;

    @ApiModelProperty(value="mapData数据内容")
    private String mapData;

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName == null ? null : mapName.trim();
    }

    public String getMapAuthor() {
        return mapAuthor;
    }

    public void setMapAuthor(String mapAuthor) {
        this.mapAuthor = mapAuthor == null ? null : mapAuthor.trim();
    }

    public String getMapVersion() {
        return mapVersion;
    }

    public void setMapVersion(String mapVersion) {
        this.mapVersion = mapVersion == null ? null : mapVersion.trim();
    }

    public String getMapData() {
        return mapData;
    }

    public void setMapData(String mapData) {
        this.mapData = mapData == null ? null : mapData.trim();
    }
}