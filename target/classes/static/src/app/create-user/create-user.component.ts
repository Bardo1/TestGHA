import { userService } from '../user.service';
import { user } from '../user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateuserComponent implements OnInit {

  user: user = new user();
  submitted = false;

  constructor(private userService: userService,
    private router: Router) { }

  ngOnInit() {
  }

  newuser(): void {
    this.submitted = false;
    this.user = new user();
  }

  save() {
    this.userService.createuser(this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new user();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
}
