package com.jinfang.graduationproject.office.vo;

import com.jinfang.graduationproject.office.FileName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BornResult {

    private boolean isSuccess;

    private FileName.Mapping fileNameMapping;

    private String targetFilePath;
}
