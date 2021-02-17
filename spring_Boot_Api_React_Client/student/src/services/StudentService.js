

import axios from 'axios';

const STUDENT_API_BASE_URL = "http://127.0.0.1:8082/api/v1/students/";

class StudentService {

    getStudents(){
        return axios.get(STUDENT_API_BASE_URL);
    }

    createStudent(student){
        return axios.post(STUDENT_API_BASE_URL, student);
    }

    getStudentById(studentId){
        return axios.get(STUDENT_API_BASE_URL + studentId);
    }

    updateStudent(student, studentId){
        return axios.put(STUDENT_API_BASE_URL + studentId, student);
    }

    deleteStudent(studentId){
        return axios.delete(STUDENT_API_BASE_URL+ studentId);
    }
}

export default new StudentService()

