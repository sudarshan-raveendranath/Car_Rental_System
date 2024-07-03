import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = ["http://localhost:8080"];

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, private storageService: StorageService) { }

  postCar(carDto:any): Observable<any> {
    console.log('Attempting to post car:', carDto);
    return this.http.post(BASIC_URL + `/api/admin/car`, carDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    const token = this.storageService.getToken();
    console.log('Retrieved token:', token);
  
    const headersWithToken = authHeaders.set('Authorization', 'Bearer ' + token);
    console.log('Created Authorization header:', headersWithToken);
  
    return headersWithToken;
  }
}

