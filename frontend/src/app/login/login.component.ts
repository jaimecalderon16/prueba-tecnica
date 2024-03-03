import { Component, OnInit } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { MessageService } from 'primeng/api';
import { CardModule } from 'primeng/card';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    InputTextModule,
    CardModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  LoginForm = this.fb.group({
    email: ['', Validators.required, Validators.email],
    password: ['', Validators.required],
  });

  constructor(private fb: FormBuilder){}

  getEmail(){
    return this.LoginForm.controls.email
  }
  getPassword(){
    return this.LoginForm.controls.password
  }
}
