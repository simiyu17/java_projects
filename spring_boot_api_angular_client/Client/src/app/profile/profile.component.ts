import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import Student from '../Student';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  studentForm: FormGroup;
  form_title: string;
  student: Student;
  student_id: string;
  students: Student[];
  constructor(private fb: FormBuilder, private ss: StudentService, private router: Router, public datepipe: DatePipe) {
    this.createForm();
  }

  createForm(): void {
    this.studentForm = this.fb.group({
      name: [null, Validators.required],
      gender: [null, Validators.required],
      city: [null, Validators.required],
      country: [null, Validators.required],
      dateOfBirth: [null, Validators.required]
    });
  }


  refresh() {
    this.router.navigate(['']);
    this.ngOnInit();  
   }

  redirectTo(uri) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
    this.router.navigate([uri]));
  }
  
  resetStudent(): void {
    this.form_title = 'New Student Details';
    this.student_id = null;
    this.student = new Student();
  }

  setStudentId(selected_id) {
    this.student_id = selected_id;
  }

  onFormSubmit() {
    if (this.student_id != null) {
      this.ss.updateStudent(this.student_id, this.studentForm.value)
        .subscribe((res: any) => {
          this.refresh();
        }, (err: any) => {
          console.log(err);
        });
    } else {
      this.ss.addStudent(this.studentForm.value)
        .subscribe((res: any) => {
          this.refresh();
        }, (err: any) => {
          console.log(err);
        });
    }
  }

  formatDate(date: string) {
    return this.datepipe.transform(date, 'MM/dd/yyyy')
  }

  getStudentWithId(id: string) {
    this.form_title = 'Edit Student Details';
    this.ss.getStudentById(id)
      .subscribe((data: any) => {
        this.student = data;
        this.student_id = data.id;
        this.student.dateOfBirth = this.formatDate(this.student.dateOfBirth)
        console.log(this.student);
      });
  }

  deleteStudentWithId(id: any) {
    this.ss.deleteStudent(id)
      .subscribe(res => {
        this.refresh();
      }, (err) => {
        console.log(err);
      }
      );
  }

  dtOptions: DataTables.Settings = {};

  ngOnInit(): void {
    this.resetStudent();

    this.ss.getStudents()
    .subscribe((res: any) => {
      this.students = res;
      console.log(this.students);
    }, err => {
      console.log(err);
    });

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };

  }



}
