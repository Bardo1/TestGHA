import { Component, OnInit } from '@angular/core';
import { user } from '../user';
import { ActivatedRoute, Router } from '@angular/router';
import { userService } from '../user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateuserComponent implements OnInit {

  id: number;
  user: user;

  constructor(private route: ActivatedRoute,private router: Router,
    private userService: userService) { }

  ngOnInit() {
    this.user = new user();

    this.id = this.route.snapshot.params['id'];
    
    this.userService.getuser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
      }, error => console.log(error));
  }

  updateuser() {
    this.userService.updateuser(this.id, this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new user();
    this.gotoList();
  }

  onSubmit() {
    this.updateuser();    
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
}
