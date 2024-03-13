package fr.nzuguem.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ScheduledEvent {

    private String version;
    private String account;
    private String region;
    private Map<String, Object> detail;
    @JsonProperty("detail-type")
    private String detailType;
    private String source;
    private String id;
    private LocalDateTime time;
    private List<String> resources;

    public ScheduledEvent() {
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ScheduledEvent withVersion(String version) {
        this.setVersion(version);
        return this;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ScheduledEvent withAccount(String account) {
        this.setAccount(account);
        return this;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ScheduledEvent withRegion(String region) {
        this.setRegion(region);
        return this;
    }

    public Map<String, Object> getDetail() {
        return this.detail;
    }

    public void setDetail(Map<String, Object> detail) {
        this.detail = detail;
    }

    public ScheduledEvent withDetail(Map<String, Object> detail) {
        this.setDetail(detail);
        return this;
    }

    public String getDetailType() {
        return this.detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public ScheduledEvent withDetailType(String detailType) {
        this.setDetailType(detailType);
        return this;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ScheduledEvent withSource(String source) {
        this.setSource(source);
        return this;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ScheduledEvent withTime(LocalDateTime time) {
        this.setTime(time);
        return this;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ScheduledEvent withId(String id) {
        this.setId(id);
        return this;
    }

    public List<String> getResources() {
        return this.resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public ScheduledEvent withResources(List<String> resources) {
        this.setResources(resources);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.getVersion() != null) {
            sb.append("version: ").append(this.getVersion()).append(",");
        }

        if (this.getAccount() != null) {
            sb.append("account: ").append(this.getAccount()).append(",");
        }

        if (this.getRegion() != null) {
            sb.append("region: ").append(this.getRegion()).append(",");
        }

        if (this.getDetail() != null) {
            sb.append("detail: ").append(this.getDetail().toString()).append(",");
        }

        if (this.getDetailType() != null) {
            sb.append("detailType: ").append(this.getDetailType()).append(",");
        }

        if (this.getSource() != null) {
            sb.append("source: ").append(this.getSource()).append(",");
        }

        if (this.getId() != null) {
            sb.append("id: ").append(this.getId()).append(",");
        }

        if (this.getTime() != null) {
            sb.append("time: ").append(this.getTime().toString()).append(",");
        }

        if (this.getResources() != null) {
            sb.append("resources: ").append(this.getResources());
        }

        sb.append("}");
        return sb.toString();
    }
}
