import { userDetailsComponent } from './user-details/user-details.component';
import { CreateuserComponent } from './create-user/create-user.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { userListComponent } from './user-list/user-list.component';
import { UpdateuserComponent } from './update-user/update-user.component';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path: 'users', component: userListComponent },
  { path: 'add', component: CreateuserComponent },
  { path: 'update/:id', component: UpdateuserComponent },
  { path: 'details/:id', component: userDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
