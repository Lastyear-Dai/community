package xyz.lastyear.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class QaginationDTO {
    private List<QuestionDTO> questionDTOs;
    private Integer page;
    private Integer pages;
    private Integer number;

}
