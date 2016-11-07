package at.fhjoanneum.ippr.persistence.entities.model.process;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import at.fhjoanneum.ippr.persistence.entities.model.subject.SubjectModelImpl;
import at.fhjoanneum.ippr.persistence.objects.model.enums.ProcessModelState;
import at.fhjoanneum.ippr.persistence.objects.model.process.ProcessModel;
import at.fhjoanneum.ippr.persistence.objects.model.subject.SubjectModel;

@Entity(name = "PROCESS_MODEL")
public class ProcessModelmpl implements ProcessModel, Serializable {

  private static final long serialVersionUID = -7935085761537865714L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long pmId;

  @Column(unique = true)
  @NotBlank
  @Size(min = 1, max = 100)
  private String name;

  @Column
  @Lob
  private String description;

  @Column
  @NotNull
  private LocalDateTime createdAt;

  @Column
  @NotNull
  @Enumerated(EnumType.STRING)
  private ProcessModelState state;

  @NotNull
  @ManyToMany
  @Size(min = 1)
  @JoinTable(name = "process_subject_model_map", joinColumns = {@JoinColumn(name = "pm_id")},
      inverseJoinColumns = {@JoinColumn(name = "sm_id")})
  private List<SubjectModelImpl> subjectModels;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "starter_subject")
  private SubjectModelImpl starterSubject;

  ProcessModelmpl() {}

  ProcessModelmpl(final String name, final String description, final ProcessModelState state,
      final List<SubjectModelImpl> subjectModels, final SubjectModelImpl starterSubject) {
    this.name = name;
    this.description = description;
    this.createdAt = LocalDateTime.now();
    this.state = state;
    this.subjectModels = subjectModels;
    this.starterSubject = starterSubject;
  }

  @Override
  public Long getPmId() {
    return pmId;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public LocalDateTime createdAt() {
    return createdAt;
  }

  @Override
  public void setState(final ProcessModelState state) {
    this.state = state;
  }

  @Override
  public List<SubjectModel> getSubjectModels() {
    return ImmutableList.copyOf(subjectModels);
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (!ProcessModel.class.isAssignableFrom(obj.getClass())) {
      return false;
    }
    final ProcessModel other = (ProcessModel) obj;
    if ((this.pmId == null) ? (other.getPmId() != null) : !this.pmId.equals(other.getPmId())) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(pmId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("pmId", pmId)
        .append("name", name).toString();
  }
}
