import { userDetailsComponent } from './../user-details/user-details.component';
import { Observable } from "rxjs";
import { userService } from "./../user.service";
import { user } from "./../user";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
  styleUrls: ["./user-list.component.css"]
})
export class userListComponent implements OnInit {
  users: Observable<user[]>;

  constructor(private userService: userService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getusersList();
  }

  deleteuser(id: number) {
    this.userService.deleteuser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  userDetails(id: number){
    this.router.navigate(['details', id]);
  }

  updateuser(id: number){
    this.router.navigate(['update', id]);
  }
}
