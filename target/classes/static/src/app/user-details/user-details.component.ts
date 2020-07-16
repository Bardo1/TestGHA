import { user } from '../user';
import { Component, OnInit, Input } from '@angular/core';
import { userService } from '../user.service';
import { userListComponent } from '../user-list/user-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class userDetailsComponent implements OnInit {

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

  list(){
    this.router.navigate(['users']);
  }
}
