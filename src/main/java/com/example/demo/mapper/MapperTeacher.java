package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Files_;

@Mapper 
public interface MapperTeacher {

	@Select("insert into files (name, path, file_type, usage_type, status) OUTPUT Inserted.id values "
			+ "(#{name}, #{path}, #{file_type}, #{usage_type}, #{status}) ")
	public Long addAssignmentFile(Files_ newfile);



}
