package com.mapstruct.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.mapstruct.dto.StudentDto;
import com.mapstruct.entiy.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	
	@Mappings({
        @Mapping(target="studentId", source="student.id"),
        @Mapping(target="studentfirstName", source="student.firstName"),
        @Mapping(target="studentLastName", source="student.lastName"),
        @Mapping(target="dob", source="student.birthDate")
      })
    StudentDto studentToStudentDto( Student student);
    
    
    @Mappings({
        @Mapping(target="id", source="studentDto.studentId"),
        @Mapping(target="firstName", source="studentDto.studentfirstName"),
        @Mapping(target="lastName", source="studentDto.studentLastName"),
        @Mapping(target="birthDate", source="studentDto.dob")
      })
    Student studentDtoToStudent(StudentDto studentDto);
    
    List<StudentDto> modelsToDtos(List<Student> students);
}
