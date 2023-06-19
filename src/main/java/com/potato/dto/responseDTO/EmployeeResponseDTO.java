package com.potato.dto.responseDTO;

import com.potato.entity.Employee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author potato
 * @PackageName:com.potato.dto.responseDTO
 * @Description: 员工信息ResponseDTO
 */
@Data
@ApiModel(description = "员工信息ResponseDTO")
public class EmployeeResponseDTO implements Serializable {

    private static final long serialVersionUID = -7701717985489736216L;

    @ApiModelProperty(value = "员工信息")
    private Employee employee;

    @ApiModelProperty(value = "有效的职位列表")
    private List<ActivePositionListResponseDTO> activePositionList;

    @ApiModelProperty(value = "有效的部门列表")
    private List<ActiveDepartmentListResponseDTO> activeDepartmentList;

}
