package com.hrsolutionsystem.hrss.model.domain.entity;

import com.hrsolutionsystem.hrss.model.domain.enums.CvStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="CV_DETAILS")
public class CvDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="APPLYING_POSITION")
    @NotNull
    private String applyingPosition;

    @Column(name ="FIRST_NAME")
    @NotNull
    private String firstName;

    @Column(name ="LAST_NAME")
    @NotNull
    private String lastName;

    @Column(name ="PHONE_NUMBER")
    @NotNull
    private Long phoneNumber;

    @Column(name ="EMAIL")
    @NotNull
    private String email;

    @Column(name ="CV_FILE_ID")
    @NotNull
    private Long cvFileId;

    @Column(name ="COVER_LETTER_ID")
    @NotNull
    private Long coverLetterId;

    @Column(name ="STATUS")
    @NotNull
    @Enumerated(EnumType.STRING)
    private CvStatus status;

}
