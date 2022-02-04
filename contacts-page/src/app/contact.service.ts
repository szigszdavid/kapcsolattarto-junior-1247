import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Contact } from "./contact";
import { environment } from "src/environments/environment";

@Injectable(
    {
        providedIn: 'root'
    }
)
export class ContactService {
    private apiServerUri = environment.apiBaseUrl;

    constructor(private http: HttpClient) {}

    public getContacts() : Observable<Contact[]> {
        return this.http.get<Contact[]>(`${this.apiServerUri}/contact/all`)
    }

    public addContact(contact: Contact) : Observable<Contact> {
        return this.http.post<Contact>(`${this.apiServerUri}/contact/add`, contact)
    }

    public updateContact(contact: Contact) : Observable<Contact> {
        return this.http.put<Contact>(`${this.apiServerUri}/contact/update`, contact)
    }

    public deleteContact(contactId: number) : Observable<void> {
        return this.http.delete<void>(`${this.apiServerUri}/contact/delete/${contactId}`)
    }

}