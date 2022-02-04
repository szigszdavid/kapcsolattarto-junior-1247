import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Contact } from './contact';
import { ContactService } from './contact.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  public contacts: Contact[] = [];
  constructor(private contactService: ContactService) {}

  ngOnInit() {
      this.getContacts();
  }
  public getContacts(): void
  {
    this.contactService.getContacts().subscribe(
      (response: Contact[]) => {
        this.contacts = response;
        console.log(response);
        
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public onOpenModal( mode: string,contact?: Contact): void {
    console.log("1");
    
    const container = document.querySelector("#main-container")
    const button = document.createElement('button');
    button.type = "button";
    button.style.display = "none";
    button.setAttribute('data-toggle','modal');
    console.log("2");
    
    if (mode === 'add') {
      console.log("3");
    
      button.setAttribute('data-target','#addEmployeeModal');
    }
    if (mode === 'edit') {
      button.setAttribute('data-target','#updateEmployeeModal');
    }
    if (mode === 'delete') {
      button.setAttribute('data-target','#deleteEmployeeModal');
    }

    console.log("4");
    

    container?.appendChild(button);
    button.click();
  }

}
