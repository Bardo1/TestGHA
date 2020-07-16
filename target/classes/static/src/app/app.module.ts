import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateuserComponent } from './create-user/create-user.component';
import { userDetailsComponent } from './user-details/user-details.component';
import { userListComponent } from './user-list/user-list.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdateuserComponent } from './update-user/update-user.component';
@NgModule({
  declarations: [
    AppComponent,
    CreateuserComponent,
    userDetailsComponent,
    userListComponent,
    UpdateuserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
