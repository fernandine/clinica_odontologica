import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerForm!: FormGroup;
  mobileNumber: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      passConfirmation: ['', Validators.required, this.passwordMatchValidator],
    });

  }

  get form() {
    return this.registerForm.controls;
  }

  passwordMatchValidator(formControl: FormControl) {
    const password = formControl.get('password')?.value;
    const passConfirmation = formControl.get('passConfirmation')?.value;
    return password === passConfirmation ? null : { mismatch: true };
  }

  formatPhoneNumber(mobileNumber: string): string {
    const phoneRegex = /^(\d{2})?[\s-]?(\d{4,5})-?(\d{4})$/;
    const formattedPhoneNumber = mobileNumber.replace(
      phoneRegex,
      function (match, p1, p2, p3) {
        if (p1) {
          return '(' + p1 + ') ' + p2 + '-' + p3;
        } else {
          return p2 + '-' + p3;
        }
      }
    );
    return formattedPhoneNumber;
  }

  formatMobileNumber() {
    let mobileNumber = this.registerForm.get('mobileNumber')?.value;
    mobileNumber = mobileNumber.replace(/\D/g, ''); // remove todos os caracteres não numéricos
    const formattedPhoneNumber = this.formatPhoneNumber(mobileNumber);
    this.registerForm.get('mobileNumber')?.setValue(formattedPhoneNumber);
  }

  onReturn() {
    this.router.navigate(['/auth-login']);
  }

  onRegister() {
    console.log('Dados enviados:', this.registerForm.value);
    if (this.registerForm.invalid) {
      return;
    }
    // Formata o número de telefone antes de enviar
    const formattedPhoneNumber = this.formatPhoneNumber(this.mobileNumber);
    this.registerForm.get('mobileNumber')?.setValue(formattedPhoneNumber);

    this.userService.createUser(this.registerForm.value).subscribe(
      (response) => {
        console.log('Usuário registrado com sucesso!', response);
        this.router.navigate(['/auth-login']);
      },
      (error) => console.log('Erro ao registrar usuário', error)
    );
  }
}
