import { Component, OnInit,    } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { CalendarModule } from 'primeng/calendar';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService, ConfirmEventType } from 'primeng/api';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { DropdownModule } from 'primeng/dropdown';
import { DialogModule } from 'primeng/dialog';
import axios from 'axios';
import {route} from '../../config'
import { Console, log } from 'console';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  providers:[
    MessageService,
    ConfirmationService
  ],
  imports: [
    TableModule,
    ButtonModule,
    CardModule,
    InputTextModule,
    FormsModule,
    CalendarModule,
    ConfirmDialogModule,
    DialogModule,
    ReactiveFormsModule,
    ProgressSpinnerModule,
    DropdownModule,
    ToastModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})

export class DashboardComponent implements OnInit  {


  code= '';
  identification= '';
  name= '';
  startDate= '';
  endDate= '';

  grades = [{
    courseName: '',
    grade: ''
  }];

  isloadingtable = true;
  students = [];
  typesIdentifications = [];

  isEdit = false;
  idUserEdit:number | null = null

  constructor(private confirmationService: ConfirmationService, private messageService: MessageService, public fb: FormBuilder) {
    this.estudentForm = this.fb.group({
      code: ['', [Validators.required]],
      firtsName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      typeIdentification: ['', [Validators.required]],
      identificacion: ['', [Validators.required]],
      phone : ['', [Validators.required]],
      address: ['', [Validators.required]],
      birthDate: ['', [Validators.required]],
      dateAdmission: ['', [Validators.required]],
    });
  }

  ngOnInit() {
    this.getJoke();
  }


getJoke() {
  this.loadData();
}

confirm2(event: Event, id:number) {
    this.confirmationService.confirm({
        target: event.target as EventTarget,
        message: 'Estas seguro que quieres eleminar el registro?',
        header: 'Eliminar',
        icon: 'pi pi-info-circle',
        acceptButtonStyleClass:"p-button-danger p-button-text",
        rejectButtonStyleClass:"p-button-text p-button-text",
        acceptIcon:"none",
        rejectIcon:"none",

        accept: () => {
            this.deleteStudent(id)
            this.loadStudents();
        },
        reject: () => {
            this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
        }
    });
}

estudentForm: FormGroup;

async loadData(){
  await axios.get( route + '/home').then(
    (res) =>{
      this.students = res.data.list_students;
      this.typesIdentifications = res.data.list_type_identification;
      this.isloadingtable = false;
    }
  ).catch(
    (e)=>{
      console.error(e)

    }
  );
}

async loadStudents(){
  this.isloadingtable = true;
  await axios.get( route + '/getStudents').then(
    (res) =>{
      this.students = res.data;
      this.isloadingtable = false;
    }
  ).catch(
    (e)=>{
      console.error(e)
    }
  );
}

addGred(){
  if(this.grades[this.grades.length - 1].courseName != '' && this.grades[this.grades.length - 1].grade != ''){

  this.grades.push(
    {
      courseName: '',
      grade: ''
    }
  )
  }
}

async filterStudent(){
  this.isloadingtable = false;
  await axios.post( route + '/searchStudents', {
    "code": this.code == '' ? null : this.code,
    "identification": this.identification == '' ? null : this.identification,
    "name": this.name == '' ? null : this.name,
    "dateAdmissionStart": this.startDate == '' ? null : this.startDate,
    "dateAdmissionEnd": this.endDate == '' ? null : this.endDate
  }).then(
    (res) =>{
      this.students = res.data;
      this.isloadingtable = false;
    }
  ).catch(
    (e)=>{
      console.error(e)

    }
  );
}

storeStudent(){
  if (this.isEdit) {
    this.updateStudent();
  }else{
    this.saveStudent()
  }
}

async saveStudent(){
  if(this.estudentForm.valid){

  await axios.post( route + '/addStudent', {
    userData: {
         "code": this.estudentForm.value.code,
        "firstName": this.estudentForm.value.firtsName,
        "lastName": this.estudentForm.value.lastName,
        "phone": this.estudentForm.value.phone,
        "dateOfBirth": this.estudentForm.value.birthDate,
        "dateOfAdmission": this.estudentForm.value.dateAdmission,
        "address": this.estudentForm.value.address,
        "identification": this.estudentForm.value.identificacion,
        "typeIdentification": {
            "id": this.estudentForm.value.typeIdentification,
        }
    },
    grades: this.grades
  }).then(
    (res) =>{
      this.loadStudents();
      this.showSuccess('Studiante Creado Con exito');
      this.closeDialog();
      this.isloadingtable = false;
    }
  ).catch(
    (e)=>{
      console.log(e);

    }
  );
  }else{
    this.showError('Revise que todos los campos esten llenos')
  }

}

async updateStudent(){
  if(this.estudentForm.valid){

  await axios.put( route + `/updateStudent/${this.idUserEdit}`, {
    student: {
         "code": this.estudentForm.value.code,
        "firstName": this.estudentForm.value.firtsName,
        "lastName": this.estudentForm.value.lastName,
        "phone": this.estudentForm.value.phone,
        "dateOfBirth": this.estudentForm.value.birthDate,
        "dateOfAdmission": this.estudentForm.value.dateAdmission,
        "address": this.estudentForm.value.address,
        "identification": this.estudentForm.value.identificacion,
        "typeIdentification": {
            "id": this.estudentForm.value.typeIdentification,
        }
    },
    grades: this.grades
  }).then(
    (res) =>{
      this.loadStudents()
      this.showSuccess('Studiante Actualizado Con exito');
      this.closeDialog();
      this.isloadingtable = false;
    }
  ).catch(
    (e)=>{
      console.log(e);

    }
  );
  }else{
    this.showError('Revise que todos los campos esten llenos')
  }

}

fillform(estudent: any){
  this.idUserEdit = estudent.id;
  this.isEdit = true;
  this.grades = estudent.grades;

  this.estudentForm.patchValue({
    code: estudent.code,
    firtsName: estudent.firstName, // Assumed typo correction
    lastName: estudent.lastName,
    phone: estudent.phone,
    birthDate:  new Date(estudent.dateOfBirth),
    dateAdmission:  new Date(estudent.dateOfAdmission),
    address: estudent.address,
    identificacion: estudent.identification,
    typeIdentification: estudent.typeIdentification.id
  });

  this.showDialog();
}

async deleteStudent(id: number){
  await axios.delete( route + `/deleteStudent/${id}`).then(
    (res) =>{
      this.showSuccess('Estudiante eliminado correctamente.')
      this.loadStudents()
    }
  ).catch(
    (e)=>{
      console.error(e)

    }
  );
}

modalVisible: boolean = false;

showDialog() {
    this.modalVisible = true;
}

closeDialog() {
    this.isEdit = false;
    this.modalVisible = false;
}

showSuccess(message: string) {
  this.messageService.add({ severity: 'success', summary: 'Exito!', detail: message });
}

showError(message:string) {
  this.messageService.add({ severity: 'error', summary: 'Error', detail: message });
}

}


