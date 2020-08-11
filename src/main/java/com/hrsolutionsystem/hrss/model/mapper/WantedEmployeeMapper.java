package com.hrsolutionsystem.hrss.model.mapper;

import com.hrsolutionsystem.hrss.model.domain.WantedEmployee;
import com.hrsolutionsystem.hrss.model.domain.WantedEmployeeDto;
import org.mapstruct.Mapper;

@Mapper
public interface WantedEmployeeMapper {
    WantedEmployee toMap(WantedEmployeeDto wantedEmployeeDto);
    WantedEmployeeDto toDto(WantedEmployee wantedEmployee);
}