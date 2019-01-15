package com.edu.tuiguang.controller;

import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.utils.FileUtils;
import com.edu.tuiguang.utils.ResultUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "FileUploadController", description = "文件上传")
@RestController
@RequestMapping(value = {"/admin/file", "/app/file"})
public class FileUploadController {

	@Value("${file.path}")
	private String filePath;

	@PostMapping("/imageUpload")
	public Object imageUpload(@RequestParam("image") MultipartFile file) {

		String upload = FileUtils.upload(file, filePath, file.getOriginalFilename());
		if (StringUtils.isNotBlank(upload) && null != upload) {
			return ResultUtils.success(upload);
		} else {
			return ResultUtils.error(ErrorCode.IMG_UPLOAD_ERROR);
		}
	}
}
