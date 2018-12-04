package com.imti.persistence.model;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by imteyaz on 01/12/18
 **/
@MappedSuperclass
abstract class AbstractAuditable {

  private static final String SYSTEM = "System";

  private LocalDateTime createDate;
  private LocalDateTime modifiedDate;
  private String createBy;
  private String modifiedBy;

  @PrePersist
  public void prePersist() {
    LocalDateTime now = LocalDateTime.now();
    this.createDate = now;
    this.modifiedDate = now;
    this.createBy = SYSTEM;
    this.modifiedBy = SYSTEM;
  }

  @PreUpdate
  public void preUpdate() {
    this.modifiedDate = LocalDateTime.now();
    this.modifiedBy = SYSTEM;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public LocalDateTime getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(LocalDateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Override
  public String toString() {
    return "AbstractAuditable{" +
        "createDate=" + createDate +
        ", modifiedDate=" + modifiedDate +
        ", createBy='" + createBy + '\'' +
        ", modifiedBy='" + modifiedBy + '\'' +
        '}';
  }
}
